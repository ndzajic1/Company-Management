package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.event.ActionEvent;

public class RemoveEmployee {

    private Employee employee;
    private EmployeeManager employeeManager = new EmployeeManager();

    public RemoveEmployee(Employee e){
        this.employee = e;
    }

    public void removeEmployee(ActionEvent actionEvent) {
        employeeManager.deleteEmployee(employee.getId());
    }
}
