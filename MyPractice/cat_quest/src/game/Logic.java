package game;

import game.animals.Cat;
import game.animals.Dog;
import javafx.application.Platform;

public class Logic {

    private static Controller controller;
    private static StartWindowController startWindowController;
    private static Cat cat;
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

    private static synchronized void pause() throws InterruptedException {
        Thread.sleep(1500);
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
            // TODO EAT
        }).start();
    }
}
