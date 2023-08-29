package ba.unsa.etf.rpr.controllers.EmployeePanel.EmployeesTab;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;
import ba.unsa.etf.rpr.exceptions.CompanyException;
import javafx.beans.property.SimpleDoubleProperty;
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

public class AddEmployeeController {

    private EmployeeManager employeeManager = new EmployeeManager();
    private DepartmentManager departmentManager = new DepartmentManager();

    private JobManager jobManager = new JobManager();

    private EmployeesTabController mainController;

    @FXML
    public Button addButton;
    @FXML
    public TextField firstName;

    private SimpleStringProperty firstNameProperty;
    @FXML
    public TextField lastName;
    private SimpleStringProperty lastNameProperty;
    @FXML
    public DatePicker hired;
    @FXML
    public ChoiceBox<Department> dept;
    private ObservableList<Department> departments;
    @FXML
    public ChoiceBox<Job> job;
    private ObservableList<Job> jobs;
    @FXML
    public  Spinner<Double> salary;
    private SimpleDoubleProperty salaryProperty;

    public AddEmployeeController(Object mainController) throws CompanyException {
        this.mainController = (EmployeesTabController) mainController;
        firstNameProperty = new SimpleStringProperty("");
        lastNameProperty = new SimpleStringProperty("");
        departments = FXCollections.observableArrayList(departmentManager.getAllDepts());
        jobs = FXCollections.observableArrayList(jobManager.getAllJobs());
        salaryProperty = new SimpleDoubleProperty();
    }

    @FXML
    public void initialize() {
        firstName.textProperty().bindBidirectional(firstNameProperty);
        lastName.textProperty().bindBidirectional(lastNameProperty);
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
        dept.setItems(departments);
        job.setItems(jobs);
        salary.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,100000, 0));
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

    @FXML
    public void addEmployee(ActionEvent actionEvent) throws SQLException {
        try {
            Department d = dept.valueProperty().getValue();
            Job j = job.valueProperty().getValue();

            Employee e = new Employee();
            e.setFirstName(firstNameProperty.getValue());
            e.setLastName(lastNameProperty.getValue());
            e.setHireDate(hired.getValue());
            e.setDepartment(d);
            e.setJob(j);

            System.out.println(salary.getValueFactory().getValue());
            e.setSalary(salary.getValueFactory().getValue());

            employeeManager.addNewEmployee(e);
            mainController.returnFromModal();

            Node n = (Node) actionEvent.getSource();
            Stage currStage = (Stage) n.getScene().getWindow();
            currStage.close();
        } catch(CompanyException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }


}

