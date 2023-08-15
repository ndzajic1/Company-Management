package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.domain.Job;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EditJob {
    private JobManager jobManager = new JobManager();
    private Job job;


    public Button editJob;

    public TextField title;
    private SimpleStringProperty titleProperty;

    public TextField minSalary;
    private SimpleStringProperty minSalaryProperty;

    public TextField maxSalary;
    private SimpleStringProperty maxSalaryProperty;


    public EditJob(Job j) throws SQLException {
        this.job = j;
        titleProperty = new SimpleStringProperty(j.getTitle());
        minSalaryProperty = new SimpleStringProperty(Double.toString(j.getMinSalary()));
        maxSalaryProperty = new SimpleStringProperty(Double.toString(j.getMaxSalary()));

    }

    public void initialize(){
        title.textProperty().bindBidirectional(titleProperty);
        minSalary.textProperty().bindBidirectional(minSalaryProperty);
        maxSalary.textProperty().bindBidirectional(maxSalaryProperty);
    }

    public void editJob(ActionEvent actionEvent) {

        job.setTitle(titleProperty.getValue());
        job.setMinSalary(Double.parseDouble(minSalaryProperty.getValue()));
        job.setMaxSalary(Double.parseDouble(maxSalaryProperty.getValue()));

        jobManager.updateJob(job);
    }
}
