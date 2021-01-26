package game.animals;

public class Dog extends Animal {

    private String name;
    private int level;
    private static final String[] names = {"Пумба", "Микки", "Чак", "Блейд", "Спайк"};
    private static int nameCount = 0;

    public Dog(int maxHitPoints, int hitPoints, int power, int defense, String name, int level) {
        super(maxHitPoints, hitPoints, power, defense);
        this.name = names[nameCount];
        if (nameCount < 4) nameCount++;
        this.level = level;
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
