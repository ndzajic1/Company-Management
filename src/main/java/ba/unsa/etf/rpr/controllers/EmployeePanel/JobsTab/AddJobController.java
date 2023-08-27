package ba.unsa.etf.rpr.controllers.EmployeePanel.JobsTab;


import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.domain.Job;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AddJobController {

    private JobManager jobManager = new JobManager();

    public Button addJob;

    public TextField title;
    private SimpleStringProperty titleProperty;

    public TextField minSalary;
    private SimpleStringProperty minSalaryProperty;

    public TextField maxSalary;
    private SimpleStringProperty maxSalaryProperty;


    public AddJobController() throws SQLException {
        titleProperty = new SimpleStringProperty("");
        minSalaryProperty = new SimpleStringProperty("");
        maxSalaryProperty = new SimpleStringProperty("");

    }

    public void initialize(){
        title.textProperty().bindBidirectional(titleProperty);
        minSalary.textProperty().bindBidirectional(minSalaryProperty);
        maxSalary.textProperty().bindBidirectional(maxSalaryProperty);
    }

    public void addJob(ActionEvent actionEvent) {

        Job j = new Job();
        j.setTitle(titleProperty.getValue());
        j.setMinSalary(Double.parseDouble(minSalaryProperty.getValue()));
        j.setMaxSalary(Double.parseDouble(maxSalaryProperty.getValue()));

        jobManager.addNewJob(j);
    }


}

