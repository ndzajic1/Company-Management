package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Employee;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class EmployeeCellValueFactory implements Callback<TableColumn.CellDataFeatures<Employee, String>, String> {
    private String field;

    public EmployeeCellValueFactory(String e){
        field = e;
    }

    @Override
    public String call(TableColumn.CellDataFeatures<Employee, String> features) {
        Employee e = features.getValue();
        return switch (field) {
            case "First Name" -> e.getFirstName();
            case "Last Name" -> e.getLastName();
            case "Date Hired" -> e.getHireDate().toString();
            case "Department" -> e.getDepartment().getName();
            case "Job" -> e.getJob().getTitle();
            case "Salary" -> Double.toString(e.getSalary());
            default -> throw new RuntimeException();
        };
    }
}
