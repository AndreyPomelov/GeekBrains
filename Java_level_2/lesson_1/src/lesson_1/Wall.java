package lesson_1;

public class Wall extends Obstacle {

    private final String name;
    private final int height;

    Wall(String name) {
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
