package ba.unsa.etf.rpr.controllers.EmployeePanel.HomeTab;

import ba.unsa.etf.rpr.controllers.ChangePasswordController;
import ba.unsa.etf.rpr.controllers.EmployeePanel.EmployeePanelController;
import ba.unsa.etf.rpr.controllers.TitlesConfiguration;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CompanyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * Home Tab component of main panel.
 */
public class HomeTabController {

    private Employee user;

    @FXML
    private Button changePasswordButton;

    @FXML
    private Label deptManager;

    @FXML
    private Label deptName;

    @FXML
    private Label employeeName;


    public HomeTabController(){

    }

    @FXML
    public void initialize(){
        user = EmployeePanelController.getUser();
        employeeName.setText(user.getFirstName() + " " + user.getLastName());
        System.out.println(employeeName.getText());
        deptName.setText(user.getDepartment().getName());
        Employee mngr = user.getDepartment().getManager();
        deptManager.setText(mngr.getFirstName() + " " + mngr.getLastName());
    }

    /**
     * Event handler when user selects option for changing the password. Opens window.
     * @param event
     * @throws IOException
     */
    @FXML
    void changePassword(ActionEvent event) throws IOException, CompanyException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChangePassword.fxml"));
        loader.setController(new ChangePasswordController(user));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setTitle(TitlesConfiguration.getProperty("changePassword"));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

}