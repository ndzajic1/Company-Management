package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.util.Map;

public class DepartmentCellValueFactory<T> implements Callback<TableColumn.CellDataFeatures<Department, String>, String> {

    // mora ovo bolje
    private DepartmentManager departmentManager = new DepartmentManager();
    private String field;
    private Map<Department, Integer> numOfEmployeesPerDept;

    public DepartmentCellValueFactory(String e){
        field = e;
    }

    public DepartmentCellValueFactory(Map<Department, Integer> map){
        field = "Employees";
        numOfEmployeesPerDept = map;
    }

    @Override
    public String call(TableColumn.CellDataFeatures<Department, String> features) {
        Department e = features.getValue();
        return switch (field) {
            case "Department" -> e.getName();
            case "Location" -> e.getLocation();
            case "Manager" -> e.getManager().getFirstName() + " " + e.getManager().getLastName();
            case "Employees" -> Integer.toString(numOfEmployeesPerDept.get(e));
            default -> throw new RuntimeException();
        };
    }
}
