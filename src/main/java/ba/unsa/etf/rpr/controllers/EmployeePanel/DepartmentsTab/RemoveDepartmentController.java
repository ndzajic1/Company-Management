package ba.unsa.etf.rpr.controllers.EmployeePanel.DepartmentsTab;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.exceptions.CompanyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
        try {
            departmentManager.deleteDept(department.getId());
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
