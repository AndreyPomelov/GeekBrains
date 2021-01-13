package lesson_1;

public class Wall {

    private final String name;
    private final int height;

    public Wall(String name) {
        this.name = name;
        height = (int) (Math.random() * 5) + 8;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }
}
