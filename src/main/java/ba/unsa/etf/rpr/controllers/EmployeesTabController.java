package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeesTabController {

    private EmployeeManager employeeManager = new EmployeeManager();
    private Employee employee;

    private List<Employee> employeeList = new ArrayList<>();

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Employee> employeesTable;

    @FXML
    private TableColumn<Employee, String> firstNameCol;

    @FXML
    private TableColumn<Employee, String> lastNameCol;

    @FXML
    private TableColumn<Employee, String> dateHiredCol;

    @FXML
    private TableColumn<Employee, String> deptNameCol;

    @FXML
    private TableColumn<Employee, String> jobTitleCol;

    @FXML
    private TableColumn<Employee, String> salaryCol;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button removeButton;

    public EmployeesTabController(){


    }

    @FXML
    void initialize() throws SQLException {

        this.employee = EmployeePanel.getUser();
        firstNameCol.setCellValueFactory(new EmployeeCellValueFactory("First Name"));
        lastNameCol.setCellValueFactory(new EmployeeCellValueFactory("Last Name"));
        dateHiredCol.setCellValueFactory(new EmployeeCellValueFactory("Date Hired"));
        deptNameCol.setCellValueFactory(new EmployeeCellValueFactory("Department"));
        jobTitleCol.setCellValueFactory(new EmployeeCellValueFactory("Job Title"));
        salaryCol.setCellValueFactory(new EmployeeCellValueFactory("Salary"));

        if(employee.isAdmin())
            employeeList = employeeManager.getAllEmployees();
        else{
            employeeList = employeeManager.getEmployeesFromDepartment(employee.getDepartment());
        }

        employeesTable.setItems(FXCollections.observableArrayList(employeeList));
        //disable buttons if not admin
    }

    @FXML
    void addEmployee(ActionEvent event) {
        // open new window
    }

    @FXML
    void editEmployee(ActionEvent event) {
        // open new window
    }

    @FXML
    void removeEmployee(ActionEvent event) {
        // open new window
    }

    @FXML
    void searchEmployees(ActionEvent event) {
        // refresh  the list
    }

}
