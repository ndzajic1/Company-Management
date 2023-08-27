package ba.unsa.etf.rpr.controllers.EmployeePanel.JobsTab;

import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.controllers.EmployeePanel.EmployeePanelController;
import ba.unsa.etf.rpr.controllers.JobCellValueFactory;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class JobsTabController {
    
    private JobManager jobManager = new JobManager();
    private EmployeeManager employeeManager = new EmployeeManager();

    private Employee employee;
    private List<Job> jobsList;

    @FXML
    private TableView<Job> jobsTable;

    @FXML
    private TableColumn<Job, String> titleCol;

    @FXML
    private TableColumn<Job, String> minSalaryCol;
    
    @FXML
    private TableColumn<Job, String> maxSalaryCol;

    @FXML
    private TableColumn<Job, String> numOfEmployeesCol;

    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;

    public JobsTabController(){

    }

    
    @FXML
    void initialize() throws SQLException {
        this.employee = EmployeePanelController.getUser();
        
        titleCol.setCellValueFactory(new JobCellValueFactory("Job Title"));
        minSalaryCol.setCellValueFactory(new JobCellValueFactory("Minimal Salary"));
        maxSalaryCol.setCellValueFactory(new JobCellValueFactory("Maximal Salary"));
        numOfEmployeesCol.setCellValueFactory(new JobCellValueFactory("Employees"));

        refreshTable();

        //disable buttons if not admin
        if(!employee.isAdmin()){
            addButton.setDisable(true);
            editButton.setDisable(true);
            removeButton.setDisable(true);
        }

    }

    void refreshTable() throws SQLException {
        jobsList = jobManager.getAllJobs();
        jobsTable.setItems(FXCollections.observableArrayList(jobsList));
        jobsTable.refresh();
    }

    public void addJob(ActionEvent actionEvent) {
    }

    public void editJob(ActionEvent actionEvent) {
    }

    public void removeJob(ActionEvent actionEvent) {
    }
}
