package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.EmployeeManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

public class LoginController {

    private EmployeeManager employeeManager = new EmployeeManager();
    public Button login;
    public TextField user;
    public PasswordField password;

    @FXML
    public void initialize(){
        user.setText("Type username");
    }

    public void login(ActionEvent actionEvent) {
        user.setText("There u go");
    }
}
