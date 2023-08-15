package ba.unsa.etf.rpr.controllers;


import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.sql.SQLException;

public class AddJob {

    private JobManager jobManager = new JobManager();

    public Button addJob;

    public TextField title;
    private SimpleStringProperty titleProperty;

    public TextField minSalary;
    private SimpleStringProperty minSalaryProperty;

    public TextField maxSalary;
    private SimpleStringProperty maxSalaryProperty;


    public AddJob() throws SQLException {
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

