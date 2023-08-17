package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Employee;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

    public class EmployeeCellValueFactory<T> implements Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>> {
    private String field;

    public EmployeeCellValueFactory(String e){
        field = e;
    }

    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Employee, String> features) {
        Employee e = features.getValue();
        return switch (field) {
            case "First Name" -> new SimpleStringProperty(e.getFirstName());
            case "Last Name" -> new SimpleStringProperty(e.getLastName());
            case "Date Hired" -> new SimpleStringProperty(e.getHireDate().toString());
            case "Department" -> new SimpleStringProperty(e.getDepartment().getName());
            case "Job Title" -> new SimpleStringProperty(e.getJob().getTitle());
            case "Salary" -> new SimpleStringProperty(Double.toString(e.getSalary()));
            default -> throw new RuntimeException();
        };
    }
}
