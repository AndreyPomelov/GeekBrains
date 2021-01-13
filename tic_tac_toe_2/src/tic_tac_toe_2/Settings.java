package tic_tac_toe_2;

public class Settings {

    private int gameMode;
    private int fieldSize;
    private int winLength;
    private String player1Name;
    private String player2Name;

    public Settings(int gameMode, int fieldSize, int winLength, String player1Name, String player2Name) {
        this.gameMode = gameMode;
        this.fieldSize = fieldSize;
        this.winLength = winLength;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
    }

    public int getWinLength() {
        return winLength;
    }

    public void setWinLength(int winLength) {
        this.winLength = winLength;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }
}
