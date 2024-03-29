package ba.unsa.etf.rpr.controllers.EmployeePanel.JobsTab;

import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.controllers.EmployeePanel.EmployeePanelController;
import ba.unsa.etf.rpr.controllers.TitlesConfiguration;
import ba.unsa.etf.rpr.controllers.cell_value_factories.JobCellValueFactory;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;
import ba.unsa.etf.rpr.exceptions.CompanyException;
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
import java.util.Objects;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * Controller for Job tab component of the main panel.
 */
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
    void initialize() throws SQLException, CompanyException {
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

    /**
     * Updating jobs table when returning from modal window.
     */
    @FXML
    public void refreshTable()  {
        try {
            jobsList = jobManager.getAllJobs();
            JobCellValueFactory.setEmployeesPerJobMap();
            jobsTable.setItems(FXCollections.observableArrayList(jobsList));
            jobsTable.refresh();
        } catch (CompanyException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Opens form for adding new job.
     * @param actionEvent
     */
    @FXML
    public void addJob(ActionEvent actionEvent) {
        openForm(actionEvent, new AddJobController(this), "/fxml/EmployeePanel/JobsTab/AddJob.fxml", "addJob" );
    }

    /**
     * Opens form for ediitng job details.
     * @param actionEvent
     */
    @FXML
    public void editJob(ActionEvent actionEvent) {
        openForm(actionEvent, new EditJobController(jobsTable.getSelectionModel().getSelectedItem(), this), "/fxml/EmployeePanel/JobsTab/EditJob.fxml", "editJob" );
    }

    /**
     * Opens form for confirming deletion of the job.
     * @param actionEvent
     */
    @FXML
    public void removeJob(ActionEvent actionEvent) {
        openForm(actionEvent, new RemoveJobController(jobsTable.getSelectionModel().getSelectedItem(), this), "/fxml/EmployeePanel/JobsTab/RemoveJob.fxml", "removeJob" );
    }

    /**
     * General way of opening forms.
     * @param actionEvent
     * @param controller
     * @param fxmlFile
     * @param title
     */
    public void openForm(ActionEvent actionEvent, Object controller, String fxmlFile, String title){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(TitlesConfiguration.getProperty(title));
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
