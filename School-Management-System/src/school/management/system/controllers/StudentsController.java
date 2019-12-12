/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.management.system.controllers;

import com.gn.GNAvatarView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Spark
 */
public class StudentsController implements Initializable {

    @FXML
    private TableView<?> adStudentTable;
    @FXML
    private TableColumn<?, ?> registrationNumber;
    @FXML
    private TableColumn<?, ?> fullName;
    @FXML
    private TableColumn<?, ?> dob;
    @FXML
    private TableColumn<?, ?> gender;
    @FXML
    private TableColumn<?, ?> studentClass;
    @FXML
    private TableColumn<?, ?> residentialAddress;
    @FXML
    private TableColumn<?, ?> paymentStatus;
    @FXML
    private GNAvatarView stuImage;
    @FXML
    private Text studentName;
    @FXML
    private Text studentPaymentStatus;
    @FXML
    private Text stuMaleText;
    @FXML
    private Text stuFemaleText;
    @FXML
    private Text stuTotalText;
    @FXML
    private TextField studentSearch;
    @FXML
    private AnchorPane addStudentPane;
    @FXML
    private GNAvatarView avatarStudView;
    @FXML
    private GNAvatarView avatarGuardView;
    @FXML
    private JFXButton chooseStudBtn;
    @FXML
    private JFXButton chooseGuardbtn;
    @FXML
    private JFXTextField studentFirstName;
    @FXML
    private JFXTextField studentLastName;
    @FXML
    private JFXTextField studentMiddleName;
    @FXML
    private JFXComboBox<?> selectGender;
    @FXML
    private JFXDatePicker studentDateOfBirth;
    @FXML
    private JFXComboBox<?> selectClass;
    @FXML
    private JFXComboBox<?> selectDepartment;
    @FXML
    private JFXTextField studentAdmissionNumber;
    @FXML
    private JFXComboBox<?> selectReligion;
    @FXML
    private JFXTextField guardianName;
    @FXML
    private JFXTextField guardianMail;
    @FXML
    private JFXTextField guardianPhone;
    @FXML
    private JFXTextField guardianOccupation;
    @FXML
    private JFXTextField residence;
    @FXML
    private JFXComboBox<?> nationality;
    @FXML
    private AnchorPane primary_SecPaneStudent;
    @FXML
    private AnchorPane studentPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addStudent(ActionEvent event) {
        addStudentPane.setVisible(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(addStudentPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    @FXML
    private void editStudent(ActionEvent event) {
        DashboardController dash = new DashboardController();
        dash.editStudentModals.setVisible(true);
////        dashboardPane.setDisable(true);
////        studentPane.setDisable(true);
////        staffPane.setDisable(true);
////        settingsPane.setDisable(true);
////        gradesPane.setDisable(true);
////        parentsPane.setDisable(true);
////        coursesPane.setDisable(true);
//        FadeTransition fade = new FadeTransition();
//        fade.setDuration(Duration.millis(300));
//        fade.setNode(dash.editStudentModals);
//        fade.setFromValue(0);
//        fade.setToValue(1);
//        fade.play();
    }

    @FXML
    private void deleteStudent(ActionEvent event) {
        DashboardController dash = new DashboardController();
        dash.setV();
//        dashboardPane.setDisable(true);
//        studentPane.setDisable(true);
//        staffPane.setDisable(true);
//        settingsPane.setDisable(true);
//        gradesPane.setDisable(true);
//        parentsPane.setDisable(true);
//        coursesPane.setDisable(true);
//        FadeTransition fade = new FadeTransition();
//        fade.setDuration(Duration.millis(300));
//        fade.setNode(dash.deleteStudentPane);
//        fade.setFromValue(0);
//        fade.setToValue(1);
//        fade.play();
    }

    @FXML
    private void searchStudentDetails(KeyEvent event) {
    }

    @FXML
    private void saveStudentDetails(ActionEvent event) {
    }

    @FXML
    private void studentResetBtn(ActionEvent event) {
    }

    @FXML
    private void openStudbtn(ActionEvent event) {
    }

    @FXML
    private void openGuardbtn(ActionEvent event) {
    }

    @FXML
    private void backFromAddStudent(ActionEvent event) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(addStudentPane);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((ActionEvent event1) -> {
            addStudentPane.setVisible(false);
        });
    }

    @FXML
    private void selectStudentSchool(ActionEvent event) {
    }

    @FXML
    private void studentPrimary(ActionEvent event) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(primary_SecPaneStudent);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((event2) -> {
            primary_SecPaneStudent.setVisible(false);
        });
    }

    @FXML
    private void studentSecondary(ActionEvent event) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(primary_SecPaneStudent);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((event2) -> {
            primary_SecPaneStudent.setVisible(false);
        });
    }

}
