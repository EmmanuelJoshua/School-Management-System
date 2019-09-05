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
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import school.management.system.demoDatabase.Database;
import school.management.system.tables.AdminStudentsTable;
import org.controlsfx.control.Notifications;
import school.management.system.tables.TeachersTable;
import java.util.regex.Pattern;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.input.KeyEvent;
import java.util.Calendar;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

/**
 *
 * @author DELL
 */
public class DashboardController implements Initializable {

    public Image errorImg = new Image("/school/management/system/images/cross.png");
    public Image successImg = new Image("/school/management/system/images/checked.png");

//    private static final String NAME = "(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{2,}";
    private static final String NAME = "(?=^[^-':\\n]*[-':]{0,1}[^-':\\n]*$)^[A-Z][-':\\w ]{4,30}$";
    private static final String PHONE = "\\d{11}";
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public String where = "";
    String genderSelect;

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
    private StackPane logoutPane;
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
    private JFXComboBox nationality;
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
    private TableColumn<TeachersTable, String> teacherID;
    @FXML
    private TableColumn<TeachersTable, String> teacherFullName;
    @FXML
    private TableColumn<TeachersTable, String> teacherDob;
    @FXML
    private TableColumn<TeachersTable, String> teacherGender;
    @FXML
    private TableColumn<TeachersTable, String> teacherQualification;
    @FXML
    private TableColumn<TeachersTable, String> teacherAddress;
    @FXML
    private TableColumn<TeachersTable, String> teacherDesignation;
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
    private TextField OldPasswordTxtF;
    @FXML
    private TextField NewPasswordTxtF;
    @FXML
    private TextField ConfirmPasswordTxtF;
    @FXML
    private Text stuMaleText;
    @FXML
    private Text stuFemaleText;
    @FXML
    private Text stuTotalText;

    String OldPassword, NewPassword, ConfirmedPassword, user1, pass1;
    Connection conn;
    @FXML
    private TextField studentSearch;
    @FXML
    private TextField teacherSearch;
    @FXML
    private Text staffMaleGenderCount;
    @FXML
    private Text staffFemaleGenderCount;
    @FXML
    private Text staffTotalGenderCount;
    @FXML
    private JFXButton staffResetBtn;
    @FXML
    private AnchorPane parentsPane;
    @FXML
    private JFXButton parentBtn;
    @FXML
    private MaterialDesignIconView parentIcon;
    @FXML
    private AnchorPane coursesPane;
    @FXML
    private JFXButton courseBtn;
    @FXML
    private MaterialDesignIconView courseIcon;
    @FXML
    private JFXListView<Label> view;
    @FXML
    private JFXListView<Label> leaderList;
    @FXML
    private StackPane editStudentModals;
    @FXML
    private StackPane editTeachersModals;
    @FXML
    private StackPane editParentsModals;
    @FXML
    private StackPane deleteStudentPane;
    @FXML
    private JFXButton deleteStudentYes;
    @FXML
    private JFXButton deleteStudentNo;
    @FXML
    private StackPane deleteStaffPane;
    @FXML
    private JFXButton deleteStaffYes;
    @FXML
    private JFXButton deleteStaffNo;

    public DashboardController() {
        try {
            this.conn = Database.getConnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

        if (parentBtn.getStyleClass().size() == 2 && parentIcon.getStyleClass().size() == 2) {

        } else if (parentBtn.getStyleClass().size() == 3 && parentIcon.getStyleClass().size() == 3) {
            parentBtn.getStyleClass().remove(2);
            parentIcon.getStyleClass().remove(2);
        }

        if (courseBtn.getStyleClass().size() == 2 && courseIcon.getStyleClass().size() == 2) {

        } else if (courseBtn.getStyleClass().size() == 3 && courseIcon.getStyleClass().size() == 3) {
            courseBtn.getStyleClass().remove(2);
            courseIcon.getStyleClass().remove(2);
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
        parentsPane.setVisible(false);
        settingsPane.setVisible(false);
        gradesPane.setVisible(false);
        coursesPane.setVisible(false);
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

        if (parentBtn.getStyleClass().size() == 2 && parentIcon.getStyleClass().size() == 2) {

        } else if (parentBtn.getStyleClass().size() == 3 && parentIcon.getStyleClass().size() == 3) {
            parentBtn.getStyleClass().remove(2);
            parentIcon.getStyleClass().remove(2);
        }

        if (courseBtn.getStyleClass().size() == 2 && courseIcon.getStyleClass().size() == 2) {

        } else if (courseBtn.getStyleClass().size() == 3 && courseIcon.getStyleClass().size() == 3) {
            courseBtn.getStyleClass().remove(2);
            courseIcon.getStyleClass().remove(2);
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
        parentsPane.setVisible(false);
        coursesPane.setVisible(false);
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

        if (parentBtn.getStyleClass().size() == 2 && parentIcon.getStyleClass().size() == 2) {

        } else if (parentBtn.getStyleClass().size() == 3 && parentIcon.getStyleClass().size() == 3) {
            parentBtn.getStyleClass().remove(2);
            parentIcon.getStyleClass().remove(2);
        }

        if (courseBtn.getStyleClass().size() == 2 && courseIcon.getStyleClass().size() == 2) {

        } else if (courseBtn.getStyleClass().size() == 3 && courseIcon.getStyleClass().size() == 3) {
            courseBtn.getStyleClass().remove(2);
            courseIcon.getStyleClass().remove(2);
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
        parentsPane.setVisible(false);
        coursesPane.setVisible(false);
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

        if (parentBtn.getStyleClass().size() == 2 && parentIcon.getStyleClass().size() == 2) {

        } else if (parentBtn.getStyleClass().size() == 3 && parentIcon.getStyleClass().size() == 3) {
            parentBtn.getStyleClass().remove(2);
            parentIcon.getStyleClass().remove(2);
        }

        if (courseBtn.getStyleClass().size() == 2 && courseIcon.getStyleClass().size() == 2) {

        } else if (courseBtn.getStyleClass().size() == 3 && courseIcon.getStyleClass().size() == 3) {
            courseBtn.getStyleClass().remove(2);
            courseIcon.getStyleClass().remove(2);
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
        parentsPane.setVisible(false);
        coursesPane.setVisible(false);
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

        if (parentBtn.getStyleClass().size() == 2 && parentIcon.getStyleClass().size() == 2) {

        } else if (parentBtn.getStyleClass().size() == 3 && parentIcon.getStyleClass().size() == 3) {
            parentBtn.getStyleClass().remove(2);
            parentIcon.getStyleClass().remove(2);
        }

        if (courseBtn.getStyleClass().size() == 2 && courseIcon.getStyleClass().size() == 2) {

        } else if (courseBtn.getStyleClass().size() == 3 && courseIcon.getStyleClass().size() == 3) {
            courseBtn.getStyleClass().remove(2);
            courseIcon.getStyleClass().remove(2);
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
        parentsPane.setVisible(false);
        coursesPane.setVisible(false);
    }

    @FXML
    void openParents(ActionEvent event) {
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

        if (gradeBtn.getStyleClass().size() == 2 && gradeIcon.getStyleClass().size() == 2) {

        } else if (gradeBtn.getStyleClass().size() == 3 && gradeIcon.getStyleClass().size() == 3) {
            gradeBtn.getStyleClass().remove(2);
            gradeIcon.getStyleClass().remove(2);
        }

        if (courseBtn.getStyleClass().size() == 2 && courseIcon.getStyleClass().size() == 2) {

        } else if (courseBtn.getStyleClass().size() == 3 && courseIcon.getStyleClass().size() == 3) {
            courseBtn.getStyleClass().remove(2);
            courseIcon.getStyleClass().remove(2);
        }

        if (parentBtn.getStyleClass().toString().contains("active") && parentIcon.getStyleClass().toString().contains("iconActive")) {

        } else {
            parentBtn.getStyleClass().add("active");
            parentIcon.getStyleClass().add("iconActive");
            parentsPane.setVisible(true);
            FadeTransition fade = new FadeTransition();
            fade.setDuration(Duration.millis(300));
            fade.setNode(parentsPane);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play();
        }

        dashboardPane.setVisible(false);
        studentPane.setVisible(false);
        staffPane.setVisible(false);
        settingsPane.setVisible(false);
        gradesPane.setVisible(false);
        coursesPane.setVisible(false);
    }

    @FXML
    void openCourses(ActionEvent event) {
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

        if (gradeBtn.getStyleClass().size() == 2 && gradeIcon.getStyleClass().size() == 2) {

        } else if (gradeBtn.getStyleClass().size() == 3 && gradeIcon.getStyleClass().size() == 3) {
            gradeBtn.getStyleClass().remove(2);
            gradeIcon.getStyleClass().remove(2);
        }

        if (parentBtn.getStyleClass().size() == 2 && parentIcon.getStyleClass().size() == 2) {

        } else if (parentBtn.getStyleClass().size() == 3 && parentIcon.getStyleClass().size() == 3) {
            parentBtn.getStyleClass().remove(2);
            parentIcon.getStyleClass().remove(2);
        }

        if (courseBtn.getStyleClass().toString().contains("active") && courseIcon.getStyleClass().toString().contains("iconActive")) {

        } else {
            courseBtn.getStyleClass().add("active");
            courseIcon.getStyleClass().add("iconActive");
            coursesPane.setVisible(true);
            FadeTransition fade = new FadeTransition();
            fade.setDuration(Duration.millis(300));
            fade.setNode(coursesPane);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play();
        }

        dashboardPane.setVisible(false);
        studentPane.setVisible(false);
        staffPane.setVisible(false);
        settingsPane.setVisible(false);
        gradesPane.setVisible(false);
        parentsPane.setVisible(false);

    }

    @FXML
    public void editStudent() {
        editStudentModals.setVisible(true);
        dashboardPane.setDisable(true);
        studentPane.setDisable(true);
        staffPane.setDisable(true);
        settingsPane.setDisable(true);
        gradesPane.setDisable(true);
        parentsPane.setDisable(true);
        coursesPane.setDisable(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(editStudentModals);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    @FXML
    public void deleteStudent() {
        deleteStudentPane.setVisible(true);
        dashboardPane.setDisable(true);
        studentPane.setDisable(true);
        staffPane.setDisable(true);
        settingsPane.setDisable(true);
        gradesPane.setDisable(true);
        parentsPane.setDisable(true);
        coursesPane.setDisable(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(deleteStudentPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    @FXML
    public void editTeacher() {
        editTeachersModals.setVisible(true);
        dashboardPane.setDisable(true);
        studentPane.setDisable(true);
        staffPane.setDisable(true);
        settingsPane.setDisable(true);
        gradesPane.setDisable(true);
        parentsPane.setDisable(true);
        coursesPane.setDisable(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(editTeachersModals);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    @FXML
    public void deleteTeacher() {
        deleteStaffPane.setVisible(true);
        dashboardPane.setDisable(true);
        studentPane.setDisable(true);
        staffPane.setDisable(true);
        settingsPane.setDisable(true);
        gradesPane.setDisable(true);
        parentsPane.setDisable(true);
        coursesPane.setDisable(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(deleteStaffPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    @FXML
    public void editParents() {
        editParentsModals.setVisible(true);
        dashboardPane.setDisable(true);
        studentPane.setDisable(true);
        staffPane.setDisable(true);
        settingsPane.setDisable(true);
        gradesPane.setDisable(true);
        parentsPane.setDisable(true);
        coursesPane.setDisable(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(editParentsModals);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    @FXML
    public void exitStudentEditModal() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(editStudentModals);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((ActionEvent event) -> {
            dashboardPane.setDisable(false);
            studentPane.setDisable(false);
            staffPane.setDisable(false);
            settingsPane.setDisable(false);
            gradesPane.setDisable(false);
            parentsPane.setDisable(false);
            coursesPane.setDisable(false);
            editStudentModals.setVisible(false);
        });
    }

    @FXML
    public void exitTeacherEditModal() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(editTeachersModals);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((ActionEvent event) -> {
            dashboardPane.setDisable(false);
            studentPane.setDisable(false);
            staffPane.setDisable(false);
            settingsPane.setDisable(false);
            gradesPane.setDisable(false);
            parentsPane.setDisable(false);
            coursesPane.setDisable(false);
            editTeachersModals.setVisible(false);
        });
    }

    @FXML
    public void exitParentEditModal() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(editParentsModals);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((ActionEvent event) -> {
            dashboardPane.setDisable(false);
            studentPane.setDisable(false);
            staffPane.setDisable(false);
            settingsPane.setDisable(false);
            gradesPane.setDisable(false);
            parentsPane.setDisable(false);
            coursesPane.setDisable(false);
            editParentsModals.setVisible(false);
        });
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
                "Science", "Art", "Commercial", "None");
        selectDepartment.setItems(departments);
        ObservableList religions = FXCollections.observableArrayList(
                "Christianity", "Islamism", "Others");
        selectReligion.setItems(religions);
        employeeReligion.setItems(religions);
        ObservableList qualifications = FXCollections.observableArrayList(
                "None", "PHD", "BSC", "HND", "OND", "SSCE");
        selectQualification.setItems(qualifications);
        ObservableList maritalStats = FXCollections.observableArrayList(
                "Married", "Single", "Divorced", "Seperated");
        selectMaritalStatus.setItems(maritalStats);
        ObservableList designation = FXCollections.observableArrayList(
                "Teaching", "Non Teaching");
        selectDesignation.setItems(designation);
        ObservableList nationality1 = FXCollections.observableArrayList(
                "Nigerian", "Ghanian", "Cameroonian", "Benin");
        nationality.setItems(nationality1);
    }

    public void birthdays() {
        String[] names = {"Isaac Ogunleye", "Joy Ajiboye", "Olumide Awodeji", "Daniel Odey", "Edmund Giwa"};
//        String[] dates = {"1/1/1","2/2/2","3/3/3","4/4/4","5/5/5"};
        for (int i = 0; i < names.length; i++) {

            try {
                Image img = new Image(new FileInputStream("src/school/management/system/images/birthday.png"));
                Label lbl = new Label(names[i]);
                ImageView imgView = new ImageView(img);
                imgView.setFitHeight(25);
                imgView.setFitWidth(25);
                lbl.setGraphicTextGap(10);
                lbl.setGraphic(imgView);
                view.getItems().add(lbl);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (int i = 0; i < 3; i++) {

            try {
                Image img = new Image(new FileInputStream("src/school/management/system/images/icons8_Star_Filled_48px.png"));
                Label lbl = new Label(names[i]);
                ImageView imgView = new ImageView(img);
                imgView.setFitHeight(25);
                imgView.setFitWidth(25);
                lbl.setGraphicTextGap(10);
                lbl.setGraphic(imgView);
                leaderList.getItems().add(lbl);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        birthdays();
        populateComboBoxes();
//        refreshStudentTable();
//        refreshStaffTable();
//        try {
//            loadTeacherTableData();
//        } catch (SQLException ex) {
//            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        try {
//            studentTableData();
//        } catch (SQLException ex) {
//            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        validators();
//        employeeValidators();

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
                dashboardPane.setDisable(false);
                studentPane.setDisable(false);
                staffPane.setDisable(false);
                settingsPane.setDisable(false);
                gradesPane.setDisable(false);
                parentsPane.setDisable(false);
                coursesPane.setDisable(false);
                logoutPane.setVisible(false);
            });
        });

        deleteStudentYes.setOnAction((ActionEvent event) -> {

        });

        deleteStudentNo.setOnAction((ActionEvent event) -> {
            FadeTransition fade = new FadeTransition();
            fade.setDuration(Duration.millis(300));
            fade.setNode(deleteStudentPane);
            fade.setFromValue(1);
            fade.setToValue(0);
            fade.play();
            fade.setOnFinished((ActionEvent event1) -> {
                dashboardPane.setDisable(false);
                studentPane.setDisable(false);
                staffPane.setDisable(false);
                settingsPane.setDisable(false);
                gradesPane.setDisable(false);
                parentsPane.setDisable(false);
                coursesPane.setDisable(false);
                deleteStudentPane.setVisible(false);
            });
        });

        deleteStaffYes.setOnAction((ActionEvent event) -> {

        });

        deleteStaffNo.setOnAction((ActionEvent event) -> {
            FadeTransition fade = new FadeTransition();
            fade.setDuration(Duration.millis(300));
            fade.setNode(deleteStaffPane);
            fade.setFromValue(1);
            fade.setToValue(0);
            fade.play();
            fade.setOnFinished((ActionEvent event1) -> {
                dashboardPane.setDisable(false);
                studentPane.setDisable(false);
                staffPane.setDisable(false);
                settingsPane.setDisable(false);
                gradesPane.setDisable(false);
                parentsPane.setDisable(false);
                coursesPane.setDisable(false);
                deleteStaffPane.setVisible(false);
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
        dashboardPane.setDisable(true);
        studentPane.setDisable(true);
        staffPane.setDisable(true);
        settingsPane.setDisable(true);
        gradesPane.setDisable(true);
        parentsPane.setDisable(true);
        coursesPane.setDisable(true);
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

        Scene scene = new Scene(parent);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.initOwner(((Stage) mainDashPane.getScene().getWindow()));
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    // Save student button
    @FXML
    private void saveStudentDetails(ActionEvent event) throws SQLException, FileNotFoundException {

        if (selectGender.getValue() == "Male") {
            genderSelect = "Male";
        } else if (selectGender.getValue() == "Female") {
            genderSelect = "Female";
        } else {
            genderSelect = null;
        }

        // if validation returns true
        if (validateStudentMethod()) {
            Statement sta = da.getConnection().createStatement();
            String query = "Select ClassId From Students.Class WHERE ClassName ='" + selectClass.getValue().toString() + "'";
            ResultSet re = sta.executeQuery(query);
            int id = 0;
            while (re.next()) {
                id = re.getInt("ClassId");
            }

            if (selectedFile == null) {
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
            statement.setString(14, nationality.getValue().toString());
            statement.setString(15, guardianOccupation.getText());
            statement.setBinaryStream(16, (InputStream) fis, (int) mainFile.length());
            statement.setBinaryStream(17, (InputStream) fis1, (int) guardianFile.length());
            int s = statement.executeUpdate();
            if (s != 0) {
                Notifications notify = Notifications.create()
                        .graphic(new ImageView(successImg))
                        .hideAfter(Duration.seconds(8))
                        .title("Success")
                        .text("Uploaded Succesfully")
                        .position(Pos.TOP_CENTER);
                notify.show();
                studentResetBtn(event);
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
        } else {
            Notifications notify = Notifications.create()
                    .graphic(new ImageView(errorImg))
                    .title("Error!")
                    .text("One or morefields are empty")
                    .position(Pos.BOTTOM_RIGHT)
                    .hideAfter(Duration.seconds(5));
            notify.show();
        }
    }

    private void populateStudentTable() {
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
            String name = adStudentTable.getSelectionModel().getSelectedItem().getFullName();
            String stuPayment = adStudentTable.getSelectionModel().getSelectedItem().getPaymentStatus();
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

        if (selectTeacherGender.getValue() == "Male") {
            genderSelect = "Male";
        } else if (selectTeacherGender.getValue() == "Female") {
            genderSelect = "Female";
        } else {
            genderSelect = null;
        }

        // if validation returns true
        if (validateTeachersMethod()) {
//            Statement sta = da.getConnection().createStatement();
//            String query = "Select StaffID From Staffs.StaffDetails WHERE ClassName ='" + selectClass.getValue().toString() + "'";
//            ResultSet re = sta.executeQuery(query);
//            int id = 0;
//            while (re.next()) {
//                id = re.getInt("StaffID");
//            }

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
            statement.setString(6, selectTeacherGender.getValue().toString());
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
                staffResetBtn(event);
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
        } else {
            Notifications notify = Notifications.create()
                    .graphic(new ImageView(errorImg))
                    .title("Error!")
                    .text("One or morefields are empty")
                    .position(Pos.BOTTOM_RIGHT)
                    .hideAfter(Duration.seconds(5));
            notify.show();
        }
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

    private void populateStaffTable() {
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
                    image = new Image(ips, teacherImage.getHeight(), teacherImage.getWidth(), true, true);
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

    private void refreshStaffTable() {
        teachersTable.getItems().clear();
        obs1.removeAll(obs1);
        populateStaffTable();
    }

    private void refreshStudentTable() {
        adStudentTable.getItems().clear();
        obs.removeAll(obs);
        populateStudentTable();
    }

    public void saveChangesAction(ActionEvent event) {
        try {
            ResultSet res = conn.createStatement().executeQuery("select * from LogInDetails");
            while (res.next()) {
                int id = res.getInt("Id");
                user1 = res.getString("Username");
                pass1 = res.getString("Password");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (OldPasswordTxtF.getText().isEmpty() && NewPasswordTxtF.getText().isEmpty() && ConfirmPasswordTxtF.getText().isEmpty()) {
            Notifications notify = Notifications.create()
                    .graphic(new ImageView(errorImg))
                    .title("ERROR")
                    .text("All fields are Empty")
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(3));
            notify.show();
        } else {
            if (OldPasswordTxtF.getText().equals(pass1)) {
                if (NewPasswordTxtF.getText().equals(ConfirmPasswordTxtF.getText())) {
                    try {
                        String query = "Update LogInDetails SET Password = '" + NewPasswordTxtF.getText() + "' where id = 1";
                        PreparedStatement pst = Database.getConnect().prepareStatement(query);
                        pst.execute();
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    Notifications notify = Notifications.create()
                            .graphic(new ImageView(errorImg))
                            .title("ERROR")
                            .text("New Password does not Match")
                            .position(Pos.TOP_CENTER)
                            .hideAfter(Duration.seconds(3));
                    notify.show();
                }
            } else {
                Notifications notify = Notifications.create()
                        .graphic(new ImageView(errorImg))
                        .title("ERROR")
                        .text("Password is Incorrect")
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(3));
                notify.show();
            }
        }
    }

    @FXML
    private void saveAction(ActionEvent event) {
        try {
            ResultSet res = conn.createStatement().executeQuery("select * from LogInDetails");
            while (res.next()) {
                int id = res.getInt("Id");
                user1 = res.getString("Username");
                pass1 = res.getString("Password");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (OldPasswordTxtF.getText().isEmpty() && NewPasswordTxtF.getText().isEmpty() && ConfirmPasswordTxtF.getText().isEmpty()) {
            Notifications notify = Notifications.create()
                    .graphic(new ImageView(errorImg))
                    .title("ERROR")
                    .text("All fields are Empty")
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(3));
            notify.show();
        } else {
            if (OldPasswordTxtF.getText().equals(pass1)) {
                if (NewPasswordTxtF.getText().equals(ConfirmPasswordTxtF.getText())) {
                    try {
                        String query = "Update LogInDetails SET Password = '" + NewPasswordTxtF.getText() + "' where id = 1";
                        PreparedStatement pst = conn.prepareStatement(query);
                        pst.execute();
                        Notifications notify = Notifications.create()
                                .graphic(new ImageView(successImg))
                                .title("SUCCESS")
                                .text("Password Changed Successfully")
                                .position(Pos.TOP_CENTER)
                                .hideAfter(Duration.seconds(3));
                        notify.show();
                    } catch (SQLException ex) {
                        Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    Notifications notify = Notifications.create()
                            .graphic(new ImageView(errorImg))
                            .title("ERROR")
                            .text("New Password does not Match")
                            .position(Pos.TOP_CENTER)
                            .hideAfter(Duration.seconds(3));
                    notify.show();
                }
            } else {
                Notifications notify = Notifications.create()
                        .graphic(new ImageView(errorImg))
                        .title("ERROR")
                        .text("Password is Incorrect")
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(3));
                notify.show();
            }
        }
    }

    @FXML
    // Reset button for student
    private void studentResetBtn(ActionEvent event) {
        // Set all fields to empty or null on click
        studentFirstName.setText("");
        studentLastName.setText("");
        studentMiddleName.setText("");
        guardianName.setText("");
        studentDateOfBirth.setValue(null);
        guardianMail.setText("");
        guardianOccupation.setText("");
        residence.setText("");
        guardianPhone.setText("");
        selectClass.setValue(null);
        selectDepartment.setValue(null);
        selectGender.setValue(null);
        selectReligion.setValue(null);
        studentAdmissionNumber.setText("");
        nationality.setValue("");

        File file = new File("src/user.png");
        selectedFile = file;
        guardianFile = selectedFile;
        mainFile = selectedFile;
        Image image = new Image(selectedFile.toURI().toString());
        avatarStudView.setImage(image);
        avatarGuardView.setImage(image);
    }

    // Validator method for student
    public void validators() {

    }

    //validations for Staffs
    public void employeeValidators() {

        RequiredFieldValidator rFValidator1;
        rFValidator1 = new RequiredFieldValidator();
        rFValidator1.setMessage("Empty Field");
        rFValidator1.setIcon(new ImageView(getClass().getResource("/school/management/system/images/warning.png").toString()));

        employeeFirstName.getValidators().add(rFValidator1);
        employeeFirstName.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                employeeFirstName.validate();
            }
        });

        employeeFirstName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                employeeFirstName.resetValidation();
            } else if (newValue.isEmpty()) {
                employeeFirstName.validate();
            }
        });

        RequiredFieldValidator rFValidator2;
        rFValidator2 = new RequiredFieldValidator();
        rFValidator2.setMessage("Empty Field");
        rFValidator2.setIcon(new ImageView(getClass().getResource("/school/management/system/images/warning.png").toString()));

        employeeLastName.getValidators().add(rFValidator2);
        employeeLastName.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                employeeLastName.validate();
            }
        });

        employeeLastName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                employeeLastName.resetValidation();
            } else if (newValue.isEmpty()) {
                employeeLastName.validate();
            }
        });

        RequiredFieldValidator rFValidator3;
        rFValidator3 = new RequiredFieldValidator();
        rFValidator3.setMessage("Empty Field");
        rFValidator3.setIcon(new ImageView(getClass().getResource("/school/management/system/images/warning.png").toString()));

        employeeMiddleName.getValidators().add(rFValidator3);
        employeeMiddleName.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                employeeMiddleName.validate();
            }
        });

        employeeMiddleName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                employeeMiddleName.resetValidation();
            } else if (newValue.isEmpty()) {
                employeeMiddleName.validate();
            }
        });

        RequiredFieldValidator rFValidator4;
        rFValidator4 = new RequiredFieldValidator();
        rFValidator4.setMessage("Empty Field");
        rFValidator4.setIcon(new ImageView(getClass().getResource("/school/management/system/images/warning.png").toString()));

        employeeID.getValidators().add(rFValidator4);
        employeeID.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                employeeID.validate();
            }
        });

        employeeID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                employeeID.resetValidation();
            } else if (newValue.isEmpty()) {
                employeeID.validate();
            }
        });

        RequiredFieldValidator rFValidator5;
        rFValidator5 = new RequiredFieldValidator();
        rFValidator5.setMessage("Empty Field");
        rFValidator5.setIcon(new ImageView(getClass().getResource("/school/management/system/images/warning.png").toString()));

        employeeNationality.getValidators().add(rFValidator5);
        employeeNationality.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                employeeNationality.validate();
            }
        });

        employeeNationality.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                employeeNationality.resetValidation();
            } else if (newValue.isEmpty()) {
                employeeNationality.validate();
            }
        });

        RequiredFieldValidator rFValidator6;
        rFValidator6 = new RequiredFieldValidator();
        rFValidator6.setMessage("Empty Field");
        rFValidator6.setIcon(new ImageView(getClass().getResource("/school/management/system/images/warning.png").toString()));

        employeeResidence.getValidators().add(rFValidator6);
        employeeResidence.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                employeeResidence.validate();
            }
        });

        employeeResidence.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                employeeResidence.resetValidation();
                rFValidator6.setIcon(new ImageView(getClass().getResource("/school/management/system/images/warning.png").toString()));
            } else if (newValue.isEmpty()) {
                employeeResidence.validate();
            }
        });

        RequiredFieldValidator rFValidator7;
        rFValidator7 = new RequiredFieldValidator();
        rFValidator7.setMessage("Empty Field");
        rFValidator7.setIcon(new ImageView(getClass().getResource("/school/management/system/images/warning.png").toString()));

        employeeEmail.getValidators().add(rFValidator7);
        employeeEmail.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                employeeEmail.validate();
            }
        });

        employeeEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                employeeEmail.resetValidation();
            } else if (newValue.isEmpty()) {
                employeeEmail.validate();
            }
        });

        NumberValidator numberValidator;
        numberValidator = new NumberValidator();
        RequiredFieldValidator phonenum = new RequiredFieldValidator();

        numberValidator.setIcon(new ImageView(getClass().getResource("/school/management/system/images/warning.png").toString()));

        employeePhoneNumber.getValidators().add(phonenum);
        employeePhoneNumber.getValidators().add(numberValidator);

        employeePhoneNumber.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                phonenum.setMessage("Empty Field");
                phonenum.setIcon(new ImageView(getClass().getResource("/school/management/system/images/warning.png").toString()));
                System.out.println("Empty field");
                employeePhoneNumber.validate();
            } else {
                System.out.println("just lost focus");
            }
        });

        employeePhoneNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                employeePhoneNumber.resetValidation();
                if ((!employeePhoneNumber.getText().matches(PHONE)) && (employeePhoneNumber.getText().length() != 11)) {
                    numberValidator.setMessage("Numbers must be 11 digits only");
                    System.out.println("i said eleven");
                    employeePhoneNumber.validate();
                } else {
                    employeePhoneNumber.resetValidation();
                    System.out.println("Correct Input");
                }
            } else if (newValue.isEmpty()) {
                employeePhoneNumber.validate();
            }
        });

        boolean isMyComboBoxEmpty = selectQualification.getSelectionModel().isEmpty();

        employeePhoneNumber.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!"0123456789".contains(event.getCharacter())) {
//                          RequiredFieldValidator eventFilter = new RequiredFieldValidator();
//                                  employeePhoneNumber.getValidators().add(eventFilter);
//                                  eventFilter.setMessage("Input Only Numbers");
//                    keyTypedErrorMsg();
                    event.consume();
                }
            }
        });

//        employeePhoneNumber.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (!newValue.isEmpty()) {
//                employeePhoneNumber.resetValidation();
//            } else if (newValue.isEmpty()) {
//                employeePhoneNumber.validate();
//            }else if(employeePhoneNumber.getText().matches(PHONE)){
//                employeePhoneNumber.validate();
//            }
//        });
    }

//    void keyTypedErrorMsg() {
//        RequiredFieldValidator eventFilter = new RequiredFieldValidator();
//        employeePhoneNumber.getValidators().add(eventFilter);
//        eventFilter.setMessage("Input Only Numbers");
//    }
    // Validations for Staffs method on user input
    public boolean validateTeachersMethod() {
        // checks if the employee last name field is empty
        if ("".equals(employeeLastName.getText())) {
            return false;
        }

        // checks if the employee first name field is empty
        if ("".equals(employeeFirstName.getText())) {
            return false;
        }

        // checks if the employee middle name field is empty
        if ("".equals(employeeMiddleName.getText())) {
            return false;
        }

        // checks if the employee ID field is empty
        if ("".equals(employeeID.getText())) {
            return false;
        }

        // checks if the employee nationality field is empty
        if ("".equals(employeeNationality.getText())) {
            return false;
        }

        // checks if the employee residence field is empty
        if ("".equals(employeeResidence.getText())) {
            return false;
        }

        // checks if the employee email field is empty
        if ("".equals(employeeEmail.getText())) {
            return false;
        }

        // checks if the employee phone number field is empty
        if ("".equals(employeePhoneNumber.getText())) {
            return false;
        }

        // checks if the employee religion value is empty
        if ("".equals(employeeReligion.getValue().toString().isEmpty())) {
            return false;
        }

        // checks if the employee email value is empty
        if ("".equals(employeDOB.getValue().toString().isEmpty())) {
            return false;
        }

        // checks if the employee qualification value is empty
        if ("".equals(selectQualification.getValue().toString().isEmpty())) {
            return false;
        }

        // checks if the employee gender value is empty
        if ("".equals(selectTeacherGender.getValue().toString().isEmpty())) {
            return false;
        }

        // checks if the employee designation value is empty
        if ("".equals(selectDesignation.getValue().toString().isEmpty())) {
            return false;
        }

        // checks if the employee email mattches the specified email pattern
        if (!Pattern.matches(EMAIL_PATTERN, employeeEmail.getText())) {
            return false;
        }

        // checks if the employee phone number matches the specified phone generic pattern
        if (!Pattern.matches(PHONE, employeePhoneNumber.getText())) {
            return false;
        }

        // checks if the employee first name field matches the desired generic pattern
        if (!Pattern.matches(NAME, employeeFirstName.getText())) {
            return false;
        }

        // checks if the employee last name field matches the desired generic pattern
        if (!Pattern.matches(NAME, employeeLastName.getText())) {
            return false;
        }

        // checks if the employee middle name field matches the desired generic pattern
        if (!Pattern.matches(NAME, employeeMiddleName.getText())) {
            return false;
        }

        // else if all fields are entered correctly return false
        return true;
    }

    // Validation Method 2 for student on user input
    public boolean validateStudentMethod() {
        // checks if the first name field is empty
        if ("".equals(studentFirstName.getText())) {
            return false;
        }

        // checks if the last name field is empty
        if ("".equals(studentLastName.getText())) {
            return false;
        }

        // checks if the middle name field is empty
        if ("".equals(studentMiddleName.getText())) {
            return false;
        }

        // checks and returns false if the guardian field is empty
        if ("".equals(guardianName.getText())) {
            return false;
        }

        // checks if the guardian's Email field is empty
        if ("".equals(guardianMail.getText())) {
            return false;
        }

        // checks if the student's admin number is empty
        if ("".equals(studentAdmissionNumber.getText())) {
            return false;
        }

        // checks if the residence address is empty
        if ("".equals(residence.getText())) {
            return false;
        }

        //checks if the phone number field is empty
        if ("".equals(guardianPhone.getText())) {
            return false;
        }

        // checks if the guardian occupation field is empty
        if ("".equals(guardianOccupation.getText())) {
            return false;
        }

        // checks if the date of birth value is empty
        if ("".equals(studentDateOfBirth.getValue().toString())) {
            return false;
        }

        // checks if the students class field is empty
        if ("".equals(selectClass.getValue().toString())) {
            return false;
        }

        // checks if the student department field is empty
        if ("".equals(selectDepartment.getValue().toString())) {
            return false;
        }

        // checks if the students gender field is empty
        if ("".equals(selectGender.getValue().toString())) {
            return false;
        }

        // checks if the religion value field is empty
        if ("".equals(selectReligion.getValue().toString())) {
            return false;
        }

        // checks if the guardians email mattches the specified email pattern
        if (!Pattern.matches(EMAIL_PATTERN, guardianMail.getText())) {
            return false;
        }

        // checks if the guardians phone matches the specified phone generic pattern
        if (!Pattern.matches(PHONE, guardianPhone.getText())) {
            return false;
        }

        // checks if the first name field matches the desired generic pattern
        if (!Pattern.matches(NAME, studentFirstName.getText())) {
            return false;
        }

        // specifies if the middle name matches the desired generic pattern
        if (!Pattern.matches(NAME, studentLastName.getText())) {
            return false;
        }

        // specifies if the middle name matches the desired generic pattern
        if (!Pattern.matches(NAME, studentMiddleName.getText())) {
            return false;
        }

        // checks if the guardians name matches the desired generic pattern
        if (!Pattern.matches(NAME, guardianName.getText())) {
            return false;
        }

        // checks if the date entered is more than the current date which is invalid
        if (isDateValid("dd-mm-yyyy", currentDate("-"), studentDateOfBirth.getValue().toString()) == false) {
//    studentDateOfBirth.setValue(null);
            return false;
        }

        // else if all fields are entered correctly return false
        return true;
    }

    // Method for loading count on student Table data
    public void studentTableData() throws SQLException, ClassNotFoundException {
        Connection con;
        con = da.getConnection();
        Statement state = da.getConnection().createStatement();

        // query for selecting all the data on the students table
        String sql = "SELECT * FROM students.studentDetails ";
        // query for counting number of males in the student table
        String maleCount = "SELECT count(Gender) AS 'Count1' FROM students.studentDetails  where Gender = 'Male' ";
        // query for counting number of females on the students table
        String femaleCount = "SELECT count(Gender) AS 'Count2' FROM students.vwStudentsInfo where Gender = 'Female'";
        // query for counting total genders on the table
        String totalCount = "SELECT count(Gender) AS 'Count3' FROM students.vwStudentsInfo";

        ResultSet rrs1 = da.executeQuery(sql);
        ResultSet rrs = da.executeQuery(maleCount);
        ResultSet rrs2 = da.executeQuery(femaleCount);
        ResultSet rrs3 = da.executeQuery(totalCount);

        try {

            while (rrs1.next()) {
                String gen = rrs1.getString("gender");
            }

            while (rrs.next()) {

                String num = rrs.getString("count1");
                if (num.length() == 1) {
                    stuMaleText.setText("00" + num);
                } else if (num.length() == 2) {
                    stuMaleText.setText("0" + num);
                } else if (num.length() == 3) {
                    stuMaleText.setText(num);
                }
            }

            while (rrs2.next()) {
                String num = rrs2.getString("count2");
                if (num.length() == 1) {
                    stuFemaleText.setText("00" + num);
                } else if (num.length() == 2) {
                    stuFemaleText.setText("0" + num);
                } else if (num.length() == 3) {
                    stuFemaleText.setText(num);
                }
            }

            while (rrs3.next()) {
                String num = rrs3.getString("count3");
                if (num.length() == 1) {
                    stuTotalText.setText("00" + num);
                } else if (num.length() == 2) {
                    stuTotalText.setText("0" + num);
                } else if (num.length() == 3) {
                    stuTotalText.setText(num);
                }
            }
            rrs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method for loading count on staff Table
    public void loadTeacherTableData() throws SQLException, ClassNotFoundException {
        Connection conn;
        conn = da.getConnection();
        Statement statement = da.getConnection().createStatement();

        // query for selecting all the data on the students table
        String sql = "SELECT * FROM  Staffs.StaffDetails ";

        // query for counting number of males in the student table
        String maleCount = "SELECT count(Gender) AS 'Count1' FROM  Staffs.StaffDetails  where Gender = 'Male' ";

        // query for counting number of females on the students table
        String femaleCount = "SELECT count(Gender) AS 'Count2' FROM  Staffs.StaffDetails where Gender = 'Female'";

        // query for counting total genders on the table
        String totalCount = "SELECT count(Gender) AS 'Count3' FROM  Staffs.StaffDetails";

        ResultSet rrs1 = da.executeQuery(sql);
        ResultSet rrs = da.executeQuery(maleCount);
        ResultSet rrs2 = da.executeQuery(femaleCount);
        ResultSet rrs3 = da.executeQuery(totalCount);

        try {
            while (rrs1.next()) {
                String gen = rrs1.getString("gender");
            }

            while (rrs.next()) {
                String num = rrs.getString("count1");
                if (num.length() == 1) {
                    staffMaleGenderCount.setText("00" + num);
                } else if (num.length() == 2) {
                    staffMaleGenderCount.setText("0" + num);
                } else if (num.length() == 3) {
                    staffMaleGenderCount.setText(num);
                }
            }

            while (rrs2.next()) {
                String num = rrs2.getString("count2");
                if (num.length() == 1) {
                    staffFemaleGenderCount.setText("00" + num);
                } else if (num.length() == 2) {
                    staffFemaleGenderCount.setText("0" + num);
                } else if (num.length() == 3) {
                    staffFemaleGenderCount.setText(num);
                }
            }

            while (rrs3.next()) {
                String num = rrs3.getString("count3");
                if (num.length() == 1) {
                    staffTotalGenderCount.setText("00" + num);
                } else if (num.length() == 2) {
                    staffTotalGenderCount.setText("0" + num);
                } else if (num.length() == 3) {
                    staffTotalGenderCount.setText(num);
                }
            }
            rrs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchStudentDetails(KeyEvent event) {
        FilteredList<AdminStudentsTable> filteredData = new FilteredList<>(obs, e -> true);
        studentSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super AdminStudentsTable>) adminStudentTable -> {
                String lowerCaseFilter = newValue.toLowerCase();
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                if (adminStudentTable.getRegistrationNumber().contains(newValue)) {
                    return true;
                } else if (adminStudentTable.getFullName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (adminStudentTable.getGender().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (adminStudentTable.getStudentClass().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });

        });
        SortedList<AdminStudentsTable> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(adStudentTable.comparatorProperty());
        adStudentTable.setItems(sortedData);
    }

    @FXML
    private void searchTeacherDetails(KeyEvent event) {
        FilteredList<TeachersTable> filteredData = new FilteredList<>(obs1, e -> true);
        teacherSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super TeachersTable>) teac -> {
                String lowerCaseFilter = newValue.toLowerCase();
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                if (teac.getEmployeeID().contains(newValue)) {
                    return true;
                } else if (teac.getFullName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (teac.getGender().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<TeachersTable> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(teachersTable.comparatorProperty());
        teachersTable.setItems(sortedData);
    }

    // Reset button for Staff
    @FXML
    private void staffResetBtn(ActionEvent event) {
        // Set all fields to empty or null on click
        employeeID.setText("");
        employeeFirstName.setText("");
        employeeLastName.setText("");
        employeeMiddleName.setText("");
        selectQualification.setValue(null);
        selectTeacherGender.setValue(null);
        employeDOB.setValue(null);
        selectMaritalStatus.setValue(null);
        employeeReligion.setValue(null);
        employeePhoneNumber.setText("");
        employeeResidence.setText("");
        employeeEmail.setText("");
        employeeNationality.setText("");
        selectDesignation.setValue(null);

        File file = new File("src/user.png");
        selectedFile = file;
        employeeFile = selectedFile;
        Image image = new Image(selectedFile.toURI().toString());
        avatarEmployeeView.setImage(image);
    }

    // Method for getting the current date
    public static String currentDate(String separator) {
        Calendar date = Calendar.getInstance();
        String day = Integer.toString(date.get(Calendar.DAY_OF_MONTH));
        String month = Integer.toString(date.get(Calendar.MONTH) + 1);
        String year = Integer.toString(date.get(Calendar.YEAR));
        if (month.length() < 2) {
            month = "0" + month;
        }
        if (day.length() < 2) {
            day = "0" + day;
        }
        String regDate = year + separator + month + separator + day;
        return regDate;
    }

    //boolean method for returning boolean value if date is valid or not
    public boolean isDateValid(String dateformat,
            String currentDate, String dateOfInterest) {

        Calendar cal = Calendar.getInstance();
        String format = dateformat;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date cd = null;  // current date
        Date doi = null; // date of interest

        try {
            cd = sdf.parse(currentDate);
            doi = sdf.parse(dateOfInterest);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = cd.getTime() - doi.getTime();
        int diffDays = (int) (diff / (24 * 1000 * 60 * 60));

//        Date currentTime =  cal.getTime();
//        DateFormat df = new SimpleDateFormat("HH:mm:ss");
//        String formatDate = df.format(currentTime);
        if (diffDays > 0) {
            Notifications notify = Notifications.create()
                    .graphic(new ImageView(errorImg))
                    .title("ERROR")
                    .text("Date of birth cannot be greater than current date")
                    .position(Pos.BOTTOM_RIGHT)
                    .hideAfter(Duration.seconds(3));
            notify.show();
            return false;
        } else {
            return true;
        }
    }

}
