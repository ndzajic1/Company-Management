package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.LoggableUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeTabController {

    private Employee user;

    @FXML
    private Button changePasswordButton;

    @FXML
    private Label deptManager;

    @FXML
    private Label deptName;

    @FXML
    private Label employeeName;


    public HomeTabController(){

    }

    @FXML
    public void initialize(){
        user = EmployeePanel.getUser();
        employeeName.setText(user.getFirstName() + " " + user.getLastName());
        System.out.println(employeeName.getText());
        deptName.setText(user.getDepartment().getName());
        Employee mngr = user.getDepartment().getManager();
        deptManager.setText(mngr.getFirstName() + " " + mngr.getLastName());
    }

    @FXML
    void changePassword(ActionEvent event) {
        // open changing password
    }

}