package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.sql.SQLException;

public class RemoveEmployeeController {

    private Employee employee;
    private EmployeeManager employeeManager = new EmployeeManager();
    private EmployeesTabController mainController;

    public RemoveEmployeeController(Employee e, Object mainController){
        this.employee = e;
        this.mainController = (EmployeesTabController) mainController;
    }

    @FXML
    public void removeEmployee(ActionEvent actionEvent) throws SQLException {
        employeeManager.deleteEmployee(employee.getId());
        mainController.refreshTable();
        Node n = (Node) actionEvent.getSource();
        Stage currStage = (Stage) n.getScene().getWindow();
        currStage.close();
    }
}
