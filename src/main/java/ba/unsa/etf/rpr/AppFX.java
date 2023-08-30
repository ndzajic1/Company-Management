package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.domain.LoggableUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Objects;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class AppFX extends Application {


    public static void main(String[] args) throws SQLException, IOException, NoSuchAlgorithmException {
        System.out.println(LoggableUser.hashedPassword("12345678"));
        launch(args);

    }

   @Override
    public void start(Stage stage) throws Exception {
        Parent p = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Login.fxml")));
        stage.setTitle("Company");
        stage.setScene(new Scene(p, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/img/logo.jpeg")).toExternalForm()));
        stage.setResizable(false);
        stage.show();
    }

}
