package ba.unsa.etf.rpr.controllers.EmployeePanel.DepartmentsTab;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.sql.SQLException;

public class EditDepartmentController {
    private EmployeeManager employeeManager = new EmployeeManager();
    private DepartmentManager departmentManager = new DepartmentManager();

    private Department department;

    public Button editButton;
    public TextField deptName;
    private SimpleStringProperty deptProperty;

    public TextField location;
    private SimpleStringProperty locationProperty;
    public ChoiceBox<Employee> managers;
    private ObservableList<Employee> managersList;

    public EditDepartmentController(Department d) throws SQLException {
        this.department = d;
        deptProperty = new SimpleStringProperty(d.getName());
        locationProperty = new SimpleStringProperty(d.getLocation());
        managersList = FXCollections.observableArrayList(employeeManager.getEmployeesFromDepartment(d));

    }

    public void initialize(){
        deptName.textProperty().bindBidirectional(deptProperty);
        location.textProperty().bindBidirectional(locationProperty);
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

    public void editDept(ActionEvent actionEvent) {

        Employee mngr = managers.valueProperty().getValue();

        department.setName(deptProperty.getValue());
        department.setLocation(locationProperty.getValue());
        department.setManager(mngr);

        departmentManager.updateDept(department);
    }
}
