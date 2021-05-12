import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Package;
import model.PackageType;

import java.io.File;
import java.io.IOException;

// Это класс-хэндлер сетевого соединения
public class Handler extends SimpleChannelInboundHandler<Package> {

    protected static MainController mainController;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Package pack) throws Exception {

        if (pack.getPackageType().equals(PackageType.SHOW_FILES)) {
            Platform.runLater(() -> {
                for (String s : pack.getFilesList()) {
                    if (s.endsWith("(folder)")) {
                        mainController.serverFilesList.getItems().add(s);
                    }
                }
                for (String s : pack.getFilesList()) {
                    if (!s.endsWith("(folder)")) {
                        mainController.serverFilesList.getItems().add(s);
                    }
                }
            });
        }

        if (pack.getPackageType().equals(PackageType.AUTH_OK)) {
            Parent parent = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
            Platform.runLater(() -> {
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.setResizable(false);
                stage.setTitle("Claudia");
                stage.show();
            });
            AuthController.closeWindow();
        }

        if (pack.getPackageType().equals(PackageType.AUTH_FAIL)) {
            Parent parent = FXMLLoader.load(getClass().getResource("authDeniedWindow.fxml"));
            Platform.runLater(() -> {
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.setResizable(false);
                stage.setTitle("Claudia");
                stage.show();
            });
        }

        if (pack.getPackageType().equals(PackageType.REG_OK)) {
            Parent parent = FXMLLoader.load(getClass().getResource("regOKWindow.fxml"));
            Platform.runLater(() -> {
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.setResizable(false);
                stage.setTitle("Claudia");
                stage.show();
            });
        }

        if (pack.getPackageType().equals(PackageType.REG_FAIL)) {
            Parent parent = FXMLLoader.load(getClass().getResource("regFailWindow.fxml"));
            Platform.runLater(() -> {
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.setResizable(false);
                stage.setTitle("Claudia");
                stage.show();
            });
        }
    }
}
