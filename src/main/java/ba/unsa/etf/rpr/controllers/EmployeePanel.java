package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.LoggableUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;

public class EmployeePanel {

    private Employee user;

    //tabs
    public Tab homeTab;
    public Tab employeesTab;
    public Tab departmentsTab;
    public Tab jobsTab;


    //home tab controls
    public Label employeeName;
    public Label deptName;
    public Label deptManager;

    public EmployeePanel(Employee user){

        this.user = user;
    }

    public void searchEmployees(ActionEvent actionEvent) {


    }

    private void homeTabInitialize(){
        employeeName.setText(user.getFirstName() + " " + user.getLastName());
        deptName.setText(user.getDepartment().getName());
        Employee mngr = user.getDepartment().getManager();
        deptManager.setText(mngr.getFirstName() + " " + mngr.getLastName());
        // listener to change password
    }
    @FXML
    void  initialize(){
        homeTabInitialize();
        // roles simul
        if(!user.isAdmin() || !user.getDepartment().getManager().equals(user))
            employeesTab.setDisable(true);
    }

    public void changePassword(ActionEvent actionEvent) {

    }
}
