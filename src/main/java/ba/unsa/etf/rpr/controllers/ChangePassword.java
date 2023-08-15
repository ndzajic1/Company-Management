package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.LoggableUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.security.NoSuchAlgorithmException;

public class ChangePassword {

    private EmployeeManager employeeManager;
    public TextField currentPassword;
    public TextField newPassword;

    public Label status;

    private LoggableUser loggedUser;

    public ChangePassword(LoggableUser user){
        this.loggedUser = user;
    }
    @FXML
    void initialize(){
        status.setText("");
    }
    public void changePassword(ActionEvent actionEvent) throws NoSuchAlgorithmException {
        String hashedPassword = LoggableUser.hashedPassword(currentPassword.getText());
        if(hashedPassword.equals(loggedUser.getPasswordHash())){
            loggedUser.setPasswordHash(LoggableUser.hashedPassword(newPassword.getText()));
            employeeManager.updateEmployee((Employee) loggedUser);
            // close the window
        }
        else{
            status.setText("Invalid password.");
        }

    }
}
