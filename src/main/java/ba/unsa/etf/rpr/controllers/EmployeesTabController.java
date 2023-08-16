package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EmployeesTabController {

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<?, ?> dateHiredCol;

    @FXML
    private TableColumn<?, ?> deptNameCol;

    @FXML
    private Button editButton;

    @FXML
    private TableView<?> employeesTable;

    @FXML
    private TableColumn<?, ?> firstNameCol;

    @FXML
    private TableColumn<?, ?> jobTitleCol;

    @FXML
    private TableColumn<?, ?> lastNameCol;

    @FXML
    private Button removeButton;

    @FXML
    private TableColumn<?, ?> salaryCol;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    void addEmployee(ActionEvent event) {

    }

    @FXML
    void editEmployee(ActionEvent event) {

    }

    @FXML
    void removeEmployee(ActionEvent event) {

    }

    @FXML
    void searchEmployees(ActionEvent event) {

    }

}
