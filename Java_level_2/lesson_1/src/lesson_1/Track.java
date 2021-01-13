package lesson_1;

public class Track {

    private final String name;
    private final int length;

    public Track(String name) {
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
