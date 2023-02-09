package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

public class sampleController {
    public Button cancelButtonId;

    public Button okButtonId;
    public TextField userId;
    public PasswordField passId;

    public void okButtonPressed(ActionEvent ae){
        userId.setText("blabber");
    }
    @FXML
    public void initialize(){
        userId.setText("bleed");
    }

}
