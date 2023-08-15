package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class DepartmentCellValueFactory implements Callback<TableColumn.CellDataFeatures<Department, String>, String> {

    // mora ovo bolje
    private DepartmentManager departmentManager = new DepartmentManager();
    private String field;

    public DepartmentCellValueFactory(String e){
        field = e;
    }

    @Override
    public String call(TableColumn.CellDataFeatures<Department, String> features) {
        Department e = features.getValue();
        return switch (field) {
            case "Department" -> e.getName();
            case "Location" -> e.getLocation();
            case "Manager" -> e.getManager().getFirstName() + " " + e.getManager().getLastName();
            case "Employees" -> Integer.toString(17);
            default -> throw new RuntimeException();
        };
    }
}
