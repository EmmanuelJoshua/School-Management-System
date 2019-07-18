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
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.octicons.OctIconView;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import school.management.system.demoDatabase.Database;
import school.management.system.tables.AdminStudentsTable;
import org.controlsfx.control.Notifications;
import school.management.system.tables.TeachersTable;

/**
 *
 * @author DELL
 */
public class DashboardController implements Initializable {

    public Image errorImg = new Image("/school/management/system/images/cross.png");
    public Image successImg = new Image("/school/management/system/images/checked.png");

    Database da = new Database();
    private File mainFile;
    private File guardianFile;
    private File employeeFile;
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
    ObservableList<TeachersTable> obs1 = FXCollections.observableArrayList();
    @FXML
    private TableView<AdminStudentsTable> adStudentTable;
    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private AnchorPane studentPane;
    @FXML
    private AnchorPane gradesPane;
    @FXML
    private AnchorPane staffPane;
    @FXML
    private AnchorPane settingsPane;
    @FXML
    private JFXButton dashboardBtn;
    @FXML
    private JFXButton studentBtn;
    @FXML
    private JFXButton gradeBtn;
    @FXML
    private JFXButton staffBtn;
    @FXML
    private JFXButton settingBtn;
    @FXML
    private OctIconView gradeIcon;
    @FXML
    private OctIconView stuIcon;
    @FXML
    private FontAwesomeIconView staffIcon;
    @FXML
    private MaterialDesignIconView dashIcon;
    @FXML
    private MaterialDesignIconView settingsIcon;
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
    private AnchorPane addTeacherPane;
    @FXML
    private JFXComboBox selectGender;
    @FXML
    private JFXComboBox selectTeacherGender;
    @FXML
    private JFXComboBox selectClass;
    @FXML
    private JFXComboBox selectDepartment;
    @FXML
    private JFXComboBox selectReligion;
    @FXML
    private JFXComboBox selectMaritalStatus;
    @FXML
    private JFXComboBox selectQualification;
    @FXML
    private JFXComboBox selectDesignation;
    @FXML
    private JFXButton chooseStudBtn;
    @FXML
    private GNAvatarView avatarStudView;
    @FXML
    private JFXButton chooseGuardbtn;
    @FXML
    private GNAvatarView avatarGuardView;
    @FXML
    private GNAvatarView stuImage;
    @FXML
    private JFXTextField studentFirstName;
    @FXML
    private JFXTextField studentLastName;
    @FXML
    private JFXTextField studentMiddleName;
    @FXML
    private JFXTextField studentAdmissionNumber;
    @FXML
    private JFXTextField guardianMail;
    @FXML
    private JFXTextField guardianOccupation;
    @FXML
    private JFXTextField residence;
    @FXML
    private JFXTextField guardianPhone;
    @FXML
    private JFXTextField nationality;
    private Image image;
    @FXML
    private JFXDatePicker studentDateOfBirth;
    @FXML
    private JFXTextField guardianName;

    FileChooser fileChooser = new FileChooser();
    File selectedFile;
    File selectedFile1;
    @FXML
    private TableView<TeachersTable> teachersTable;
    @FXML
    private TableColumn<TeachersTable,String> teacherID;
    @FXML
    private TableColumn<TeachersTable,String> teacherFullName;
    @FXML
    private TableColumn<TeachersTable, String> teacherDob;
    @FXML
    private TableColumn<TeachersTable, String> teacherGender;
    @FXML
    private TableColumn<TeachersTable, String> teacherQualification;
    @FXML
    private TableColumn<TeachersTable,String> teacherAddress;
    @FXML
    private TableColumn<TeachersTable,String> teacherDesignation;
    @FXML
    private FontAwesomeIconView staffIcon1;
    @FXML
    private JFXButton chooseEmployeePhoto;
    @FXML
    private GNAvatarView avatarEmployeeView;
    @FXML
    private JFXTextField employeeID;
    @FXML
    private JFXTextField employeeFirstName;
    @FXML
    private JFXTextField employeeLastName;
    @FXML
    private JFXTextField employeeMiddleName;
    @FXML
    private JFXTextField employeePhoneNumber;
    @FXML
    private JFXDatePicker employeDOB;
    @FXML
    private JFXComboBox employeeReligion;
    @FXML
    private JFXTextField employeeResidence;
    @FXML
    private JFXTextField employeeEmail;
    @FXML
    private JFXTextField employeeNationality;
    @FXML
    private GNAvatarView teacherImage;
    @FXML
    private Hyperlink viewTeacher;
    @FXML
    private Text studentName;
    @FXML
    private Text studentPaymentStatus;
    @FXML
    private Text teacherName;

    @FXML
    private void openStudbtn(ActionEvent event) {
        //set title for filechooser
        fileChooser.setTitle("Open Image");

        //set extension filter
        try {
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All images", "*.jpg", "*.jpeg", "*.png", "*.gif"));
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        //show open file dialog
        fileChooser.getInitialDirectory();
        selectedFile = fileChooser.showOpenDialog(null);

        try {
            if (selectedFile != null) {
                String path = selectedFile.getPath();
                selectedFile = new File(path);
                mainFile = selectedFile;
                Image image = new Image(selectedFile.toURI().toString());
                avatarStudView.setImage(image);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void openGuardbtn(ActionEvent event) {
        //set title for filechooser
        fileChooser.setTitle("Open Image");

        //set extension filter
        try {
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All images", "*.jpg", "*.jpeg", "*.png", "*.gif"));
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        //show open file dialog
        fileChooser.getInitialDirectory();
        selectedFile = fileChooser.showOpenDialog(null);

        try {
            if (selectedFile != null) {
                String path = selectedFile.getPath();
                selectedFile = new File(path);
                guardianFile = selectedFile;
                Image image = new Image(selectedFile.toURI().toString());
                avatarGuardView.setImage(image);
            }
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

        if (settingBtn.getStyleClass().size() == 2 && settingsIcon.getStyleClass().size() == 2) {

        } else if (settingBtn.getStyleClass().size() == 3 && settingsIcon.getStyleClass().size() == 3) {
            settingBtn.getStyleClass().remove(2);
            settingsIcon.getStyleClass().remove(2);
        }

        if (gradeBtn.getStyleClass().size() == 2 && gradeIcon.getStyleClass().size() == 2) {

        } else if (gradeBtn.getStyleClass().size() == 3 && gradeIcon.getStyleClass().size() == 3) {
            gradeBtn.getStyleClass().remove(2);
            gradeIcon.getStyleClass().remove(2);
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
        settingsPane.setVisible(false);
        gradesPane.setVisible(false);
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

        if (settingBtn.getStyleClass().size() == 2 && settingsIcon.getStyleClass().size() == 2) {

        } else if (settingBtn.getStyleClass().size() == 3 && settingsIcon.getStyleClass().size() == 3) {
            settingBtn.getStyleClass().remove(2);
            settingsIcon.getStyleClass().remove(2);
        }

        if (gradeBtn.getStyleClass().size() == 2 && gradeIcon.getStyleClass().size() == 2) {

        } else if (gradeBtn.getStyleClass().size() == 3 && gradeIcon.getStyleClass().size() == 3) {
            gradeBtn.getStyleClass().remove(2);
            gradeIcon.getStyleClass().remove(2);
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
        settingsPane.setVisible(false);
        gradesPane.setVisible(false);
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

        if (settingBtn.getStyleClass().size() == 2 && settingsIcon.getStyleClass().size() == 2) {

        } else if (settingBtn.getStyleClass().size() == 3 && settingsIcon.getStyleClass().size() == 3) {
            settingBtn.getStyleClass().remove(2);
            settingsIcon.getStyleClass().remove(2);
        }

        if (gradeBtn.getStyleClass().size() == 2 && gradeIcon.getStyleClass().size() == 2) {

        } else if (gradeBtn.getStyleClass().size() == 3 && gradeIcon.getStyleClass().size() == 3) {
            gradeBtn.getStyleClass().remove(2);
            gradeIcon.getStyleClass().remove(2);
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
        settingsPane.setVisible(false);
        gradesPane.setVisible(false);
    }

    @FXML
    public void openSettings(ActionEvent event) {
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

        if (staffBtn.getStyleClass().size() == 2 && staffIcon.getStyleClass().size() == 2) {

        } else if (staffBtn.getStyleClass().size() == 3 && staffIcon.getStyleClass().size() == 3) {
            staffBtn.getStyleClass().remove(2);
            staffIcon.getStyleClass().remove(2);
        }

        if (gradeBtn.getStyleClass().size() == 2 && gradeIcon.getStyleClass().size() == 2) {

        } else if (gradeBtn.getStyleClass().size() == 3 && gradeIcon.getStyleClass().size() == 3) {
            gradeBtn.getStyleClass().remove(2);
            gradeIcon.getStyleClass().remove(2);
        }

        if (settingBtn.getStyleClass().toString().contains("active") && settingsIcon.getStyleClass().toString().contains("iconActive")) {

        } else {
            settingBtn.getStyleClass().add("active");
            settingsIcon.getStyleClass().add("iconActive");
            settingsPane.setVisible(true);
            FadeTransition fade = new FadeTransition();
            fade.setDuration(Duration.millis(300));
            fade.setNode(settingsPane);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play();
        }

        dashboardPane.setVisible(false);
        studentPane.setVisible(false);
        staffPane.setVisible(false);
        gradesPane.setVisible(false);
    }

    @FXML
    void openGrade(ActionEvent event) {
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

        if (staffBtn.getStyleClass().size() == 2 && staffIcon.getStyleClass().size() == 2) {

        } else if (staffBtn.getStyleClass().size() == 3 && staffIcon.getStyleClass().size() == 3) {
            staffBtn.getStyleClass().remove(2);
            staffIcon.getStyleClass().remove(2);
        }

        if (settingBtn.getStyleClass().size() == 2 && settingsIcon.getStyleClass().size() == 2) {

        } else if (settingBtn.getStyleClass().size() == 3 && settingsIcon.getStyleClass().size() == 3) {
            settingBtn.getStyleClass().remove(2);
            settingsIcon.getStyleClass().remove(2);
        }

        if (gradeBtn.getStyleClass().toString().contains("active") && gradeIcon.getStyleClass().toString().contains("iconActive")) {

        } else {
            gradeBtn.getStyleClass().add("active");
            gradeIcon.getStyleClass().add("iconActive");
            gradesPane.setVisible(true);
            FadeTransition fade = new FadeTransition();
            fade.setDuration(Duration.millis(300));
            fade.setNode(gradesPane);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play();
        }

        dashboardPane.setVisible(false);
        studentPane.setVisible(false);
        staffPane.setVisible(false);
        settingsPane.setVisible(false);
    }

    public void populateComboBoxes() {
        ObservableList genders = FXCollections.observableArrayList(
                "Male", "Female");
        selectGender.setItems(genders);
        selectTeacherGender.setItems(genders);
        ObservableList classes = FXCollections.observableArrayList(
                "JSS1", "JSS2", "JSS3", "SS1", "SS2", "SS3");
        selectClass.setItems(classes);
        ObservableList departments = FXCollections.observableArrayList(
                "Science", "Art", "Commercial");
        selectDepartment.setItems(departments);
        ObservableList religions = FXCollections.observableArrayList(
                "Christianity", "Islamism", "Others");
        selectReligion.setItems(religions);
            employeeReligion.setItems(religions);
        ObservableList qualifications = FXCollections.observableArrayList(
                "PHD", "BSC", "HND", "OND", "SSCE");
        selectQualification.setItems(qualifications);
        ObservableList maritalStats = FXCollections.observableArrayList(
                "Married", "Single", "Divorced", "Seperated");
        selectMaritalStatus.setItems(maritalStats);
        ObservableList designation = FXCollections.observableArrayList(
                "Teaching", "Non Teaching");
        selectDesignation.setItems(designation);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateComboBoxes();
        refreshStudentTable();
        refreshStaffTable();
       
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
    private void addTeacher(ActionEvent event) {
        addTeacherPane.setVisible(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(addTeacherPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    @FXML
    private void backFromAddTeacher() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(addTeacherPane);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((ActionEvent event) -> {
            addTeacherPane.setVisible(false);
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

    @FXML
    private void saveStudentDetails(ActionEvent event) throws SQLException, FileNotFoundException {

        Statement sta = da.getConnection().createStatement();
        String query = "Select ClassId From Students.Class WHERE ClassName ='" + selectClass.getValue().toString() + "'";
        ResultSet re = sta.executeQuery(query);
        int id = 0;
        while (re.next()) {
            id = re.getInt("ClassId");
        }
    
        if (selectedFile == null) {
//                String path = selectedFile.getPath();
            File file = new File("src/user.png");
            selectedFile = file;
            guardianFile = selectedFile;
            mainFile = selectedFile;
            Image image = new Image(selectedFile.toURI().toString());
            avatarStudView.setImage(image);
            avatarGuardView.setImage(image);
        }
        FileInputStream fis = new FileInputStream(mainFile);
        FileInputStream fis1 = new FileInputStream(guardianFile);
        PreparedStatement statement = da.getConnection().prepareStatement("insert into students.StudentDetails(StudentReg,FirstName,MiddleName,LastName,"
                + "DOB,Gender,Class,Department,Religion,"
                + "GuardianName,GuardianPhone,GuardianAddress,GuardianEmail,Nationality,GuardianOccupation,StudentImage,GuardianImage)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        statement.setString(1, studentAdmissionNumber.getText());
        statement.setString(2, studentFirstName.getText());
        statement.setString(3, studentMiddleName.getText());
        statement.setString(4, studentLastName.getText());
        statement.setString(5, studentDateOfBirth.getValue().toString());
        statement.setString(6, selectGender.getValue().toString());
        statement.setInt(7, id);
        statement.setString(8, selectDepartment.getValue().toString());
        statement.setString(9, selectReligion.getValue().toString());
        statement.setString(10, guardianName.getText());
        statement.setString(11, guardianPhone.getText());
        statement.setString(12, residence.getText());
        statement.setString(13, guardianMail.getText());
        statement.setString(14, nationality.getText());
        statement.setString(15, guardianOccupation.getText());
        statement.setBinaryStream(16, (InputStream) fis, (int) mainFile.length());
        statement.setBinaryStream(17, (InputStream) fis1, (int) guardianFile.length());
        int s = statement.executeUpdate();
        if (s > 0) {
            Notifications notify = Notifications.create()
                    .graphic(new ImageView(successImg))
                    .hideAfter(Duration.seconds(5))
                    .title("Success")
                    .text("Uploaded Succesfully")
                    .position(Pos.TOP_CENTER);
            notify.show();
        } else {
            Notifications notify = Notifications.create()
                    .graphic(new ImageView(errorImg))
                    .hideAfter(Duration.seconds(5))
                    .title("Failed")
                    .text("Uploaded Unsuccesfully")
                    .position(Pos.TOP_CENTER);
            notify.show();
        }
        refreshStudentTable();
    }
    private void populateStudentTable(){
         try {
            da.dbConnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection con = da.getConnection();
            ResultSet res = con.createStatement().executeQuery("SELECT StudentReg,FirstName+' '+MiddleName+' '+LastName AS 'FullName',DOB,Gender,ClassName,GuardianAddress,PaymentStatus FROM Students.vwStudentsInfo;");
            while (res.next()) {
                obs.add(new AdminStudentsTable(res.getString("StudentReg"), res.getString("FullName"),
                        res.getString("DOB"), res.getString("Gender"), res.getString("ClassName"),
                        res.getString("GuardianAddress"), res.getString("PaymentStatus")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        registrationNumber.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));
        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        studentClass.setCellValueFactory(new PropertyValueFactory<>("studentClass"));
        residentialAddress.setCellValueFactory(new PropertyValueFactory<>("residentialAddress"));
        paymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        adStudentTable.setItems(obs);

        adStudentTable.setOnMouseClicked((event) -> {
            Connection con;
            String name=adStudentTable.getSelectionModel().getSelectedItem().getFullName();
            String stuPayment=adStudentTable.getSelectionModel().getSelectedItem().getPaymentStatus();
            studentName.setText(name);
            studentPaymentStatus.setText(stuPayment);
            try {
                con = da.getConnection();
                String id = adStudentTable.getSelectionModel().getSelectedItem().getRegistrationNumber();
                
                PreparedStatement ps = con.prepareStatement("Select StudentImage from Students.vwStudentsInfo WHERE StudentReg= ?");
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    InputStream ips = rs.getBinaryStream(1);
                    image = new Image(ips, stuImage.getHeight(), stuImage.getWidth(), true, true);
                    stuImage.setImage(image);
                }

            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    @FXML
    private void saveEmployeeDetails(ActionEvent event) throws SQLException, FileNotFoundException {
        
        if (selectedFile1 == null) {
            File file = new File("src/user.png");
            selectedFile1 = file;
            employeeFile = selectedFile1;
            Image image = new Image(selectedFile1.toURI().toString());
            avatarEmployeeView.setImage(image);
        }
        FileInputStream fish = new FileInputStream(employeeFile);
        PreparedStatement statement = da.getConnection().prepareStatement("insert into staffs.StaffDetails(EmployeeNo,FirstName,MiddleName,LastName,"
                + "DOB,Gender,Nationality,maritalStatus,Phone,EmailAddress,ResidentialAddress,Religion,"
                + "Qualifications,StaffDesignation,Image)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        statement.setString(1, employeeID.getText());
        statement.setString(2, employeeFirstName.getText());
        statement.setString(3, employeeMiddleName.getText());
        statement.setString(4, employeeLastName.getText());
        statement.setString(5, employeDOB.getValue().toString());
        statement.setString(6,selectTeacherGender.getValue().toString());
        statement.setString(7, employeeNationality.getText());
        statement.setString(8, selectMaritalStatus.getValue().toString());
        statement.setString(9, employeePhoneNumber.getText());
        statement.setString(10, employeeEmail.getText());
        statement.setString(11, employeeResidence.getText());
        statement.setString(12, employeeReligion.getValue().toString());
        statement.setString(13, selectQualification.getValue().toString());
        statement.setString(14, selectDesignation.getValue().toString());
        statement.setBinaryStream(15, (InputStream) fish, (int) employeeFile.length());
       
        int s = statement.executeUpdate();
        if (s > 0) {
            Notifications notify = Notifications.create()
                    .graphic(new ImageView(successImg))
                    .hideAfter(Duration.seconds(5))
                    .title("Success")
                    .text("Uploaded Succesfully")
                    .position(Pos.TOP_CENTER);
            notify.show();
        } else {
            Notifications notify = Notifications.create()
                    .graphic(new ImageView(errorImg))
                    .hideAfter(Duration.seconds(5))
                    .title("Failed")
                    .text("Uploaded Unsuccesfully")
                    .position(Pos.TOP_CENTER);
            notify.show();
        }
        refreshStaffTable();
    }

    @FXML
    private void chooseEmployeeImage(ActionEvent event) {
         fileChooser.setTitle("Open Image");

        //set extension filter
        try {
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All images", "*.jpg", "*.jpeg", "*.png", "*.gif"));
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        //show open file dialog
        fileChooser.getInitialDirectory();
        selectedFile1 = fileChooser.showOpenDialog(null);

        try {
            if (selectedFile1 != null) {
                String path = selectedFile1.getPath();
                selectedFile1 = new File(path);
                employeeFile = selectedFile1;
                Image image = new Image(selectedFile1.toURI().toString());
                avatarEmployeeView.setImage(image);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     private void populateStaffTable(){
         try {
            da.dbConnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection con = da.getConnection();
            ResultSet res = con.createStatement().executeQuery("SELECT EmployeeNo,FirstName+' '+MiddleName+' '+LastName AS 'FullName',DOB,Gender,Qualifications,ResidentialAddress,StaffDesignation FROM Staffs.StaffDetails;");
            while (res.next()) {
                obs1.add(new TeachersTable(res.getString("EmployeeNo"), res.getString("FullName"),
                        res.getString("DOB"), res.getString("Gender"), res.getString("Qualifications"),
                        res.getString("ResidentialAddress"), res.getString("StaffDesignation")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        teacherID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        teacherFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        teacherDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        teacherGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        teacherQualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        teacherAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        teacherDesignation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        teachersTable.setItems(obs1);

       teachersTable.setOnMouseClicked((event) -> {
        String name = teachersTable.getSelectionModel().getSelectedItem().getFullName();
        teacherName.setText(name);

            Connection con;
            try {
                con = da.getConnection();
                String id = teachersTable.getSelectionModel().getSelectedItem().getEmployeeID();
                PreparedStatement ps = con.prepareStatement("Select Image from Staffs.StaffDetails WHERE EmployeeNo= ?");
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    InputStream ips = rs.getBinaryStream(1);
                    image = new Image(ips, teacherImage.getHeight(),teacherImage.getWidth(), true, true);
                   teacherImage.setImage(image);
//               }

                    if (teacherImage.isVisible()) {
                        System.out.println("Teacher's image is not visible");
                    } else {
                        System.out.println("teacher's image is not visible");
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }
     private void refreshStaffTable(){
         teachersTable.getItems().clear();
        obs1.removeAll(obs1);
        populateStaffTable();
     }
    private void refreshStudentTable(){
        adStudentTable.getItems().clear();
        obs.removeAll(obs);
        populateStudentTable();
    }
}
