/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.management.system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author DELL
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane dashboardPane;
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    // @FXML
    // private void logoutBtn(ActionEvent event) throws IOException {
    //     Stage stage = (Stage) dashboardPane.getScene().getWindow();
    //     stage.close();
    //     Parent root = FXMLLoader.load(getClass().getResource("/school/management/system/fxml/Login.fxml"));
    //     Scene scene = new Scene(root);
    //     stage.setScene(scene);
    //     stage.show();
    // }
    
}
