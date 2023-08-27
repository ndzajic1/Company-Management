package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DepartmentCellValueFactory<T> implements Callback<TableColumn.CellDataFeatures<Department, String>, ObservableValue<String>> {

    private final EmployeeManager employeeManager = new EmployeeManager();
    private final DepartmentManager departmentManager = new DepartmentManager();
    private final String field;
    private static Map<Integer, Integer> employeesPerDept = null;

    public DepartmentCellValueFactory(String e) throws SQLException {
        field = e;
        if(employeesPerDept == null){
            setEmployeesPerDeptMap();
        }
    }

    public void setEmployeesPerDeptMap() throws SQLException {
        employeesPerDept = new TreeMap<>();
        for(Department d : departmentManager.getAllDepts()){
            employeesPerDept.put(d.getId(),0);
        }
        for(Employee e : employeeManager.getAllEmployees()){
            Integer curr = employeesPerDept.get(e.getDepartment().getId()); // != null ? employeesPerDept.get(e.getDepartment().getId()) : 0;
            employeesPerDept.put(e.getDepartment().getId(), curr + 1);
        }
    }


    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Department, String> features) {
        Department e = features.getValue();
        return switch (field) {
            case "Department" -> new SimpleStringProperty(e.getName());
            case "Location" -> new SimpleStringProperty(e.getLocation());
            case "Manager" -> new SimpleStringProperty(e.getManager().getFirstName() + " " + e.getManager().getLastName());
            case "Employees" -> new SimpleStringProperty(Integer.toString(employeesPerDept.get(e.getId())));
            default -> throw new RuntimeException();
        };
    }
}
