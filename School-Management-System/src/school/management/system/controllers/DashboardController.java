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
import java.util.regex.Pattern;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import school.management.system.demoDatabase.Database;
import school.management.system.tables.AdminStudentsTable;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 *
 * @author DELL
 */
public class DashboardController implements Initializable {

    public Image errorImg = new Image("/school/management/system/images/cross.png");
    public Image successImg = new Image("/school/management/system/images/checked.png");

    private static final String NAME = "(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{2,}";
    private static final String PHONE = "\\d{11}";
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public String where = "";
    String genderSelect;

    Database da = new Database();
    private File mainFile;
    private File guardianFile;
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
    @FXML
    private JFXButton studentResetBtn;
    @FXML
    private Text stuMaleText;
    @FXML
    private Text stuFemaleText;
    @FXML
    private Text stuTotalText;
    @FXML
    private JFXButton chooseStudBtn1;
    @FXML
    private GNAvatarView avatarStudView1;
    @FXML
    private FontAwesomeIconView staffIcon1;

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
                "None", "Science", "Art", "Commercial");
        selectDepartment.setItems(departments);
        ObservableList religions = FXCollections.observableArrayList(
                "Christianity", "Islamism", "Others");
        selectReligion.setItems(religions);
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
        // studentTableData();

        try {
            da.dbConnect();
            studentTableData();
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
            //res.close();
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
//               }

                    if (stuImage.isVisible()) {
                        System.out.println("dispplay image is visible");
                    } else {
                        System.out.println("Not visible");
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        validators();

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
        validators();
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

        if (selectGender.getValue() == "Male") {
            genderSelect = "Male";
        } else if (selectGender.getValue() == "Female") {
            genderSelect = "Female";
        } else {
            genderSelect = null;
        }

//                  selectedFile = fileChooser.showOpenDialog(null);
        if (validateStudentMethod()) {
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
            if (s != 0) {
                Notifications notify = Notifications.create()
                        .graphic(new ImageView(successImg))
                        .hideAfter(Duration.seconds(8))
                        .title("Success")
                        .text("Uploaded Succesfully")
                        .position(Pos.TOP_CENTER);
                notify.show();
                clearAllFields();
            } else {
                Notifications notify = Notifications.create()
                        .graphic(new ImageView(errorImg))
                        .hideAfter(Duration.seconds(5))
                        .title("Failed")
                        .text("Uploaded Unsuccesfully")
                        .position(Pos.TOP_CENTER);
                notify.show();
            }
        } else {
            Notifications notify = Notifications.create()
                    .darkStyle()
                    .graphic(new ImageView(errorImg))
                    .title("Error!")
                    .text("Some fields are empty")
                    .position(Pos.BOTTOM_RIGHT)
                    .hideAfter(Duration.seconds(5));
            notify.show();
        }
    }

    public void validators() {
        ValidationSupport validation1 = new ValidationSupport();
        validation1.registerValidator(studentFirstName, Validator.createEmptyValidator("Input Required", Severity.WARNING));

        ValidationSupport validation2 = new ValidationSupport();
        validation2.registerValidator(studentFirstName, Validator.createRegexValidator("Provide Correct First Name", NAME, Severity.ERROR));

        ValidationSupport validation3 = new ValidationSupport();
        validation3.registerValidator(studentLastName, Validator.createEmptyValidator("Input Required", Severity.WARNING));

        ValidationSupport validation4 = new ValidationSupport();
        validation4.registerValidator(studentLastName, Validator.createRegexValidator("Provide Correct Last Name", NAME, Severity.ERROR));

        ValidationSupport validation5 = new ValidationSupport();
        validation5.registerValidator(guardianMail, Validator.createEmptyValidator("Input Required", Severity.WARNING));

        ValidationSupport validation6 = new ValidationSupport();
        validation6.registerValidator(guardianMail, Validator.createRegexValidator("Provide Correct Email", EMAIL_PATTERN, Severity.ERROR));

        ValidationSupport validation8 = new ValidationSupport();
        validation8.registerValidator(residence, Validator.createEmptyValidator("Input Required", Severity.WARNING));

        ValidationSupport validation9 = new ValidationSupport();
        validation9.registerValidator(guardianPhone, Validator.createEmptyValidator("Input Required", Severity.WARNING));

        ValidationSupport validation10 = new ValidationSupport();
        validation10.registerValidator(guardianPhone, Validator.createRegexValidator("Provide Correct Phone Number", PHONE, Severity.ERROR));

        ValidationSupport validation11 = new ValidationSupport();
        validation11.registerValidator(selectGender, Validator.createEmptyValidator("Input Required", Severity.WARNING));

        ValidationSupport validation12 = new ValidationSupport();
        validation12.registerValidator(selectClass, Validator.createEmptyValidator("Input Required", Severity.WARNING));

        ValidationSupport validation13 = new ValidationSupport();
        validation13.registerValidator(selectReligion, Validator.createEmptyValidator("Input Required", Severity.WARNING));

        ValidationSupport validation14 = new ValidationSupport();
        validation14.registerValidator(guardianName, Validator.createEmptyValidator("Input Required", Severity.WARNING));

        ValidationSupport validation15 = new ValidationSupport();
        validation15.registerValidator(guardianName, Validator.createRegexValidator("Provide Correct Guardian` Name", NAME, Severity.ERROR));

        ValidationSupport validation16 = new ValidationSupport();
        validation16.registerValidator(studentMiddleName, Validator.createEmptyValidator("Input Required", Severity.WARNING));

        ValidationSupport validation18 = new ValidationSupport();
        validation18.registerValidator(studentMiddleName, Validator.createRegexValidator("Input Required", NAME, Severity.WARNING));

        ValidationSupport validation19 = new ValidationSupport();
        validation19.registerValidator(nationality, Validator.createEmptyValidator("Input Required", Severity.WARNING));
    }

    // Validation Method
    public boolean validateStudentMethod() {
        if ("".equals(studentFirstName.getText())) {
            Notifications notify = Notifications.create()
                    .graphic(new ImageView(errorImg))
                    .hideAfter(Duration.seconds(5))
                    .title("Empty")
                    .text("First Name field left blank")
                    .position(Pos.BASELINE_RIGHT);
            notify.show();

        }

        if ("".equals(studentLastName.getText())) {
            return false;
        }

        if ("".equals(studentMiddleName.getText())) {
            return false;
        }

        if ("".equals(guardianName.getText())) {
            return false;
        }

        if ("".equals(guardianMail.getText())) {
            return false;
        }

        if ("".equals(studentAdmissionNumber.getText())) {
            return false;
        }

        if ("".equals(residence.getText())) {
            return false;
        }

        if ("".equals(guardianPhone.getText())) {
            return false;
        }

        if ("".equals(guardianOccupation.getText())) {
            return false;
        }

        if ("".equals(studentDateOfBirth.getValue().toString())) {
            return false;
        }

        if ("".equals(selectClass.getValue().toString())) {
            return false;
        }
        if ("".equals(selectDepartment.getValue().toString())) {
            return false;
        }

        if ("".equals(selectGender.getValue().toString())) {
            return false;
        }

        if ("".equals(selectReligion.getValue().toString())) {
            return false;
        }

        if (!Pattern.matches(EMAIL_PATTERN, guardianMail.getText())) {
            return false;
        }

        if (!Pattern.matches(PHONE, guardianPhone.getText())) {
            return false;
        }

        if (!Pattern.matches(NAME, studentFirstName.getText())) {
            return false;
        }

        if (!Pattern.matches(NAME, studentLastName.getText())) {
            return false;
        }

        if (!Pattern.matches(NAME, studentMiddleName.getText())) {
            return false;
        }

        if (!Pattern.matches(NAME, guardianName.getText())) {
            return false;
        }

        return true;
    }

    @FXML
    public void clearAllFields() {

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
        nationality.setText("");
        
          File file = new File("src/user.png");
                selectedFile = file;
                guardianFile = selectedFile;
                mainFile = selectedFile;
                Image image = new Image(selectedFile.toURI().toString());
                avatarStudView.setImage(image);
                avatarGuardView.setImage(image);
        
//        stuImage.setImage(f);

    }

    public void studentTableData() throws SQLException, ClassNotFoundException {
//        Connection con;
//        con = da.getConnection();
        Statement state = da.getConnection().createStatement();

        String sql = "SELECT * FROM students.studentDetails ";
        String maleCount = "SELECT count(Gender) AS 'Count1' FROM students.studentDetails  where Gender = 'Male' ";
        String femaleCount = "SELECT count(Gender) AS 'Count2' FROM students.vwStudentsInfo where Gender = 'Female'";
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

}
