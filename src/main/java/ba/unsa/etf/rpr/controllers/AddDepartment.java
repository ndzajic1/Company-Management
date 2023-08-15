package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
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

public class AddDepartment {
    private EmployeeManager employeeManager = new EmployeeManager();
    private DepartmentManager departmentManager = new DepartmentManager();
    @FXML
    public Button addButton;
    public TextField deptName;
    private SimpleStringProperty deptProperty;

    public TextField location;
    private SimpleStringProperty locationProperty;
    public ChoiceBox<Employee> managers;
    private ObservableList<Employee> managersList;

    public AddDepartment() throws SQLException {
        deptProperty = new SimpleStringProperty("");
        locationProperty = new SimpleStringProperty("");
        managersList = FXCollections.observableArrayList(employeeManager.getAllEmployees());

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

    public void addDept(ActionEvent actionEvent) {
        Employee mngr = managers.valueProperty().getValue();

        Department d = new Department();
        d.setName(deptProperty.getValue());
        d.setLocation(locationProperty.getValue());
        d.setManager(mngr);

        departmentManager.addNewDept(d);
    }


}
