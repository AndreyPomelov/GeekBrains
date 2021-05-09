import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.Package;
import model.PackageType;

import java.net.URL;
import java.util.ResourceBundle;

// Это класс-контроллер для окна регистрации и авторизации клиента
// Методы ещё не доделал, по факту никакого обмена информацией
// с сервером ещё не происходит на данном этапе разработки.
public class AuthController implements Initializable {

    public static AuthController authController;
    public TextField authLogin;
    public PasswordField authPass;
    public TextField regLogin;
    public PasswordField regPass;
    public PasswordField regPassConfirm;
    public Label regLabel;
    public Label authLabel;
    private Client client;

    public void authorisation(ActionEvent actionEvent) {
        if (authLogin.getText().length() == 0) {
            authLabel.setTextFill(Color.RED);
            authLabel.setText("Enter login");
            return;
        }

        if (authPass.getText().length() == 0) {
            authLabel.setTextFill(Color.RED);
            authLabel.setText("Enter password");
            return;
        }

        Package pack = new Package(PackageType.AUTH);
        pack.setLogin(authLogin.getText());
        pack.setPassword(authPass.getText());
        client.write(pack);
    }

    public void registration(ActionEvent actionEvent) {
        if (regLogin.getText().length() == 0) {
            regLabel.setTextFill(Color.RED);
            regLabel.setText("Enter login");
            return;
        }

        if (regPass.getText().length() == 0) {
            regLabel.setTextFill(Color.RED);
            regLabel.setText("Enter password");
            return;
        }

        if (!regPass.getText().equals(regPassConfirm.getText())) {
            regLabel.setTextFill(Color.RED);
            regLabel.setText("Passwords don't match");
            return;
        }

        Package pack = new Package(PackageType.REG);
        pack.setLogin(regLogin.getText());
        pack.setPassword(regPass.getText());
        client.write(pack);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        client = new Client();
        authController = this;
    }

    public static void closeWindow() {
        Platform.runLater(() -> {
            authController.authLabel.getScene().getWindow().hide();
        });
    }
}
