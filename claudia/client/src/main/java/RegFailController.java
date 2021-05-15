import javafx.event.ActionEvent;
import javafx.scene.control.Button;

// Класс-контроллер всплывающего информационного окна
public class RegFailController {
    public Button button;

    public void closeButton(ActionEvent actionEvent) {
        button.getParent().getScene().getWindow().hide();
    }
}
