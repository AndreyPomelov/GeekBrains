package game;

import game.animals.*;
import javafx.application.Platform;

public class Logic {

    private static Controller controller;
    private static StartWindowController startWindowController;
    public static Cat cat;
    private static Dog dog;
    private static int chance;
    private static boolean winGame;
    private static boolean allBossesBeaten = false;

    public static synchronized void startNewGame(String catName) {
        controller.mainTextArea.setText("Ты управляешь своим кошаком.\n" +
                "Задача кошака - спереть колбасу.\n" +
                "Но колбаса охраняется пятью боссами-собакиренами.\n" +
                "Удачи!\n\n");
        cat = new Cat(500, 500, 50, 50,
                catName, 1, 0, 0, 0);
        updateLeftPanel();
        Dog.setNameCount(0);
        Dog.setLastLevel(0);
        dog = new Dog();
    }

    public static synchronized void recon() {
        new Thread(() -> {
            if (cat == null) return;
            controller.mainTextArea.appendText(cat.getName() + " пытается провести разведку\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            chance = (int)(Math.random() * 5);
            if (chance == 4) {
                controller.mainTextArea.appendText("Разведка успешна!\nИмя босса - " +
                        dog.getName() + "\nУровень - " + dog.getLevel() + "\n\n");
                updateRightPanel();
            } else {
                controller.mainTextArea.appendText("Разведка провалилась! " + cat.getName() + " отхватывает люлей!\n");
                cat.setHitPoints(cat.getHitPoints() - 100);
                if (cat.getHitPoints() < 0) cat.setHitPoints(0);
                controller.mainTextArea.appendText("Здоровье кошака упало до " + cat.getHitPoints() + "\n\n");
                if (cat.getHitPoints() == 0) levelDown();
                updateLeftPanel();
            }
            controller.unblockButtons();
        }).start();
    }

    private static synchronized void levelUp() {
        controller.mainTextArea.appendText(cat.getName() + " повышает свой уровень!");
        cat.setLevel(cat.getLevel() + 1);
        cat.setMaxHitPoints(cat.getMaxHitPoints() + 50);
        cat.setHitPoints(cat.getMaxHitPoints());
        cat.setPower(cat.getPower() + 5);
        cat.setDefense(cat.getDefense() + 5);
        cat.setExp(0);
        updateLeftPanel();
        }

    private static synchronized void levelDown() {
        if (cat.getLevel() > 1) {
            controller.mainTextArea.appendText(cat.getName() + " теряет уровень!\n\n");
            cat.setLevel(cat.getLevel() - 1);
            cat.setMaxHitPoints(cat.getMaxHitPoints() - 50);
            cat.setHitPoints(cat.getMaxHitPoints());
            cat.setPower(cat.getPower() - 5);
            cat.setDefense(cat.getDefense() - 5);
            cat.setExp(0);
            updateLeftPanel();
        } else {
            //TODO конец игры
        }
    }

    public static synchronized void updateLeftPanel() {
        Platform.runLater(() -> {
            controller.leftPanelLabel.setText(" Статус:\n Здоровье: \t" + cat.getHitPoints() + " из " + cat.getMaxHitPoints() +
                    "\n Сила: \t\t" + cat.getPower() +
                    "\n Защита: \t\t" + cat.getDefense() +
                    "\n Уровень: \t" + cat.getLevel() +
                    "\n Еда: \t\t" + cat.getFoodCount() +
                    "\n Валерьянка: \t" + cat.getValCount());
        });
    }

    public static synchronized void updateRightPanel() {
        Platform.runLater(() -> {
            controller.rightPanelLabel.setText(" Данные разведки:\n" +
                    " Босс " + dog.getName() + "\n Уровень: " + dog.getLevel());
        });
    }

    public static synchronized void eraseRightPanel() {
        Platform.runLater(() -> {
            controller.rightPanelLabel.setText(" Данные разведки:");
        });
    }

    public static synchronized Controller getController() {
        return controller;
    }

    public static synchronized void setController(Controller controller) {
        Logic.controller = controller;
    }

    public static synchronized StartWindowController getStartWindowController() {
        return startWindowController;
    }

    public static synchronized void setStartWindowController(StartWindowController startWindowController) {
        Logic.startWindowController = startWindowController;
    }

    public static synchronized void steal() {
        new Thread(() -> {
            controller.mainTextArea.appendText(cat.getName() + " пытается спереть колбасу.\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (allBossesBeaten) {
                // TODO WINGAME
            }
            else {
                controller.mainTextArea.appendText("Колбаса охраняется боссом! " + cat.getName() + " отхватывает люлей!\n");
                cat.setHitPoints(cat.getHitPoints() - 100);
                if (cat.getHitPoints() < 0) cat.setHitPoints(0);
                controller.mainTextArea.appendText("Здоровье кошака упало до " + cat.getHitPoints() + "\n\n");
                if (cat.getHitPoints() == 0) levelDown();
                updateLeftPanel();
            }
            controller.unblockButtons();
        }).start();
    }

    public static synchronized void eat() {
        new Thread(() -> {
            if (cat.getFoodCount() > 0) {
                cat.setFoodCount(cat.getFoodCount() - 1);
                cat.setHitPoints(cat.getHitPoints() + 100);
                if (cat.getHitPoints() > cat.getMaxHitPoints()) cat.setHitPoints(cat.getMaxHitPoints());
                controller.mainTextArea.appendText(cat.getName() +
                        " с удовольствием хавает!\nЗдоровье увеличено до " + cat.getHitPoints());
            } else controller.mainTextArea.appendText(cat.getName() +
                    " смотрит на тебя голодными глазками.\nЧем ты собрался кошака кормить? Еды-то нет!");
            controller.unblockButtons();
        }).start();
    }

    public static synchronized void val() {
        if (cat.getValCount() > 0) {
            cat.setValCount(cat.getValCount() - 1);
            chance = (int)(Math.random() * 5) + 1;
            if (chance == 1) {
                cat.setMaxHitPoints(cat.getMaxHitPoints() + 50);
                controller.mainTextArea.appendText(cat.getName() + " чувствует прилив здоровья!");
            } else if (chance == 2) {
                cat.setPower(cat.getPower() + 5);
                controller.mainTextArea.appendText(cat.getName() + " чувствует прилив силы!");
            } else if (chance == 3) {
                cat.setDefense(cat.getDefense() + 5);
                controller.mainTextArea.appendText(cat.getName() + " чувствует свою непобедимость!");
            } else if (chance == 4) {
                cat.setExp(cat.getExp() + 1);
                controller.mainTextArea.appendText(cat.getName() + " чувствует, что становится немного опытнее!");
                if (cat.getExp() == 3) levelUp();
            } else if (chance == 5) {
                controller.mainTextArea.appendText(cat.getName() + " ничего особенного не чувствует...");
            }
        } else controller.mainTextArea.appendText(cat.getName() + " грустно смотрит на тебя.\nНе стыдно кошака дразнить? У тебя нет валерьянки!");
        updateLeftPanel();
        controller.unblockButtons();
    }

    public static synchronized void hunt() {
        new Thread(() -> {
            controller.mainTextArea.appendText(cat.getName() + " выходит на охоту.\n\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            chance = (int)(Math.random() * 25) + 1;
            if (chance == 1) {
                controller.mainTextArea.appendText(cat.getName() + " находит валерьянку!\n");
                cat.setValCount(cat.getValCount() + 1);
                updateLeftPanel();
            } else if (chance >= 2 && chance <= 4) {
                controller.mainTextArea.appendText(cat.getName() + " находит порцию корма!\n");
                cat.setFoodCount(cat.getFoodCount() + 1);
                updateLeftPanel();
            } else if (chance >= 5 && chance <= 9) {
                controller.mainTextArea.appendText("Неожиданно из темноты на кошака нападает крыса-мутант!\n\n");
                fight(new Rat());
            } else if (chance >= 10 && chance <= 16) {
                controller.mainTextArea.appendText(cat.getName() + " замечает мышь!\n\n");
                fight(new Mouse());
            } else controller.mainTextArea.appendText("Охота не удалась!\n");
            controller.unblockButtons();
        }).start();
    }

    public static synchronized void fight(Animal enemy) {
        new Thread(() -> {
            while (cat.getHitPoints() > 0 && enemy.getHitPoints() > 0) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (cat.getHitPoints() > 0) {
                    chance = (int)(Math.random() * 3);
                    switch (chance) {
                        case 0 : controller.mainTextArea.appendText(cat.getName() + enemy.catMessage1);
                        case 1 : controller.mainTextArea.appendText(cat.getName() + enemy.catMessage2);
                        case 2 : controller.mainTextArea.appendText(cat.getName() + enemy.catMessage3);
                    }
                    if (chance == 0) System.out.println(cat.getName() + " опрокидывает цветочный горшок на собакирена!");
                    if (chance == 1) System.out.println(cat.getName() + " прищемляет собакирену хвост дверью!");
                    if (chance == 2) System.out.println(cat.getName() + " обходит сзади и кусает собакирена за задницу!");
                    chance = (int)(Math.random() * 41);
                    hitPower = cat.getPower() - 20 + chance;
                    chance = (int)(Math.random() * 41);
                    damage = hitPower - ((dog.getDefense() - 20 + chance)/2);
                    if (damage < 0) damage = 0;
                    System.out.println("Сила атаки - " + damage);
                    dog.setHitPoints(dog.getHitPoints() - damage);
                    if (dog.getHitPoints() < 0) dog.setHitPoints(0);
                    if (damage > 0) System.out.println("Здоровье босса-собакирена падает до " + dog.getHitPoints());
                }
                pause();
                pause();
                if (dog.getHitPoints() > 0) {
                    System.out.println();
                    chance = (int)(Math.random() * 3);
                    if (chance == 0) System.out.println(dog.getName() + " запинается и падает прямо на кошака!");
                    if (chance == 1) System.out.println(dog.getName() + " мощно кусает кошака!");
                    if (chance == 2) System.out.println(dog.getName() + " своим весом впечатывает кошака в стену!");
                    chance = (int)(Math.random() * 41);
                    hitPower = dog.getPower() - 20 + chance;
                    chance = (int)(Math.random() * 41);
                    damage = hitPower - ((cat.getDefense() - 20 + chance)/2);
                    if (damage < 0) damage = 0;
                    System.out.println("Сила атаки - " + damage);
                    cat.setHitPoints(cat.getHitPoints() - damage);
                    if (cat.getHitPoints() < 0) cat.setHitPoints(0);
                    if (damage > 0) System.out.println("Здоровье кошака падает до " + cat.getHitPoints());
                }
            }

        }).start();
    }
}
