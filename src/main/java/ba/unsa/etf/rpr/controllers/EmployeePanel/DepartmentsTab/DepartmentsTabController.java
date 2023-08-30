package ba.unsa.etf.rpr.controllers.EmployeePanel.DepartmentsTab;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.controllers.cell_value_factories.DepartmentCellValueFactory;
import ba.unsa.etf.rpr.controllers.EmployeePanel.EmployeePanelController;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CompanyException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.SQLException;
import java.util.*;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class DepartmentsTabController {

    private DepartmentManager departmentManager = new DepartmentManager();
    private EmployeeManager employeeManager = new EmployeeManager();
    private Employee employee;

    private List<Department> departmentsList = new ArrayList<>();

    @FXML
    private TableView<Department> departmentsTable;

    @FXML
    private TableColumn<Department, String> deptNameCol;

    @FXML
    private TableColumn<Department, String> locationCol;

    @FXML
    private TableColumn<Department, String> managerCol;

    @FXML
    private TableColumn<Department, String> numOfEmployeesCol;

    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;

    public DepartmentsTabController(){


    }

    @FXML
    void initialize() throws SQLException, CompanyException {
        this.employee = EmployeePanelController.getUser();

        deptNameCol.setCellValueFactory(new DepartmentCellValueFactory("Department"));
        locationCol.setCellValueFactory(new DepartmentCellValueFactory("Location"));
        managerCol.setCellValueFactory(new DepartmentCellValueFactory("Manager"));
        numOfEmployeesCol.setCellValueFactory(new DepartmentCellValueFactory("Employees"));



        refreshTable();

        //disable buttons if not admin
        if(!employee.isAdmin()){
            addButton.setDisable(true);
            editButton.setDisable(true);
            removeButton.setDisable(true);
        }


    }

    @FXML
    void addDept(ActionEvent event) throws SQLException, CompanyException {
        openForm(event, new AddDepartmentController(this), "/fxml/EmployeePanel/DepartmentsTab/AddDepartment.fxml", "Add department" );
    }

    @FXML
    void editDept(ActionEvent event) throws SQLException, CompanyException {
        openForm(event, new EditDepartmentController(departmentsTable.getSelectionModel().getSelectedItem(), this), "/fxml/EmployeePanel/DepartmentsTab/EditDepartment.fxml", "Edit department" );
    }

    @FXML
    void removeDept(ActionEvent event) {
        openForm(event, new RemoveDepartmentController(departmentsTable.getSelectionModel().getSelectedItem(), this), "/fxml/EmployeePanel/DepartmentsTab/RemoveDepartment.fxml", "Remove department" );

    }

    public void openForm(ActionEvent actionEvent, Object controller, String fxmlFile, String title){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/img/logo.jpeg")).toExternalForm()));
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void refreshTable() throws SQLException {
        try {
            departmentsList = departmentManager.getAllDepts();
            DepartmentCellValueFactory.setEmployeesPerDeptMap();
            departmentsTable.setItems(FXCollections.observableArrayList(departmentsList));
            departmentsTable.refresh();
        } catch(CompanyException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }


}
