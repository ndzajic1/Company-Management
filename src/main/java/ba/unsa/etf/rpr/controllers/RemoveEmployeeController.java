package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.event.ActionEvent;

public class RemoveEmployeeController {

    private Employee employee;
    private EmployeeManager employeeManager = new EmployeeManager();

    public RemoveEmployeeController(Employee e){
        this.employee = e;
    }

    public void removeEmployee(ActionEvent actionEvent) {
        employeeManager.deleteEmployee(employee.getId());
    }
}
