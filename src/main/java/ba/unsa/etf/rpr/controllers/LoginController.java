package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.LoggableUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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
        System.out.println("Password: " + passwordHash);
        Employee user = employeeManager.getEmployeeByUsername(username.getText());
        if(user.getPasswordHash().equals(passwordHash)){
            // open new window
        }
        else{
            status.setText("Invalid login credentials.");
        }
    }
}
