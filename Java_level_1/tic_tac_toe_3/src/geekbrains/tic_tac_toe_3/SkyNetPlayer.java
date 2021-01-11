package geekbrains.tic_tac_toe_3;

public class SkyNetPlayer extends Player {

    public SkyNetPlayer(String name, char dot) {
        super("SkyNet", dot);
        isAI = true;
    }

    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public void move(int x, int y) {
        gameLogic.winOrBlockMove(getDot());
        if (GameLogic.isTurnSuccess) return;
        gameLogic.randomMove(getDot());
    }
}
