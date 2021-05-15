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
public class AuthController implements Initializable {

    public static AuthController authController;
    public TextField authLogin;
    public PasswordField authPass;
    public TextField regLogin;
    public PasswordField regPass;
    public PasswordField regPassConfirm;
    public Label regLabel;
    public Label authLabel;
    protected Client client;

    // Метод, отвечающий за авторизацию клиента на сервере
    public void authorisation(ActionEvent actionEvent) {
        // Проверка, введён ли логин
        if (authLogin.getText().length() == 0) {
            authLabel.setTextFill(Color.RED);
            authLabel.setText("Enter login");
            return;
        }

        // Проверка, введён ли пароль
        if (authPass.getText().length() == 0) {
            authLabel.setTextFill(Color.RED);
            authLabel.setText("Enter password");
            return;
        }

        // Создаём пакет для авторизации и передаём его на сервер
        Package pack = new Package(PackageType.AUTH);
        pack.setLogin(authLogin.getText());
        pack.setPassword(authPass.getText());
        client.write(pack);
    }

    // Метод, отвечающий за регистрацию клиента на сервере
    public void registration(ActionEvent actionEvent) {
        // Проверка, введён ли логин
        if (regLogin.getText().length() == 0) {
            regLabel.setTextFill(Color.RED);
            regLabel.setText("Enter login");
            return;
        }

        // Проверка, введён ли пароль
        if (regPass.getText().length() == 0) {
            regLabel.setTextFill(Color.RED);
            regLabel.setText("Enter password");
            return;
        }

        // Проверка, совпадают ли пароли
        if (!regPass.getText().equals(regPassConfirm.getText())) {
            regLabel.setTextFill(Color.RED);
            regLabel.setText("Passwords don't match");
            return;
        }

        // Создаём пакет для регистрации и отправляем его на сервер
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

    // Метод, закрывающий окно авторизации
    public static void closeWindow() {
        Platform.runLater(() -> {
            authController.authLabel.getScene().getWindow().hide();
        });
    }
}
