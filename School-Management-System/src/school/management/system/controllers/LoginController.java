/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.management.system.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import school.management.system.validation.Validator;

/**
 *
 * @author DELL
 */
public class LoginController implements Initializable {
    
    Image errorImg= new Image("/school/management/system/images/cross.png");
    @FXML
    private JFXProgressBar progress;
    @FXML
    private JFXButton loginBtn;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private AnchorPane loginPane;

    private boolean userLogin(String username, String password) {
        return Validator.validate(username, password);
    }
    
    public void closeStage() {
        ((Stage) loginPane.getScene().getWindow()).close();
    }
    
    public void next() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/school/management/system/fxml/Dashboard.fxml"));

//            String path = "/ManagementSystem/images/homeA.png";

//            Image img = new Image(path);

            Stage stage = new Stage(StageStyle.DECORATED);
//            stage.setTitle("Home");
//            stage.getIcons().add(0, img);
            stage.setScene(new Scene(parent));
            stage.initOwner(((Stage) loginPane.getScene().getWindow())); 
            stage.setMaximized(true);
//            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void signIn(ActionEvent event){
       if (username.getText().isEmpty() && password.getText().isEmpty()) {
            Notifications notify=Notifications.create()
                        .graphic(new ImageView(errorImg))
                        .title("ERROR")
                        .text("Username and Password Empty")
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(3));
                notify.show();
        } else if (!userLogin(username.getText(), password.getText())) {
           Notifications notify=Notifications.create()
                        .graphic(new ImageView(errorImg))
                        .title("ERROR")
                        .text("Invalid Login")
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(3));
                notify.show(); 
        } else {
//            MainUIController.adminID = user_name.getText();
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            loginBtn.setMouseTransparent(true);
            progress.setVisible(true);
            pause.play();
            pause.setOnFinished((ActionEvent event1) -> {
                closeStage();
                next();
            });
        }
    }
    
     

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
