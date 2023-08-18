package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

public class EmployeePanel {

    private static Employee user = null;

    public static Employee getUser(){
        return user;
    }

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
        user = e;
        this.homeTabController = new HomeTabController();
        this.employeesTabController = new EmployeesTabController();
        this.departmentsTabController = new DepartmentsTabController();
        this.jobsTabController = new JobsTabController();
    }

    @FXML
    void initialize(){
        System.out.println(user.getDepartment().getManager());
        if(user.isAdmin() || user.getDepartment().getManager().equals(user)){
            employeesTab.setDisable(false);
        }
        else{
            employeesTab.setDisable(true);
        }

    }



}
