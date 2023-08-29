package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.controllers.EmployeePanel.EmployeePanelController;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.LoggableUser;
import ba.unsa.etf.rpr.exceptions.CompanyException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class LoginController {

    private EmployeeManager employeeManager = new EmployeeManager();
    public Button login;
    public TextField username;
    public PasswordField password;
    public Label status;

    @FXML
    public void initialize(){
        username.setText("");
        password.setText("");
        status.setText("");
    }

    public void login(ActionEvent actionEvent) throws NoSuchAlgorithmException, SQLException {
        try {
        String passwordHash = LoggableUser.hashedPassword(password.getText());
        Employee user = employeeManager.getEmployeeByUsername(username.getText());
        System.out.println(user.toString());
        if (user.getPasswordHash().equals(passwordHash)) {
            openPanel(actionEvent, user);
            }
        else {
            status.setText("Invalid login credentials.");
        }
    } catch (CompanyException | IOException e) {
        e.printStackTrace();
        new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
    }
    }

    void openPanel(ActionEvent actionEvent, Employee user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EmployeePanel/EmployeePanel.fxml"));
        loader.setController(new EmployeePanelController(user));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setTitle("Employee Panel");
        stage.initStyle(StageStyle.UTILITY);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/img/logo.jpeg")).toExternalForm()));
        stage.setResizable(false);
        stage.show();
        Node n = (Node) actionEvent.getSource();
        Stage currStage = (Stage) n.getScene().getWindow();
        currStage.close();
    }
}
