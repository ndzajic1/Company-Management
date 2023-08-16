package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DepartmentsTabController {

    @FXML
    private Button addButton;

    @FXML
    private TableView<?> departmentsTable;

    @FXML
    private TableColumn<?, ?> deptNameCol;

    @FXML
    private Button editButton;

    @FXML
    private TableColumn<?, ?> locationCol;

    @FXML
    private TableColumn<?, ?> managerCol;

    @FXML
    private TableColumn<?, ?> numOfEmployeesCol;

    @FXML
    private Button removeButton;

    @FXML
    void addDept(ActionEvent event) {

    }

    @FXML
    void editDept(ActionEvent event) {

    }

    @FXML
    void removeDept(ActionEvent event) {

    }

}
