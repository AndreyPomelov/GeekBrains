package my_practice.cat_quest_2.animals;

public class Cat extends Animal {

    private String name;
    private int level;
    private int foodCount;
    private int valCount;
    private int exp;

    public Cat(int maxHitPoints, int hitPoints, int power, int defense, String name, int level, int foodCount, int valCount, int exp) {
        super(maxHitPoints, hitPoints, power, defense);
        this.name = name;
        this.level = level;
        this.foodCount = foodCount;
        this.valCount = valCount;
        this.exp = exp;
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

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public int getValCount() {
        return valCount;
    }

    public void setValCount(int valCount) {
        this.valCount = valCount;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
