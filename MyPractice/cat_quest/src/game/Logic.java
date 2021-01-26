package game;

public class Logic {

    private static Controller controller;
    private static StartWindowController startWindowController;

    public static void startNewGame() {

    }

    public static Controller getController() {
        return controller;
    }

    public static void setController(Controller controller) {
        Logic.controller = controller;
    }

    public static StartWindowController getStartWindowController() {
        return startWindowController;
    }

    public static void setStartWindowController(StartWindowController startWindowController) {
        Logic.startWindowController = startWindowController;
    }
}
