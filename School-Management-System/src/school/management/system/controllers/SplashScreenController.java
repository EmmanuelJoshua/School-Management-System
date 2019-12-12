/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.management.system.controllers;

import com.jfoenix.controls.JFXProgressBar;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author DELL
 */
public class SplashScreenController implements Initializable {

    @FXML
    private Text textProgress;
    @FXML
    private VBox splashLayout;
    @FXML
    private JFXProgressBar jfxProgressBar;
    @FXML
    private AnchorPane splashPane;
    @FXML
    private Label yearLabel;

    private double xOffset = 0;
    private double yOffset = 0;

    public void loadProgressBar() {
        JFXProgressBar jfxBar = new JFXProgressBar();
        jfxBar.setPrefWidth(500);
        JFXProgressBar jfxBar1 = new JFXProgressBar();
        jfxBar1.setPrefWidth(500);
        jfxBar1.setProgress(-1.0f);
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO,
                new KeyValue(jfxProgressBar.progressProperty(), 0),
                new KeyValue(jfxBar.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(12),
                        new KeyValue(jfxProgressBar.progressProperty(), 1),
                        new KeyValue(jfxBar.progressProperty(), 1)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    Calendar calendar = Calendar.getInstance();
    String copyright = "\u00a9 "; // unicode symbol for copyright 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadProgressBar();
        textLoader();

        //for getting the current year
        int year = calendar.get(Calendar.YEAR);
        System.out.println(year); //printing the curent year to the console
        System.out.println(copyright); //printing the specila copyright character to the console

        //setting the jfxlabel to the current year and appending the copyright
        yearLabel.setText(copyright + Integer.toString(year));
    }

    public void textLoader() {
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {

                updateMessage("Loading");
                Thread.sleep(2000);
                updateMessage("Loading Dashboard");
                // some actions
                Thread.sleep(2000);
                updateMessage("Loading UI");

                //more  time consuming actions
                Thread.sleep(2000);
                updateMessage("Loading Database");

                Thread.sleep(2500);
                updateMessage("Finalizing");
                Thread.sleep(3400);

                //finally done
                updateMessage("Done");
                Thread.sleep(100);
                return null;
            }
        };

        textProgress.textProperty().bind(task.messageProperty());
        task.setOnSucceeded((WorkerStateEvent e) -> {
            textProgress.textProperty().unbind();
            ((Stage) splashPane.getScene().getWindow()).close();
            try {
                String path = "/school/management/system/images/CHMS_Icon.png";
                
                Image img = new Image(path);
                
                Stage stage = new Stage();
                stage.getIcons().add(0, img);
                Parent root = FXMLLoader.load(SplashScreenController.this.getClass().getResource("/school/management/system/fxml/Login.fxml"));
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
                stage.setTitle("Login | CHMS");
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
                stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
                
                //dragable login stage
                root.setOnMousePressed((MouseEvent event) -> {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                });
                
                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                });//end of draggable stage
            } catch (IOException ex) {
                Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}
