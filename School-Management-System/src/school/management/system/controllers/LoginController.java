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
import de.jensd.fx.glyphs.octicons.OctIconView;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import school.management.system.validation.Validator;
import school.management.system.demoDatabase.Database;

/**
 *
 * @author DELL
 */
public class LoginController implements Initializable {

    Image errorImg = new Image("/school/management/system/images/cross.png");
    @FXML
    private JFXProgressBar progress;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private Pane exitConfirmPane;
    @FXML
    private StackPane forgot_password;
           @FXML
    private AnchorPane adminPassword;
      @FXML
    private AnchorPane generatePassword;
          @FXML
    private Pane login_Stage;
    
    Connection con2;
    String user, pass;

//    private boolean userLogin(String username, String password) {
//        return Validator.validate(username, password);
//    }
    public void closeStage() {
        ((Stage) loginPane.getScene().getWindow()).close();
    }

    public void next() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/school/management/system/fxml/Dashboard.fxml"));

            String path = "/school/management/system/images/CHMS_Icon.png";
            Image img = new Image(path);
            Stage stage = new Stage();
            stage.setTitle("Home | CHMS");
            stage.getIcons().add(0, img);
            stage.setScene(new Scene(parent));
            stage.initOwner(((Stage) loginPane.getScene().getWindow()));
            stage.setMinWidth(1200);
            stage.setMinHeight(750);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void nextStaff() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/school/management/system/fxml/staffDashboard.fxml"));

            String path = "/school/management/system/images/CHMS_Icon.png";
            Image img = new Image(path);
            Stage stage = new Stage();
            stage.setTitle("Staff | CHMS");
            stage.getIcons().add(0, img);
            stage.setScene(new Scene(parent));
            stage.initOwner(((Stage) loginPane.getScene().getWindow()));
            stage.setMinWidth(1200);
            stage.setMinHeight(750);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void exitLogin(MouseEvent event) {
        exitConfirmPane.setVisible(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(500));
        fade.setNode(exitConfirmPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    @FXML
    private void minimiseLogin(MouseEvent event) {
        Stage stage = (Stage) loginPane.getScene().getWindow();
        stage = (Stage) ((OctIconView) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
//method to validate sign 

    public void validateSignIn() {
        try {
            ResultSet res = con2.createStatement().executeQuery("select * from LogInDetails");
            while (res.next()) {
                int id = res.getInt("Id");
                user = res.getString("Username");
                pass = res.getString("Password");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            Notifications notify = Notifications.create()
                    .graphic(new ImageView(errorImg))
                    .title("ERROR")
                    .text("Username and Password Empty")
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(3));
            notify.show();
//        } else if (!username.getText().equals(user) || !password.getText().equals(pass)) {
//            Notifications notify = Notifications.create()
//                    .graphic(new ImageView(errorImg))
//                    .title("ERROR")
//                    .text("Invalid Login")
//                    .position(Pos.TOP_CENTER)
//                    .hideAfter(Duration.seconds(3));
//            notify.show();
        } else if (username.getText().equals("staff") && password.getText().equals("staff")) {
             StaffDashboardController.teacherID = username.getText();
//            PauseTransition pause = new PauseTransition(Duration.seconds(5));
//            loginBtn.setMouseTransparent(true);
//            progress.setVisible(true);
//            pause.play();
//            pause.setOnFinished((ActionEvent event1) -> {
                closeStage();
                nextStaff();
//            });
        } else {

//            PauseTransition pause = new PauseTransition(Duration.seconds(5));
//            loginBtn.setMouseTransparent(true);
//            progress.setVisible(true);
//            pause.play();
//            pause.setOnFinished((ActionEvent event1) -> {
                closeStage();
                next();
//            });
        }
    }

    @FXML
    private void signIn(ActionEvent event) {
        validateSignIn();
    }

    @FXML
    private void exitYesAction(ActionEvent event) {
        System.exit(0);
    }

      @FXML
    void forgotPassword(ActionEvent event) {
            forgot_password.setVisible(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(500));
        fade.setNode(forgot_password);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }
        @FXML
    void submit_btn(ActionEvent event) {
         generatePassword.setVisible(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(500));
        fade.setNode(generatePassword);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }
        @FXML
    void login_backBtn(ActionEvent event) {
         login_Stage.setVisible(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(500));
        fade.setNode(login_Stage);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

            @FXML
    void exit_pass(ActionEvent event) {
                FadeTransition fade3 = new FadeTransition();
        fade3.setDuration(Duration.millis(500));
        fade3.setNode(forgot_password);
        fade3.setFromValue(1);
        fade3.setToValue(0);
        fade3.play();
        fade3.setOnFinished((ActionEvent event1) -> {
            forgot_password.setVisible(false);
        });
    }
    @FXML
    private void exitNoAction(ActionEvent event) {
        FadeTransition fade3 = new FadeTransition();
        fade3.setDuration(Duration.millis(500));
        fade3.setNode(exitConfirmPane);
        fade3.setFromValue(1);
        fade3.setToValue(0);
        fade3.play();
        fade3.setOnFinished((ActionEvent event1) -> {
            exitConfirmPane.setVisible(false);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Enter Button Key event listener
        loginPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    validateSignIn();
                }
            }

        });

        //get connection from database
        try {
            con2 = Database.getConnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Enter Button Key event Listener
//         username.setOnKeyPressed(new EventHandler<KeyEvent>(){
//           @Override
//           public void handle(KeyEvent event) {
//               if(event.getCode()== KeyCode.ENTER){
//                   closeStage();
//                   next();
//               }
//           }
//           
//       });
    }

}
