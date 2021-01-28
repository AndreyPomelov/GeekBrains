package game.animals;

import game.Controller;
import game.Logic;

public class Dog extends Animal {

    private String name;
    private int level;
    private static final String[] names = {"Пумба", "Микки", "Чак", "Блейд", "Спайк"};
    private static int nameCount;
    private static int lastLevel;

    public Dog() {
        Logic.eraseRightPanel();
        this.level = (int)(Math.random() * 3) + 1 + lastLevel;
        lastLevel = this.level;
        this.setMaxHitPoints(500 + 50*(this.level));
        this.setHitPoints(500 + 50*(this.level));
        this.setPower(50 + 5*(this.level));
        this.setDefense(50 + 5*(this.level));
        this.name = names[nameCount];
        nameCount++;
    }

    public static void setLastLevel(int lastLevel) {
        Dog.lastLevel = lastLevel;
    }

    public static void setNameCount(int nameCount) {
        Dog.nameCount = nameCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
