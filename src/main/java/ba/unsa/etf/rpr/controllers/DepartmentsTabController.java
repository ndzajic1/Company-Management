package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DepartmentsTabController {

    private DepartmentManager departmentManager = new DepartmentManager();
    private EmployeeManager employeeManager = new EmployeeManager();
    private Employee employee;

    private List<Department> departmentsList = new ArrayList<>();

    @FXML
    private TableView<Department> departmentsTable;

    @FXML
    private TableColumn<Department, String> deptNameCol;

    @FXML
    private TableColumn<Department, String> locationCol;

    @FXML
    private TableColumn<Department, String> managerCol;

    @FXML
    private TableColumn<Department, String> numOfEmployeesCol;

    public DepartmentsTabController(){


    }

    @FXML
    void initialize() throws SQLException {
        this.employee = EmployeePanel.getUser();
        departmentsList = departmentManager.getAllDepts();
        Map<Integer, Integer> employeesPerDept = new TreeMap<>();
        for(Department d : departmentsList){
            employeesPerDept.put(d.getId(),0);
        }
        for(Employee e : employeeManager.getAllEmployees()){
            int curr = employeesPerDept.get(e.getDepartment().getId());
            employeesPerDept.put(e.getDepartment().getId(), curr + 1);
        }
        deptNameCol.setCellValueFactory(new DepartmentCellValueFactory("Department"));
        locationCol.setCellValueFactory(new DepartmentCellValueFactory("Location"));
        managerCol.setCellValueFactory(new DepartmentCellValueFactory("Manager"));
        numOfEmployeesCol.setCellValueFactory(new DepartmentCellValueFactory(employeesPerDept));



        departmentsTable.setItems(FXCollections.observableArrayList(departmentsList));
        //disable buttons if not admin
    }

    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;

    @FXML
    void addDept(ActionEvent event) {
        // open window
    }

    @FXML
    void editDept(ActionEvent event) {
        // open window
    }

    @FXML
    void removeDept(ActionEvent event) {
        // open window
    }

}
