package ba.unsa.etf.rpr.controllers.EmployeePanel.DepartmentsTab;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.domain.Department;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.sql.SQLException;

public class RemoveDepartmentController {
    private Department department;
    private DepartmentManager departmentManager = new DepartmentManager();
    private DepartmentsTabController mainController;

    public RemoveDepartmentController(Department d, Object o){
        this.department = d;
        this.mainController = (DepartmentsTabController) o;
    }
    @FXML
    public void removeDept(ActionEvent actionEvent) throws SQLException {
        // moze baciti exc
        departmentManager.deleteDept(department.getId());
        mainController.refreshTable();
        Node n = (Node) actionEvent.getSource();
        Stage currStage = (Stage) n.getScene().getWindow();
        currStage.close();

    }
}
