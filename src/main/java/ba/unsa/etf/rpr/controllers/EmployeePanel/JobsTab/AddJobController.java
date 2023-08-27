package ba.unsa.etf.rpr.controllers.EmployeePanel.JobsTab;


import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.domain.Job;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddJobController {

    private JobManager jobManager = new JobManager();

    private JobsTabController mainController;
    @FXML
    private Button addButton;

    @FXML
    private TextField title;
    private SimpleStringProperty titleProperty;
    @FXML
    private TextField minSalary;
    private SimpleStringProperty minSalaryProperty;
    @FXML
    private TextField maxSalary;
    private SimpleStringProperty maxSalaryProperty;


    public AddJobController(Object o) throws SQLException {
        mainController = (JobsTabController) o;
        titleProperty = new SimpleStringProperty("");
        minSalaryProperty = new SimpleStringProperty("");
        maxSalaryProperty = new SimpleStringProperty("");

    }

    @FXML
    public void initialize(){
        title.textProperty().bindBidirectional(titleProperty);
        minSalary.textProperty().bindBidirectional(minSalaryProperty);
        maxSalary.textProperty().bindBidirectional(maxSalaryProperty);
    }

    @FXML
    public void addJob(ActionEvent actionEvent) throws SQLException {

        Job j = new Job();
        j.setTitle(titleProperty.getValue());
        j.setMinSalary(Double.parseDouble(minSalaryProperty.getValue()));
        j.setMaxSalary(Double.parseDouble(maxSalaryProperty.getValue()));

        jobManager.addNewJob(j);

        mainController.refreshTable();

        Node n = (Node) actionEvent.getSource();
        Stage currStage = (Stage) n.getScene().getWindow();
        currStage.close();
    }


}

