package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class AddEmployee {

    private EmployeeManager employeeManager = new EmployeeManager();
    private DepartmentManager departmentManager = new DepartmentManager();

    private JobManager jobManager = new JobManager();


    public Button addButton;
    public TextField firstName;
    private SimpleStringProperty firstNameProperty;

    public TextField lastName;
    private SimpleStringProperty lastNameProperty;

    public DatePicker hired;

    public ChoiceBox<Department> dept;
    private ObservableList<Department> departments;

    public ChoiceBox<Job> job;
    private ObservableList<Job> jobs;

    public  Spinner<Double> salary;
    private ObjectProperty<Double> salaryProperty;

    public AddEmployee() throws SQLException {
        firstNameProperty = new SimpleStringProperty("");
        lastNameProperty = new SimpleStringProperty("");
        departments = FXCollections.observableArrayList(departmentManager.getAllDepts());
        jobs = FXCollections.observableArrayList(jobManager.getAllJobs());
        salaryProperty = new SimpleObjectProperty<Double>();
    }

    public void initialize() {
        firstName.textProperty().bindBidirectional(firstNameProperty);
        lastName.textProperty().bindBidirectional(lastNameProperty);
        dept.setItems(departments);
        job.setItems(jobs);
        dept.setConverter(new StringConverter<Department>() {
            @Override
            public String toString(Department department) {
                return department.getName();
            }

            @Override
            public Department fromString(String s) {
                return null;
            }
        });
        job.setConverter(new StringConverter<Job>() {
            @Override
            public String toString(Job job) {
                return job.getTitle();
            }

            @Override
            public Job fromString(String s) {
                return null;
            }
        });
        // mozda ovo u listener
        salary.getValueFactory().valueProperty().bindBidirectional(salaryProperty);
        job.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Job>() {
            @Override
            public void changed(ObservableValue<? extends Job> observableValue, Job job, Job t1) {
                salary.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(
                     t1.getMinSalary(), t1.getMaxSalary(), t1.getMinSalary()
                ));
            }
        });
    }

    public void addEmployee(ActionEvent actionEvent) throws SQLException {

        Department d = dept.valueProperty().getValue();
        Job j = job.valueProperty().getValue();

        Employee e = new Employee();
        e.setFirstName(firstNameProperty.getValue());
        e.setLastName(lastNameProperty.getValue());
        e.setHireDate(hired.getValue());
        e.setDepartment(d);
        e.setJob(j);
        e.setSalary(salaryProperty.getValue());

        employeeManager.addNewEmployee(e);
    }


}

