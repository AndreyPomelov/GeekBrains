import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class RegOKController {
    public Button button;

    public void closeButton(ActionEvent actionEvent) {
        button.getParent().getScene().getWindow().hide();
    }
}