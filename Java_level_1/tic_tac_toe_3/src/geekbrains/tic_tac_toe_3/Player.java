package geekbrains.tic_tac_toe_3;

public abstract class Player {

    private String name;
    private char dot;
    boolean isAI;
    GameLogic gameLogic;

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

    public abstract void move(int x, int y);

    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }
}
