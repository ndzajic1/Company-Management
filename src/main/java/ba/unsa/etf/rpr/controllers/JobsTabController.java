package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class JobsTabController {

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private TableView<?> jobsTable;

    @FXML
    private TableColumn<?, ?> maxSalaryCol;

    @FXML
    private TableColumn<?, ?> minSalaryCol;

    @FXML
    private TableColumn<?, ?> numOfEmployeesCol;

    @FXML
    private Button removeButton;

    @FXML
    private TableColumn<?, ?> titleCol;

}
