package game.animals;

import game.Controller;
import game.Logic;

public class Dog extends Animal {

    public String catMessage1;
    public String catMessage2;
    public String catMessage3;
    public String enemyMessage1;
    public String enemyMessage2;
    public String enemyMessage3;

    private String name;
    private int level;
    private static final String[] names = {"Пумба", "Микки", "Чак", "Блейд", "Спайк"};
    private static int nameCount = 0;
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
        super.enemyMessage1 = name + " запинается и падает прямо на кошака!";
        super.enemyMessage2 = name + " мощно кусает кошака!";
        super.enemyMessage3 = name + " своим весом впечатывает кошака в стену!";
        super.catMessage1 = " опрокидывает цветочный горшок на собакирена!";
        super.catMessage2 = " прищемляет собакирену хвост дверью!";
        super.catMessage3 = " обходит сзади и кусает собакирена за задницу!";
        super.whatAnimal = "Dog";
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
