package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class Controller {
    public Label label;

    public void click(ActionEvent actionEvent) {
        int a = (int) (Math.random() * 100);
        label.setWrapText(true);
        label.setText("sfaddsfadsfsfds\nfffffffffffffffffffffffffffffffffff\nkjhksfhsfhhskfghsgfsffsdf" + a);
    }
}
