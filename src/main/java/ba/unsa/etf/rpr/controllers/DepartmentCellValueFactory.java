package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.util.Map;

public class DepartmentCellValueFactory<T> implements Callback<TableColumn.CellDataFeatures<Department, String>, ObservableValue<String>> {

    // mora ovo bolje
    private DepartmentManager departmentManager = new DepartmentManager();
    private String field;
    private Map<Integer, Integer> numOfEmployeesPerDept;

    public DepartmentCellValueFactory(String e){
        field = e;
    }

    public DepartmentCellValueFactory(Map<Integer, Integer> map){
        field = "Employees";
        numOfEmployeesPerDept = map;
    }

    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Department, String> features) {
        Department e = features.getValue();
        return switch (field) {
            case "Department" -> new SimpleStringProperty(e.getName());
            case "Location" -> new SimpleStringProperty(e.getLocation());
            case "Manager" -> new SimpleStringProperty(e.getManager().getFirstName() + " " + e.getManager().getLastName());
            case "Employees" -> new SimpleStringProperty(Integer.toString(numOfEmployeesPerDept.get(e.getId())));
            default -> throw new RuntimeException();
        };
    }
}
