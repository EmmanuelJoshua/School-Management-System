/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.management.system.controllers;

import com.gn.GNAvatarView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import school.management.system.demoDatabase.Database;
import school.management.system.tables.AdminStudentsTable;

/**
 *
 * @author DELL
 */
public class DashboardController implements Initializable {

    Database da = new Database();
    @FXML
    private TableColumn<AdminStudentsTable, String> registrationNumber;
    @FXML
    private TableColumn<AdminStudentsTable, String> fullName;
    @FXML
    private TableColumn<AdminStudentsTable, String> dob;
    @FXML
    private TableColumn<AdminStudentsTable, String> gender;
    @FXML
    private TableColumn<AdminStudentsTable, String> studentClass;
    @FXML
    private TableColumn<AdminStudentsTable, String> residentialAddress;
    @FXML
    private TableColumn<AdminStudentsTable, String> paymentStatus;
    ObservableList<AdminStudentsTable> obs = FXCollections.observableArrayList();
    @FXML
    private TableView<AdminStudentsTable> adStudentTable;
    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private AnchorPane studentPane;
    @FXML
    private AnchorPane staffPane;
    @FXML
    private JFXButton dashboardBtn;
    @FXML
    private JFXButton studentBtn;
    @FXML
    private JFXButton staffBtn;
    @FXML
    private FontAwesomeIconView stuIcon;
    @FXML
    private FontAwesomeIconView staffIcon;
    @FXML
    private MaterialDesignIconView dashIcon;
    @FXML
    private AnchorPane mainDashPane;
    @FXML
    private JFXButton logoutYes;
    @FXML
    private JFXButton logoutNo;
    @FXML
    private AnchorPane logoutPane;
    @FXML
    private AnchorPane addStudentPane;
    @FXML
    private JFXComboBox selectGender;
    @FXML
    private JFXComboBox selectClass;
    @FXML
    private JFXComboBox selectDepartment;
    @FXML
    private JFXComboBox selectReligion;
    @FXML
    private JFXButton chooseStudBtn;
    @FXML
    private GNAvatarView avatarStudVew;
    @FXML
    private JFXButton chooseGuardbtn;
    @FXML
    private GNAvatarView avatarGuardView;
    @FXML
    private TableView<?> adStudentTable1;
    @FXML
    private TableColumn<?, ?> registrationNumber1;
    @FXML
    private TableColumn<?, ?> fullName1;
    @FXML
    private TableColumn<?, ?> dob1;
    @FXML
    private TableColumn<?, ?> gender1;
    @FXML
    private TableColumn<?, ?> studentClass1;
    @FXML
    private TableColumn<?, ?> residentialAddress1;
    @FXML
    private TableColumn<?, ?> paymentStatus1;

    @FXML
    private void openStudbtn(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        //set title for filechooser
        fileChooser.setTitle("Open Image");

        //set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");

        //show open file dialog
        fileChooser.getInitialDirectory();
        File selectedFile = fileChooser.showOpenDialog(null);

        try {
            String filePath = selectedFile.toURI().toString();
            Image image = new Image(filePath);
            avatarStudVew.setImage(image);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void openGuardbtn(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        //set title for filechooser
        fileChooser.setTitle("Open Image");

        //set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");

        //show open file dialog
        fileChooser.getInitialDirectory();
        File selectedFile = fileChooser.showOpenDialog(null);

        try {
            String filePath = selectedFile.toURI().toString();
            Image image = new Image(filePath);
            avatarGuardView.setImage(image);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void openDashboard(ActionEvent event) {
        if (studentBtn.getStyleClass().size() == 2 && stuIcon.getStyleClass().size() == 2) {

        } else if (studentBtn.getStyleClass().size() == 3 && stuIcon.getStyleClass().size() == 3) {
            studentBtn.getStyleClass().remove(2);
            stuIcon.getStyleClass().remove(2);
        }

        if (staffBtn.getStyleClass().size() == 2 && staffIcon.getStyleClass().size() == 2) {

        } else if (staffBtn.getStyleClass().size() == 3 && staffIcon.getStyleClass().size() == 3) {
            staffBtn.getStyleClass().remove(2);
            staffIcon.getStyleClass().remove(2);
        }

        if (dashboardBtn.getStyleClass().toString().contains("active") && dashIcon.getStyleClass().toString().contains("iconActive")) {

        } else {
            dashboardBtn.getStyleClass().add("active");
            dashIcon.getStyleClass().add("iconActive");
            dashboardPane.setVisible(true);
            FadeTransition fade = new FadeTransition();
            fade.setDuration(Duration.millis(300));
            fade.setNode(dashboardPane);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play();
        }

        studentPane.setVisible(false);
        staffPane.setVisible(false);
    }

    @FXML
    private void openStudent(ActionEvent event) {
        if (dashboardBtn.getStyleClass().size() == 2 && dashIcon.getStyleClass().size() == 2) {

        } else if (dashboardBtn.getStyleClass().size() == 3 && dashIcon.getStyleClass().size() == 3) {
            dashboardBtn.getStyleClass().remove(2);
            dashIcon.getStyleClass().remove(2);
        }

        if (staffBtn.getStyleClass().size() == 2 && staffIcon.getStyleClass().size() == 2) {

        } else if (staffBtn.getStyleClass().size() == 3 && staffIcon.getStyleClass().size() == 3) {
            staffBtn.getStyleClass().remove(2);
            staffIcon.getStyleClass().remove(2);
        }

        if (studentBtn.getStyleClass().toString().contains("active") && stuIcon.getStyleClass().toString().contains("iconActive")) {

        } else {
            studentBtn.getStyleClass().add("active");
            stuIcon.getStyleClass().add("iconActive");
            studentPane.setVisible(true);
            FadeTransition fade = new FadeTransition();
            fade.setDuration(Duration.millis(300));
            fade.setNode(studentPane);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play();
        }

        dashboardPane.setVisible(false);
        staffPane.setVisible(false);
    }

    @FXML
    private void openStaff(ActionEvent event) {
        if (dashboardBtn.getStyleClass().size() == 2 && dashIcon.getStyleClass().size() == 2) {

        } else if (dashboardBtn.getStyleClass().size() == 3 && dashIcon.getStyleClass().size() == 3) {
            dashboardBtn.getStyleClass().remove(2);
            dashIcon.getStyleClass().remove(2);
        }

        if (studentBtn.getStyleClass().size() == 2 && stuIcon.getStyleClass().size() == 2) {

        } else if (studentBtn.getStyleClass().size() == 3 && stuIcon.getStyleClass().size() == 3) {
            studentBtn.getStyleClass().remove(2);
            stuIcon.getStyleClass().remove(2);
        }

        if (staffBtn.getStyleClass().toString().contains("active") && staffIcon.getStyleClass().toString().contains("iconActive")) {

        } else {
            staffBtn.getStyleClass().add("active");
            staffIcon.getStyleClass().add("iconActive");
            staffPane.setVisible(true);
            FadeTransition fade = new FadeTransition();
            fade.setDuration(Duration.millis(300));
            fade.setNode(staffPane);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play();
        }

        dashboardPane.setVisible(false);
        studentPane.setVisible(false);
    }
    public void populateComboBoxes(){
        ObservableList genders = FXCollections.observableArrayList(
        "Male","Female");
        selectGender.setItems(genders);
        ObservableList classes = FXCollections.observableArrayList(
                "JSS1","JSS2","JSS3","SS1","SS2","SS3");
        selectClass.setItems(classes);
        ObservableList departments = FXCollections.observableArrayList(
                "Science","Art","Commercial");
        selectDepartment.setItems(departments);
        ObservableList religions = FXCollections.observableArrayList(
        "Christianity","Islamism","Others");
        selectReligion.setItems(religions);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateComboBoxes();
//        try {
//            Connection con = da.getConnection();
//            ResultSet res = con.createStatement().executeQuery("SELECT RegistrationNumber,FirstName+' '+MiddleName+' '+LastName AS 'FullName',DOB,Gender,s.Class,ResidentialAddress,PaymentStatus FROM Students.StudentDetails s\n"
//                    + "INNER JOIN Students.PaymentDetails p ON p.StudentId=s.StudentId;");
//
//            while (res.next()) {
//                obs.add(new AdminStudentsTable(res.getString("RegistrationNumber"), res.getString("FullName"),
//                        res.getString("DOB"), res.getString("Gender"), res.getString("Class"),
//                        res.getString("ResidentialAddress"), res.getString("PaymentStatus")));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        registrationNumber.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));
//        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
//        dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
//        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
//        studentClass.setCellValueFactory(new PropertyValueFactory<>("studentClass"));
//        residentialAddress.setCellValueFactory(new PropertyValueFactory<>("residentialAddress"));
//        paymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
//        adStudentTable.setItems(obs);
        logoutYes.setOnAction((ActionEvent event1) -> {
            try {
                logOutAction();
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        logoutNo.setOnAction((ActionEvent event2) -> {
            FadeTransition fade3 = new FadeTransition();
            fade3.setDuration(Duration.millis(500));
            fade3.setNode(logoutPane);
            fade3.setFromValue(1);
            fade3.setToValue(0);
            fade3.play();
            fade3.setOnFinished((ActionEvent event1) -> {
                logoutPane.setVisible(false);
            });
        });
    }

    public void closeStage() {
        ((Stage) mainDashPane.getScene().getWindow()).close();
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
    private void backFromAddStudent() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(addStudentPane);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((ActionEvent event) -> {
            addStudentPane.setVisible(false);
        });
    }

    @FXML
    private void logOut(ActionEvent event) {
        logoutPane.setVisible(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(500));
        fade.setNode(logoutPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    private void logOutAction() throws IOException {
        closeStage();
        Parent parent = FXMLLoader.load(getClass().getResource("/school/management/system/fxml/Login.fxml"));

        Stage stage = new Stage(StageStyle.TRANSPARENT);

        Scene scene = new Scene(parent);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);

        stage.initOwner(((Stage) mainDashPane.getScene().getWindow()));
        stage.setScene(scene);
        stage.show();
    }
}
