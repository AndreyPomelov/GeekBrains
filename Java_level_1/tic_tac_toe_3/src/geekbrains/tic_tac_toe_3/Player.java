package geekbrains.tic_tac_toe_3;

public abstract class Player {

    private String name;
    private char dot;

    public Player(String name, char dot) {
        this.name = name;
        this.dot = dot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getDot() {
        return dot;
    }

    public void setDot(char dot) {
        this.dot = dot;
    }
}
