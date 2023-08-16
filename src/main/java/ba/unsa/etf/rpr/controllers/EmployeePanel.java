package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

public class EmployeePanel {

    @FXML
    private Tab homeTab;
    @FXML
    private HomeTabController homeTabController;

    @FXML
    private Tab employeesTab;
    @FXML
    private EmployeesTabController employeesTabController;

    @FXML
    private Tab departmentsTab;
    @FXML
    private DepartmentsTabController departmentsTabController;

    @FXML
    private Tab jobsTab;
    @FXML
    private JobsTabController jobsTabController;

    public EmployeePanel(Employee e){
        this.employeesTabController = new EmployeesTabController(e);
        this.departmentsTabController = new DepartmentsTabController(e);
        this.jobsTabController = new JobsTabController(e);
    }

    @FXML
    void initialize(){

    }



}
