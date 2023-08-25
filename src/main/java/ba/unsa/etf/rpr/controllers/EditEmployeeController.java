package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.sql.SQLException;

public class EditEmployeeController {
    private EmployeeManager employeeManager = new EmployeeManager();
    private DepartmentManager departmentManager = new DepartmentManager();

    private JobManager jobManager = new JobManager();

    private Employee employee;

    public Button editButton;
    public TextField firstName;
    private SimpleStringProperty firstNameProperty;

    public TextField lastName;
    private SimpleStringProperty lastNameProperty;

    public DatePicker hired;

    public ChoiceBox<Department> dept;
    private ObservableList<Department> departments;

    public ChoiceBox<Job> job;
    private ObservableList<Job> jobs;

    public Spinner<Double> salary;
    private SimpleDoubleProperty salaryProperty;

    public EditEmployeeController(Employee e) throws SQLException {
        this.employee = e;
        System.out.println(e);
        firstNameProperty = new SimpleStringProperty(e.getFirstName());
        lastNameProperty = new SimpleStringProperty(e.getLastName());
        departments = FXCollections.observableArrayList(departmentManager.getAllDepts());
        jobs = FXCollections.observableArrayList(jobManager.getAllJobs());
        salaryProperty = new SimpleDoubleProperty(e.getSalary());
    }

    @FXML
    public void initialize() {
        firstName.textProperty().bindBidirectional(firstNameProperty);
        lastName.textProperty().bindBidirectional(lastNameProperty);
        dept.setValue(employee.getDepartment());
        dept.setItems(departments);
        job.setValue(employee.getJob());
        job.setItems(jobs);

        hired.setValue(employee.getHireDate());

        job.setConverter(new StringConverter<Job>() {
            @Override
            public String toString(Job job) {
                return job != null ? job.getTitle() : "";
            }

            @Override
            public Job fromString(String s) {
                return null;
            }
        });
        dept.setConverter(new StringConverter<Department>() {
            @Override
            public String toString(Department department) {
                return department != null ? department.getName() : "";
            }

            @Override
            public Department fromString(String s) {
                return null;
            }
        });
        salary.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(employee.getJob().getMinSalary(), employee.getJob().getMaxSalary(), employee.getSalary()));
        // mozda ovo u listener
        salary.getValueFactory().valueProperty().bindBidirectional(salaryProperty.asObject());

        job.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Job>() {
            @Override
            public void changed(ObservableValue<? extends Job> observableValue, Job job, Job t1) {
                salary.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(
                        t1.getMinSalary(), t1.getMaxSalary(), t1.getMinSalary()
                ));
            }
        });
    }

    public void editEmployee(ActionEvent actionEvent) throws SQLException {

        Department d = dept.valueProperty().getValue();
        Job j = job.valueProperty().getValue();

        employee.setFirstName(firstNameProperty.getValue());
        employee.setLastName(lastNameProperty.getValue());
        employee.setHireDate(hired.getValue());
        employee.setDepartment(d);
        employee.setJob(j);
        employee.setSalary(salary.getValueFactory().getValue());

        employeeManager.updateEmployee(employee);

        Node n = (Node) actionEvent.getSource();
        Stage currStage = (Stage) n.getScene().getWindow();
        currStage.close();
    }
}
