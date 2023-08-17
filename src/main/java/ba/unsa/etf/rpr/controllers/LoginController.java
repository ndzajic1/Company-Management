package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.LoggableUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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
        String passwordHash = LoggableUser.hashedPassword(password.getText());
        System.out.println("Text: " + password.getText());
        System.out.println("PasswordHash: " + passwordHash);
        Employee user = employeeManager.getEmployeeByUsername(username.getText());
        System.out.println("Correct: " + user.getPasswordHash());
        if(user.getPasswordHash().equals(passwordHash)){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EmployeePanel/EmployeePanel.fxml"));
                loader.setController(new EmployeePanel(user));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage.setTitle("Employee Panel");
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
                new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
            }
        }
        else{
            status.setText("Invalid login credentials.");
        }
    }
}
