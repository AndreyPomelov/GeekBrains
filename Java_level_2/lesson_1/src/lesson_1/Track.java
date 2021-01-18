package lesson_1;

public class Track extends Obstacle {

    private final String name;
    private final int length;

    Track(String name) {
        this.name = name;
        length = (int) (Math.random() * 21) + 90;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }
}
