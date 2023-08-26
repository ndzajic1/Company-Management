package ba.unsa.etf.rpr.controllers.EmployeePanel.EmployeesTab;

import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.controllers.EmployeeCellValueFactory;
import ba.unsa.etf.rpr.controllers.EmployeePanel.EmployeePanelController;
import ba.unsa.etf.rpr.domain.Employee;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class EmployeesTabController {

    private EmployeeManager employeeManager = new EmployeeManager();
    private Employee employee;

    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getEmployeeList(){
        return employeeList;
    }
    public void setEmployeeList() throws SQLException {
        if(employee.isAdmin())
            employeeList = employeeManager.getAllEmployees();
        else{
            employeeList = employeeManager.getEmployeesFromDepartment(employee.getDepartment());
        }
    }

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Employee> employeesTable;

    @FXML
    private TableColumn<Employee, String> firstNameCol;

    @FXML
    private TableColumn<Employee, String> lastNameCol;

    @FXML
    private TableColumn<Employee, String> dateHiredCol;

    @FXML
    private TableColumn<Employee, String> deptNameCol;

    @FXML
    private TableColumn<Employee, String> jobTitleCol;

    @FXML
    private TableColumn<Employee, String> salaryCol;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button removeButton;



    public EmployeesTabController(){


    }

    @FXML
    public void initialize() throws SQLException {

        this.employee = EmployeePanelController.getUser();
        firstNameCol.setCellValueFactory(new EmployeeCellValueFactory("First Name"));
        lastNameCol.setCellValueFactory(new EmployeeCellValueFactory("Last Name"));
        dateHiredCol.setCellValueFactory(new EmployeeCellValueFactory("Date Hired"));
        deptNameCol.setCellValueFactory(new EmployeeCellValueFactory("Department"));
        jobTitleCol.setCellValueFactory(new EmployeeCellValueFactory("Job Title"));
        salaryCol.setCellValueFactory(new EmployeeCellValueFactory("Salary"));

        setEmployeeList();

        refreshTable(employeeList);

        //disable buttons if not admin
        if(!employee.isAdmin()){
            addButton.setDisable(true);
            editButton.setDisable(true);
            removeButton.setDisable(true);
        }




    }

    @FXML
    public void addEmployee(ActionEvent event) throws SQLException {
        // open new window
        openForm(event, new AddEmployeeController(this), "/fxml/EmployeePanel/EmployeesTab/AddEmployee.fxml", "Add employee");
    }

    @FXML
   public void editEmployee(ActionEvent event) throws SQLException {
        // open new window
        openForm(event, new EditEmployeeController(employeesTable.getSelectionModel().getSelectedItem(), this), "/fxml/EmployeePanel/EmployeesTab/EditEmployee.fxml", "Edit employee");
    }

    @FXML
    public void removeEmployee(ActionEvent event) {
        // open new window
        openForm(event, new RemoveEmployeeController(employeesTable.getSelectionModel().getSelectedItem(), this), "/fxml/EmployeePanel/EmployeesTab/RemoveEmployee.fxml", "Remove employee");
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

    @FXML
    public void searchEmployees(ActionEvent event) throws SQLException {
        // refresh  the list
        String query = searchField.getText();
        if(query.equals(""))
            refreshTable(employeeList);
        else
            refreshTable(employeeManager.searchEmployees(query));
        System.out.println(employeeList.size());
    }

    public void refreshTable(List<Employee> employees) throws SQLException {


        employeesTable.setItems(FXCollections.observableArrayList(employees));
        employeesTable.refresh();

        System.out.println("SIZEEEEEEEE" + employeeList.size());
    }

    public void returnFromModal() throws SQLException {
        setEmployeeList();
        refreshTable(getEmployeeList());
    }

}