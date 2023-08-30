package ba.unsa.etf.rpr.controllers.EmployeePanel.JobsTab;

import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.domain.Job;
import ba.unsa.etf.rpr.exceptions.CompanyException;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Controller for Edit job window.
 */
public class EditJobController {
    private JobManager jobManager = new JobManager();
    private JobsTabController mainController;
    private Job job;


    public Button editJob;

    public TextField title;
    private SimpleStringProperty titleProperty;

    public TextField minSalary;
    private SimpleStringProperty minSalaryProperty;

    public TextField maxSalary;
    private SimpleStringProperty maxSalaryProperty;


    public EditJobController(Job j, Object o) {
        this.job = j;
        mainController = (JobsTabController) o;
        titleProperty = new SimpleStringProperty(j.getTitle());
        minSalaryProperty = new SimpleStringProperty(Double.toString(j.getMinSalary()));
        maxSalaryProperty = new SimpleStringProperty(Double.toString(j.getMaxSalary()));

    }

    @FXML
    public void initialize(){
        title.textProperty().bindBidirectional(titleProperty);
        minSalary.textProperty().bindBidirectional(minSalaryProperty);
        maxSalary.textProperty().bindBidirectional(maxSalaryProperty);
    }

    /**
     * Confirm new data.
     * @param actionEvent
     */
    @FXML
    public void editJob(ActionEvent actionEvent) {
        try {
            job.setTitle(titleProperty.getValue());
            job.setMinSalary(Double.parseDouble(minSalaryProperty.getValue()));
            job.setMaxSalary(Double.parseDouble(maxSalaryProperty.getValue()));

            jobManager.updateJob(job);

            mainController.refreshTable();

            Node n = (Node) actionEvent.getSource();
            Stage currStage = (Stage) n.getScene().getWindow();
            currStage.close();
        } catch(NumberFormatException|CompanyException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Exit form.
     * @param actionEvent
     */
    @FXML
    public void cancel(ActionEvent actionEvent){
        Node n = (Node) actionEvent.getSource();
        Stage currStage = (Stage) n.getScene().getWindow();
        currStage.close();
    }
}
