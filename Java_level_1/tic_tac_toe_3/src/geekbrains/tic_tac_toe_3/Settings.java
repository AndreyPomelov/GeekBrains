package geekbrains.tic_tac_toe_3;

public class Settings {

    private int gameMode;
    private String player1Name;
    private String player2Name;
    private int fieldSize;
    private int winLength;

    public Settings(int gameMode, String player1Name, String player2Name, int fieldSize, int winLength) {
        this.gameMode = gameMode;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.fieldSize = fieldSize;
        this.winLength = winLength;
    }

    public int getGameMode() {
        return gameMode;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public int getWinLength() {
        return winLength;
    }
}
