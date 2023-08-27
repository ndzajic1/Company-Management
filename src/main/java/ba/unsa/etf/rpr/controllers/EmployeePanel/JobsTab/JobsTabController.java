package ba.unsa.etf.rpr.controllers.EmployeePanel.JobsTab;

import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.controllers.EmployeePanel.DepartmentsTab.AddDepartmentController;
import ba.unsa.etf.rpr.controllers.EmployeePanel.EmployeePanelController;
import ba.unsa.etf.rpr.controllers.JobCellValueFactory;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

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

    public void addJob(ActionEvent actionEvent) throws SQLException {
        openForm(actionEvent, new AddJobController(this), "/fxml/EmployeePanel/JobsTab/AddJob.fxml", "Add job" );
    }

    public void editJob(ActionEvent actionEvent) throws SQLException {
        openForm(actionEvent, new EditJobController(jobsTable.getSelectionModel().getSelectedItem(), this), "/fxml/EmployeePanel/JobsTab/EditJob.fxml", "Edit job" );
    }

    public void removeJob(ActionEvent actionEvent) {
        openForm(actionEvent, new RemoveJobController(jobsTable.getSelectionModel().getSelectedItem(), this), "/fxml/EmployeePanel/JobsTab/RemoveJob.fxml", "Remove job" );
    }

    public void openForm(ActionEvent actionEvent, Object controller, String fxmlFile, String title){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/img/logo.jpeg")).toExternalForm()));
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
