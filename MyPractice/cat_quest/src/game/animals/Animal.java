package game.animals;

public abstract class Animal {

    public String catMessage1;
    public String catMessage2;
    public String catMessage3;
    public String enemyMessage1;
    public String enemyMessage2;
    public String enemyMessage3;
    public String whatAnimal;
    private int maxHitPoints;
    private int hitPoints;
    private int power;
    private int defense;

    public Animal(int maxHitPoints, int hitPoints, int power, int defense) {
        this.maxHitPoints = maxHitPoints;
        this.hitPoints = hitPoints;
        this.power = power;
        this.defense = defense;
    }

    protected Animal() {
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
