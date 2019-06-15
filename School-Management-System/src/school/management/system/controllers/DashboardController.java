/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.management.system.controllers;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
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
    @FXML
    private AnchorPane studentPane;
    @FXML
    private JFXButton dashboardBtn;
    @FXML
    private JFXButton studentBtn;
    @FXML
    private FontAwesomeIconView stuIcon;
    @FXML
    private MaterialDesignIconView dashIcon;
    
    @FXML
    private void openDashboard(ActionEvent event){
        studentBtn.getStyleClass().remove(2);
        stuIcon.getStyleClass().remove(2);
        dashboardBtn.getStyleClass().add("active");
        dashIcon.getStyleClass().add("iconActive");
        dashboardPane.setVisible(true);
        studentPane.setVisible(false);
    }
    
    @FXML
    private void openStudent(ActionEvent event){
        dashboardBtn.getStyleClass().remove(2);
        dashIcon.getStyleClass().remove(2);
        studentBtn.getStyleClass().add("active");
        stuIcon.getStyleClass().add("iconActive");
        studentPane.setVisible(true);
        dashboardPane.setVisible(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
