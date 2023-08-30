package ba.unsa.etf.rpr.controllers.EmployeePanel.EmployeesTab;

import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CompanyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Confirm deletion window controller.
 */
public class RemoveEmployeeController {

    private Employee employee;
    private EmployeeManager employeeManager = new EmployeeManager();
    private EmployeesTabController mainController;

    public RemoveEmployeeController(Employee e, Object mainController){
        this.employee = e;
        this.mainController = (EmployeesTabController) mainController;
    }

    /**
     * Confirm deletion event handler.
     * @param actionEvent
     */
    @FXML
    public void removeEmployee(ActionEvent actionEvent) {
        try {
            employeeManager.deleteEmployee(employee.getId());
            mainController.returnFromModal();
            Node n = (Node) actionEvent.getSource();
            Stage currStage = (Stage) n.getScene().getWindow();
            currStage.close();
        } catch(CompanyException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     * Exit form.
     */
    @FXML
    public void cancel(ActionEvent actionEvent){
        Node n = (Node) actionEvent.getSource();
        Stage currStage = (Stage) n.getScene().getWindow();
        currStage.close();
    }
}
