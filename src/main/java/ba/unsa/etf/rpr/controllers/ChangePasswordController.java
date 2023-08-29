package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.LoggableUser;
import ba.unsa.etf.rpr.exceptions.CompanyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.security.NoSuchAlgorithmException;

public class ChangePasswordController {

    private EmployeeManager employeeManager = new EmployeeManager();

    @FXML
    private PasswordField currentPassword;
    @FXML
    private PasswordField newPassword;

    @FXML
    private Label status;

    @FXML
    private Button cancelButton;

    private LoggableUser loggedUser;

    public ChangePasswordController(LoggableUser user){
        this.loggedUser = user;
    }
    @FXML
    void initialize(){

        status.setText("");
    }

    @FXML
    public void changePassword(ActionEvent actionEvent) throws NoSuchAlgorithmException {
        try {
            String hashedPassword = LoggableUser.hashedPassword(currentPassword.getText());
            if (hashedPassword.equals(loggedUser.getPasswordHash())) {
                loggedUser.setPasswordHash(LoggableUser.hashedPassword(newPassword.getText()));
                employeeManager.updateEmployee((Employee) loggedUser);
                // close the window
                Node n = (Node) actionEvent.getSource();
                Stage currStage = (Stage) n.getScene().getWindow();
                currStage.close();
            } else {
                status.setText("Invalid password.");
            }
        } catch (CompanyException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }

    }
    @FXML
    void cancel(ActionEvent actionEvent){
        Node n = (Node) actionEvent.getSource();
        Stage currStage = (Stage) n.getScene().getWindow();
        currStage.close();
    }
}
