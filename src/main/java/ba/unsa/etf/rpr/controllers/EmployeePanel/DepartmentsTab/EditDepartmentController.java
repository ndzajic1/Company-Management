package ba.unsa.etf.rpr.controllers.EmployeePanel.DepartmentsTab;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CompanyException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.sql.SQLException;

public class EditDepartmentController {
    private EmployeeManager employeeManager = new EmployeeManager();
    private DepartmentManager departmentManager = new DepartmentManager();

    private DepartmentsTabController mainController;

    private Department department;
    @FXML
    private Button editButton;
    @FXML
    private TextField deptName;
    private SimpleStringProperty deptProperty;

    @FXML
    private TextField deptLocation;
    private SimpleStringProperty locationProperty;
    public ChoiceBox<Employee> managers;
    private ObservableList<Employee> managersList;

    public EditDepartmentController(Department d, Object o) throws CompanyException {
        this.department = d;
        this.mainController = (DepartmentsTabController) o;
        deptProperty = new SimpleStringProperty(d.getName());
        locationProperty = new SimpleStringProperty(d.getLocation());
        managersList = FXCollections.observableArrayList(employeeManager.getEmployeesFromDepartment(d));

    }

    @FXML
    public void initialize(){
        deptName.textProperty().bindBidirectional(deptProperty);
        deptLocation.textProperty().bindBidirectional(locationProperty);
        managers.setValue(department.getManager());
        managers.setItems(managersList);
        managers.setConverter(new StringConverter<Employee>() {
            @Override
            public String toString(Employee employee) {
                return employee.getFirstName() + " " + employee.getLastName();
            }
            @Override
            public Employee fromString(String s) {
                return null;
            }
        });
    }

    @FXML
    public void editDept(ActionEvent actionEvent) throws SQLException {
        try {
            Employee mngr = managers.valueProperty().getValue();

            department.setName(deptProperty.getValue());
            department.setLocation(locationProperty.getValue());
            department.setManager(mngr);

            departmentManager.updateDept(department);

            mainController.refreshTable();

            Node n = (Node) actionEvent.getSource();
            Stage currStage = (Stage) n.getScene().getWindow();
            currStage.close();
        } catch(CompanyException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
