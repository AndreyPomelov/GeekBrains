package geekbrains.tic_tac_toe_3;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, char dot) {
        super(name, dot);
        isAI = false;
    }

    @Override
    public void move(int x, int y) {
        if (!gameLogic.isCoordCorrect(x, y)) return;
        Field.map[x][y] = getDot();
        GameLogic.isTurnSuccess = true;
    }
}
