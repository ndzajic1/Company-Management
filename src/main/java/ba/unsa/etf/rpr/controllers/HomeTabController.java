package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.LoggableUser;
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
        user = EmployeePanel.getUser();
        employeeName.setText(user.getFirstName() + " " + user.getLastName());
        System.out.println(employeeName.getText());
        deptName.setText(user.getDepartment().getName());
        Employee mngr = user.getDepartment().getManager();
        deptManager.setText(mngr.getFirstName() + " " + mngr.getLastName());
    }

    @FXML
    void changePassword(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChangePassword.fxml"));
        loader.setController(new ChangePasswordController(user));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setTitle("Change Password");
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

}