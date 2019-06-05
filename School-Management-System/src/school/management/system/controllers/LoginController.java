/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.management.system.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author DELL
 */
public class LoginController implements Initializable {
    @FXML
    private JFXTextField userName;
    @FXML
    private JFXPasswordField password;
    @FXML
    private AnchorPane loginPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void signIn(ActionEvent event) throws IOException {
        String username = "Admin01";
        String userPassword = "AdminAdmin";
        if (userName.getText().equals(username) && password.getText().equals(userPassword)) {
            Stage stage = (Stage) loginPane.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/school/management/system/fxml/Dashboard.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.NONE, "Incorrect Username or Password", ButtonType.OK);
            alert.setTitle("Invalid");
            alert.showAndWait();
        }
    }

}
