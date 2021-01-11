package geekbrains.tic_tac_toe_3;

public class GameLogic {

    private final GameWindow gameWindow;
    private Player player1;
    private Player player2;
    private char emptyDot = '.';
    private Settings settings;
    static boolean whoseTurn = true; // Чей ход. true - 1 игрок, false - 2 игрок
    static boolean isTurnSuccess = false;
    static boolean isGameOver = false;
    private int targetCellX;
    private int targetCellY;
    private Field field;

    public GameLogic(Player player1, Player player2, Settings settings, GameWindow gameWindow, Field field) {
        this.player1 = player1;
        this.player2 = player2;
        this.settings = settings;
        this.gameWindow = gameWindow;
        this.field = field;
    }

    void move(int cellX, int cellY) {
        if (isGameOver) return;
        targetCellX = cellX;
        targetCellY = cellY;
        isTurnSuccess = false;
        if (whoseTurn) player1.move(targetCellX, targetCellY);
        if (!whoseTurn) player2.move(targetCellX, targetCellY);
        field.repaint();
        checks();
        if (!isTurnSuccess) return;
        if (!isGameOver) setText();
        whoseTurn = !whoseTurn;
    }

    void checks() {
        if (isWin(player1.getDot())) {
            isGameOver = true;
            gameWindow.gameOver(player1, true);
        }
        if (isWin(player2.getDot())){
            isGameOver = true;
            gameWindow.gameOver(player2, true);
        }
        if (isFieldFull()){
            isGameOver = true;
            gameWindow.gameOver(new HumanPlayer("", '0'), false);
        }
    }

    void setText() {
        if (whoseTurn) {
            if (player2.isAI) gameWindow.setText("   По щелчку мыши сходит " + player2.getName() + ".");
            else gameWindow.setText("   Ходит " + player2.getName() + ". Выбери свободную ячейку.");
        }
        else {
            if (player1.isAI) gameWindow.setText("   По щелчку мыши сходит " + player1.getName() + ".");
            else gameWindow.setText("   Ходит " + player1.getName() + ". Выбери свободную ячейку.");
        }
    }

    boolean isCoordCorrect(int x, int y) {
        return (x >= 0 && x < settings.getFieldSize() && y >= 0 && y < settings.getFieldSize() && Field.map[x][y] == emptyDot);
    }

    boolean isWin(char dot) {
        for (int i = 0; i < settings.getFieldSize(); i++) {
            for (int j = 0; j < settings.getFieldSize(); j++) {
                if (isUpDiagValid(j, i)) { // Смотрим, целесообразно ли проверять диагональ
                    if (checkUpDiag(j, i, dot)) return true; // Если да, проверяем диагональ
                } // Далее - аналогично со второй диагональю, горизонталью и вертикалью
                if (isDownDiagValid(j, i)) {
                    if (checkDownDiag(j, i, dot)) return true;
                }
                if (isHorizValid(j)) {
                    if (checkHoriz(j, i, dot)) return true;
                }
                if (isVertValid(i)) {
                    if (checkVert(j, i, dot)) return true;
                }
            }
        }
        return false;
    }

    boolean isFieldFull() {
        for (int i = 0; i < settings.getFieldSize(); i++) {
            for (int j = 0; j < settings.getFieldSize(); j++) {
                if (Field.map[j][i] == emptyDot) return false;
            }
        }
        return true;
    }

    // Метод, проверяющий целесообразность проверки по диагонали вверх
    private boolean isUpDiagValid(int x, int y) {
        return (x <= settings.getFieldSize() - settings.getWinLength() && y >= settings.getWinLength() - 1);
    }

    // Метод, проверяющий диагональ вверх
    private boolean checkUpDiag(int x, int y, char dot) {
        int count = 0;
        for (int i = 0; i < settings.getWinLength(); i++) {
            if (Field.map[x++][y--] == dot) count++;
        }
        return count == settings.getWinLength();
    }

    // Метод, проверяющий целесообразность проверки по диагонали вниз
    private boolean isDownDiagValid(int x, int y) {
        return (x <= settings.getFieldSize() - settings.getWinLength() && y <= settings.getFieldSize() - settings.getWinLength());
    }

    // Метод, проверяющий диагональ вниз
    private boolean checkDownDiag(int x, int y, char dot) {
        int count = 0;
        for (int i = 0; i < settings.getWinLength(); i++) {
            if (Field.map[x++][y++] == dot) count++;
        }
        return count == settings.getWinLength();
    }

    // Метод, проверяющий целесообразность проверки по горизонтали
    private boolean isHorizValid(int x) {
        return (x <= settings.getFieldSize() - settings.getWinLength());
    }

    // Метод, проверяющий горизонталь
    private boolean checkHoriz(int x, int y, char dot) {
        int count = 0;
        for (int i = 0; i < settings.getWinLength(); i++) {
            if (Field.map[x++][y] == dot) count++;
        }
        return count == settings.getWinLength();
    }

    // Метод, проверяющий целесообразность проверки по вертикали
    private boolean isVertValid(int y) {
        return (y <= settings.getFieldSize() - settings.getWinLength());
    }

    // Метод, проверяющий вертикаль
    private boolean checkVert(int x, int y, char dot) {
        int count = 0;
        for (int i = 0; i < settings.getWinLength(); i++) {
            if (Field.map[x][y++] == dot) count++;
        }
        return count == settings.getWinLength();
    }

    void winOrBlockMove (char selfDot) {
        for (int i = 0; i < settings.getFieldSize(); i++) {
            for (int j = 0; j < settings.getFieldSize(); j++) {
                if (Field.map[j][i] == emptyDot) {
                    Field.map[j][i] = player1.getDot();
                    if (isWin(player1.getDot())) {
                        Field.map[j][i] = selfDot;
                        GameLogic.isTurnSuccess = true;
                        return;
                    }
                    else Field.map[j][i] = emptyDot;
                }
                if (Field.map[j][i] == emptyDot) {
                    Field.map[j][i] = player2.getDot();
                    if (isWin(player2.getDot())) {
                        Field.map[j][i] = selfDot;
                        GameLogic.isTurnSuccess = true;
                        return;
                    }
                    else Field.map[j][i] = emptyDot;
                }
            }
        }
    }

    void randomMove (char selfDot) {
        do {
            targetCellX = (int) (Math.random() * settings.getFieldSize());
            targetCellY = (int) (Math.random() * settings.getFieldSize());
        } while (!isCoordCorrect(targetCellX, targetCellY));
        Field.map[targetCellX][targetCellY] = selfDot;
        GameLogic.isTurnSuccess = true;
    }

}
