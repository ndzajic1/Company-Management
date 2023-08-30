package ba.unsa.etf.rpr.controllers.EmployeePanel.DepartmentsTab;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.controllers.EmployeePanel.DepartmentsTab.DepartmentsTabController;
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

public class AddDepartmentController {
    private EmployeeManager employeeManager = new EmployeeManager();
    private DepartmentManager departmentManager = new DepartmentManager();

    private DepartmentsTabController mainController;

    @FXML
    private Button addButton;
    @FXML
    private TextField deptName;

    private SimpleStringProperty deptProperty;
    @FXML
    private TextField deptLocation;
    private SimpleStringProperty locationProperty;
    @FXML
    private ChoiceBox<Employee> managers;
    private ObservableList<Employee> managersList;

    public AddDepartmentController(Object o) throws SQLException, CompanyException {
        mainController = (DepartmentsTabController) o;
        deptProperty = new SimpleStringProperty("");
        locationProperty = new SimpleStringProperty("");
        managersList = FXCollections.observableArrayList(employeeManager.getAllEmployees());

    }

    @FXML
    public void initialize(){
        deptName.textProperty().bindBidirectional(deptProperty);
        deptLocation.textProperty().bindBidirectional(locationProperty);
        managers.setItems(managersList);
        managers.setConverter(new StringConverter<Employee>() {
            @Override
            public String toString(Employee employee) {
                return employee == null ? "" : employee.getFirstName() + " " + employee.getLastName();
            }
            @Override
            public Employee fromString(String s) {
                return null;
            }
        });
    }

    @FXML
    public void addDept(ActionEvent actionEvent) throws SQLException {
        try {
            Employee mngr = managers.valueProperty().getValue();
            Department d = new Department();
            d.setName(deptProperty.getValue());
            d.setLocation(locationProperty.getValue());
            d.setManager(mngr);

            departmentManager.addNewDept(d);
            mainController.refreshTable();

            Node n = (Node) actionEvent.getSource();
            Stage currStage = (Stage) n.getScene().getWindow();
            currStage.close();
        }
        catch(CompanyException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    @FXML
    public void cancel(ActionEvent actionEvent){
        Node n = (Node) actionEvent.getSource();
        Stage currStage = (Stage) n.getScene().getWindow();
        currStage.close();
    }


}
