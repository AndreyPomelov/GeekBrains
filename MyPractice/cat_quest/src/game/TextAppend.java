package game;

public class TextAppend implements Runnable {

    public static Controller controller;
    public static String message;

    @Override
    public void run() {
        controller.mainTextArea.appendText(message + "\n");
    }
}
