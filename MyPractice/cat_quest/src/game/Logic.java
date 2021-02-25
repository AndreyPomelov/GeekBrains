package game;

import game.animals.*;
import javafx.application.Platform;

import java.io.*;

public class Logic {

    private static final String SAVED_GAME_PATH = "save.ser";

    private static Controller controller;
    public static Cat cat;
    public static Dog dog;
    public static Rat rat;
    public static Mouse mouse;
    private static int chance;
    private static boolean winGame = false;
    private static boolean allBossesBeaten = false;

    public static synchronized void startNewGame(String catName) {
        controller.mainTextArea.setText("Ты управляешь своим кошаком.\n" +
                "Задача кошака - спереть колбасу.\n" +
                "Но колбаса охраняется пятью боссами-собакиренами.\n" +
                "Прокачивай кошака на охоте, побеждай боссов, забирай колбасу.\n" +
                "Удачи!\n\n");
        winGame = false;
        allBossesBeaten = false;
        cat = new Cat(500, 500, 50, 50,
                catName, 1, 0, 0, 0);
        updateLeftPanel();
        Dog.setNameCount(0);
        Dog.setLastLevel(0);
        dog = new Dog();
    }

    public static synchronized void recon() {
        if (allBossesBeaten) {
            controller.mainTextArea.appendText("Все боссы повержены!\n");
            controller.unblockButtons();
            return;
        }
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
        controller.mainTextArea.appendText(cat.getName() + " повышает свой уровень!\n\n");
        cat.setLevel(cat.getLevel() + 1);
        cat.setMaxHitPoints(cat.getMaxHitPoints() + 50);
        cat.setHitPoints(cat.getMaxHitPoints());
        cat.setPower(cat.getPower() + 5);
        cat.setDefense(cat.getDefense() + 5);
        cat.setExp(0);
        updateLeftPanel();
        }

    private static synchronized void levelDown() {
        new Thread(() -> {
            if (cat.getLevel() > 1) {
                controller.mainTextArea.appendText(cat.getName() + " теряет уровень!\n\n");
                cat.setLevel(cat.getLevel() - 1);
                cat.setMaxHitPoints(cat.getMaxHitPoints() - 50);
                cat.setPower(cat.getPower() - 5);
                cat.setDefense(cat.getDefense() - 5);
            } else {
                controller.blockButtons();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                controller.mainTextArea.appendText(cat.getName() + " немного отдыхает и восстанавливает силы...\n\n");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                controller.mainTextArea.appendText("Отдых окончен! Силы восстановлены!\n\n");
                controller.unblockButtons();
            }
            cat.setHitPoints(cat.getMaxHitPoints());
            cat.setExp(0);
            updateLeftPanel();
        }).start();
    }

    public static synchronized void updateLeftPanel() {
        Platform.runLater(() -> controller.leftPanelLabel.setText(" Статус:\n Здоровье: \t" + cat.getHitPoints() + " из " + cat.getMaxHitPoints() +
                "\n Сила: \t\t" + cat.getPower() +
                "\n Защита: \t\t" + cat.getDefense() +
                "\n Уровень: \t" + cat.getLevel() +
                "\n Еда: \t\t" + cat.getFoodCount() +
                "\n Валерьянка: \t" + cat.getValCount()));
    }

    public static synchronized void updateRightPanel() {
        Platform.runLater(() -> controller.rightPanelLabel.setText(" Данные разведки:\n" +
                " Босс " + dog.getName() + "\n Уровень: " + dog.getLevel()));
    }

    public static synchronized void eraseRightPanel() {
        Platform.runLater(() -> controller.rightPanelLabel.setText(" Данные разведки:"));
    }

    public static synchronized void setController(Controller controller) {
        Logic.controller = controller;
    }

    public static synchronized void setStartWindowController() {
    }

    public static synchronized void steal() {
        new Thread(() -> {
            controller.mainTextArea.appendText(cat.getName() + " пытается спереть колбасу.\n\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (allBossesBeaten) {
                winGame = true;
                controller.mainTextArea.appendText("Попытка успешна!!!\n\n");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cat = null;
                Controller.cat = null;
                try {
                    controller.gameOver(winGame);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                controller.mainTextArea.appendText("Колбаса охраняется боссом! " + cat.getName() + " отхватывает люлей!\n");
                cat.setHitPoints(cat.getHitPoints() - 100);
                if (cat.getHitPoints() < 0) cat.setHitPoints(0);
                controller.mainTextArea.appendText("Здоровье кошака упало до " + cat.getHitPoints() + "\n\n");
                if (cat.getHitPoints() == 0) {
                    levelDown();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
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
            updateLeftPanel();
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
                controller.unblockButtons();
            } else if (chance >= 2 && chance <= 4) {
                controller.mainTextArea.appendText(cat.getName() + " находит порцию корма!\n");
                cat.setFoodCount(cat.getFoodCount() + 1);
                updateLeftPanel();
                controller.unblockButtons();
            } else if (chance >= 5 && chance <= 9) {
                controller.mainTextArea.appendText("Неожиданно из темноты на кошака нападает крыса-мутант!\n\n");
                rat = new Rat();
                fight(rat);
            } else if (chance >= 10 && chance <= 16) {
                controller.mainTextArea.appendText(cat.getName() + " замечает мышь!\n\n");
                mouse = new Mouse();
                fight(mouse);
            } else {
                controller.mainTextArea.appendText("Охота не удалась!\n");
                controller.unblockButtons();
            }
        }).start();
    }

    public static synchronized void fight(Animal enemy) {
        new Thread(() -> {
            try {
                while (cat.getHitPoints() > 0 && enemy.getHitPoints() > 0) {
                    try {
                        Thread.sleep(1500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (cat.getHitPoints() > 0) {
                        chance = (int)(Math.random() * 3);
                        switch (chance) {
                            case 0 :
                                controller.mainTextArea.appendText(cat.getName() + enemy.catMessage1 + "\n");
                                break;
                            case 1 :
                                controller.mainTextArea.appendText(cat.getName() + enemy.catMessage2 + "\n");
                                break;
                            case 2 :
                                controller.mainTextArea.appendText(cat.getName() + enemy.catMessage3 + "\n");
                                break;
                        }
                        chance = (int)(Math.random() * 41);
                        int hitPower = cat.getPower() - 20 + chance;
                        chance = (int)(Math.random() * 41);
                        int damage = hitPower - ((enemy.getDefense() - 20 + chance)/2);
                        if (damage < 0) damage = 0;
                        controller.mainTextArea.appendText("Сила атаки - " + damage + "\n");
                        enemy.setHitPoints(enemy.getHitPoints() - damage);
                        if (enemy.getHitPoints() < 0) enemy.setHitPoints(0);
                        if (damage > 0) controller.mainTextArea.appendText("Здоровье противника падает до " + enemy.getHitPoints() + "\n\n");
                        else controller.mainTextArea.appendText("\n");
                    }
                    try {
                        Thread.sleep(1500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (enemy.getHitPoints() > 0) {
                        chance = (int)(Math.random() * 3);
                        switch (chance) {
                            case 0 :
                                controller.mainTextArea.appendText(enemy.enemyMessage1 + "\n");
                                break;
                            case 1 :
                                controller.mainTextArea.appendText(enemy.enemyMessage2 + "\n");
                                break;
                            case 2 :
                                controller.mainTextArea.appendText(enemy.enemyMessage3 + "\n");
                                break;
                        }
                        chance = (int)(Math.random() * 41);
                        int hitPower = enemy.getPower() - 20 + chance;
                        chance = (int)(Math.random() * 41);
                        int damage = hitPower - ((cat.getDefense() - 20 + chance)/2);
                        if (damage < 0) damage = 0;
                        controller.mainTextArea.appendText("Сила атаки - " + damage + "\n");
                        cat.setHitPoints(cat.getHitPoints() - damage);
                        if (cat.getHitPoints() < 0) cat.setHitPoints(0);
                        if (damage > 0) controller.mainTextArea.appendText("Здоровье кошака падает до " + cat.getHitPoints() + "\n\n");
                        else controller.mainTextArea.appendText("\n");
                        updateLeftPanel();
                    }
                }
            }
            catch (Exception e) {
                System.out.println("В битве поймано исключение");
            }
            if (cat.getHitPoints() <=0) {
                switch (enemy.whatAnimal) {
                    case "Mouse" :
                        controller.mainTextArea.appendText("Невероятно! Мышь победила кошака!\n\n");
                        break;
                    case "Rat" :
                        controller.mainTextArea.appendText(cat.getName() + " не в силах больше противостоять крысе-мутанту!\n\n");
                        break;
                    case "Dog" :
                        controller.mainTextArea.appendText(cat.getName() + " отступает, потеряв все силы в бою!\n\n");
                        break;
                }
                dog.setHitPoints(dog.getMaxHitPoints());
                levelDown();
            } else {
                switch (enemy.whatAnimal) {
                    case "Mouse" :
                        controller.mainTextArea.appendText(cat.getName() + " побеждает мышь!\n\n");
                        break;
                    case "Rat" :
                        controller.mainTextArea.appendText(cat.getName() + " одерживает победу!\n\n");
                        break;
                    case "Dog" :
                        controller.mainTextArea.appendText(cat.getName() + " одерживает блестящую победу над боссом!\n\n");
                        break;
                }
                cat.setExp(cat.getExp() + 1);
                if (cat.getExp() >= 3) levelUp();
                if (enemy.whatAnimal.equals("Dog")) createNewDog();
            }
            controller.unblockButtons();
        }).start();
    }

    private static synchronized void createNewDog() {
        if (dog.getName().equals("Спайк")) allBossesBeaten = true;
        else {
            dog = new Dog();
            eraseRightPanel();
        }
    }

    public static synchronized void bossAttack() {
        new Thread(() -> {
            if (allBossesBeaten) {
                controller.mainTextArea.appendText("Все боссы повержены!\n");
                controller.unblockButtons();
                return;
            }
            controller.mainTextArea.appendText(cat.getName() + " выходит в бой против босса!\n\n");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            controller.mainTextArea.appendText("Ему противостоит босс " + dog.getName() + "!\n\n");
            fight(dog);
        }).start();
    }

    public static synchronized void saveGame() throws IOException {
        if (cat == null) return;
        SavedGame savedGame = new SavedGame(winGame, allBossesBeaten, cat.getName(), cat.getLevel(), cat.getFoodCount(), cat.getValCount(), cat.getExp(), cat.getMaxHitPoints(), cat.getHitPoints(), cat.getPower(), cat.getDefense(), dog.getName(), dog.getLevel(), Dog.getNameCount());
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVED_GAME_PATH));
        out.writeObject(savedGame);
        out.flush();
        out.close();
        controller.mainTextArea.appendText("Игра сохранена успешно!\n\n");
    }

    public static synchronized void loadGame() throws IOException, ClassNotFoundException {
        controller = Controller.controller;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVED_GAME_PATH));
            SavedGame savedGame = (SavedGame) in.readObject();
            startNewGame("tempName");
            cat.setName(savedGame.getCatName());
            cat.setLevel(savedGame.getCatLevel());
            cat.setFoodCount(savedGame.getFoodCount());
            cat.setValCount(savedGame.getValCount());
            cat.setExp(savedGame.getExp());
            cat.setMaxHitPoints(savedGame.getCatMaxHP());
            cat.setHitPoints(savedGame.getCatHP());
            cat.setPower(savedGame.getCatPower());
            cat.setDefense(savedGame.getCatDefense());
            dog.setName(savedGame.getDogName());
            dog.setLevel(savedGame.getDogLevel());
            Dog.setNameCount(savedGame.getNameCount());
            winGame = savedGame.isWinGame();
            allBossesBeaten = savedGame.isAllBossesBeaten();
            in.close();
            updateLeftPanel();
            eraseRightPanel();
            controller.mainTextArea.setText("Ранее сохранённая игра успешно загружена!\n\n");
        }
        catch (FileNotFoundException e) {
            controller.mainTextArea.appendText("Сохранённой игры не обнаружено.\n\n");
        }
    }
}
