/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.management.system.controllers;

import tray.notification.TrayNotification;
import com.gn.GNAvatarView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListCell;
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
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import tray.animations.AnimationType;
import tray.notification.NotificationType;

/**
 * g
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
    private TableColumn<AdminStudentsTable, String> studentClass;
    @FXML
    private TableColumn<AdminStudentsTable, String> residentialAddress;
    @FXML
    private TableColumn<AdminStudentsTable, String> paymentStatus;
    ObservableList<AdminStudentsTable> obs = FXCollections.observableArrayList();
    ObservableList<TeachersTable> obs1 = FXCollections.observableArrayList();
    private TableView<AdminStudentsTable> adStudentTable;
    @FXML
    private AnchorPane dashboardPane;
    private AnchorPane studentPane;
    @FXML
    private AnchorPane gradesPane;
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
    private AnchorPane addStudentPane;
    private AnchorPane addTeacherPane;
    private JFXComboBox selectGender;
    private JFXComboBox selectTeacherGender;
    private JFXComboBox selectClass;
    private JFXComboBox selectDepartment;
    private JFXComboBox selectReligion;
    private JFXComboBox selectMaritalStatus;
    private JFXComboBox selectQualification;
    private JFXComboBox selectDesignation;
    private GNAvatarView avatarStudView;
    private GNAvatarView avatarGuardView;
    @FXML
    private GNAvatarView stuImage;
    private JFXTextField studentFirstName;
    private JFXTextField studentLastName;
    private JFXTextField studentMiddleName;
    private JFXTextField studentAdmissionNumber;
    private JFXTextField guardianMail;
    private JFXTextField guardianOccupation;
    private JFXTextField residence;
    private JFXTextField guardianPhone;
    private JFXComboBox nationality;
    private Image image;
    private JFXDatePicker studentDateOfBirth;
    private JFXTextField guardianName;

    FileChooser fileChooser = new FileChooser();
    File selectedFile;
    File selectedFile1;
    private TableView<TeachersTable> teachersTable;
    private TableColumn<TeachersTable, String> teacherID;
    private TableColumn<TeachersTable, String> teacherFullName;
    private TableColumn<TeachersTable, String> teacherDob;
    private TableColumn<TeachersTable, String> teacherGender;
    private TableColumn<TeachersTable, String> teacherQualification;
    private TableColumn<TeachersTable, String> teacherAddress;
    private TableColumn<TeachersTable, String> teacherDesignation;
    private GNAvatarView avatarEmployeeView;
    private JFXTextField employeeID;
    private JFXTextField employeeFirstName;
    private JFXTextField employeeLastName;
    private JFXTextField employeeMiddleName;
    private JFXTextField employeePhoneNumber;
    private JFXDatePicker employeDOB;
    private JFXComboBox employeeReligion;
    private JFXTextField employeeResidence;
    private JFXTextField employeeEmail;
    private JFXTextField employeeNationality;
    private GNAvatarView teacherImage;
    @FXML
    private Text studentName;
    @FXML
    private Text studentPaymentStatus;
    private Text teacherName;
    @FXML
    private TextField OldPasswordTxtF;
    @FXML
    private TextField NewPasswordTxtF;
    @FXML
    private TextField ConfirmPasswordTxtF;
    private Text stuMaleText;
    private Text stuFemaleText;
    private Text stuTotalText;
    String OldPassword, NewPassword, ConfirmedPassword, user1, pass1;
    Connection conn;
    @FXML
    private TextField coursesSearch;
    private TextField studentSearch;
    private TextField teacherSearch;
    private Text staffMaleGenderCount;
    private Text staffFemaleGenderCount;
    private Text staffTotalGenderCount;
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
    public StackPane editStudentModals;
    @FXML
    private StackPane editTeachersModals;
    @FXML
    private StackPane editParentsModals;
    @FXML
    public StackPane deleteStudentPane;
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
    @FXML
    private StackPane editCourses;
    @FXML
    private StackPane deleteCourse;
    @FXML
    private JFXButton deleteCourseYes;
    @FXML
    private JFXButton deleteCourseNo;
    private AnchorPane primary_SecPaneStaff;
    private AnchorPane primary_SecPaneParents;
    @FXML
    private AnchorPane primary_SecPaneGrades;
    private AnchorPane primary_SecPaneStudent;
    @FXML
    private AnchorPane primary_SecPaneCourses;
    @FXML
    private TableView<?> CoursesTable;
    @FXML
    private StackPane addCourse;
    @FXML
    private TextField studentSearch1;
    @FXML
    private TableView<?> gradesStudents;
    @FXML
    private TableView<?> gradesTable;
    @FXML
    private JFXButton classBtn;
    @FXML
    private MaterialDesignIconView classIcon;
    @FXML
    private AnchorPane holderPane;
    @FXML
    private StackPane addNews;
    @FXML
    private JFXButton addNewsAction;
    @FXML
    private JFXButton CancelAddNews;
    @FXML
    private JFXListView<String> newsList;
    @FXML
    private AnchorPane classesPane;
    @FXML
    private TextField classes_nameSearch;
    @FXML
    private JFXComboBox<?> classes_genderSearch;
    @FXML
    private JFXComboBox<?> classes_paymentSearch;
    @FXML
    private Pane classesPrimary;
    @FXML
    private Pane classesSecondary;
    @FXML
    private TableView<?> classesTable;
    @FXML
    private JFXComboBox<?> classesSearch;
    @FXML
    private AnchorPane primary_SecPaneClasses;
    @FXML
    private JFXDatePicker dateField;
    @FXML
    private JFXTextField newsTextField;

    public void setV() {
        editStudentModals.setVisible(true);
    }

    public DashboardController() {
        try {
            this.conn = Database.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Database database;
    Connection connection;

    @FXML
    private void openAddNews(MouseEvent event) {
        addNews.setVisible(true);
        dashboardPane.setDisable(true);
        settingsPane.setDisable(true);
        gradesPane.setDisable(true);
        parentsPane.setDisable(true);
        classesPane.setDisable(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(addNews);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    @FXML
    private void handleClassesSearch(ActionEvent event) {
    }

    static class cell extends JFXListCell<String> {

        HBox hbox = new HBox();
        JFXButton delete = new JFXButton();
        JFXButton edit = new JFXButton();
        Pane pane = new Pane();
        Label label = new Label();
        Image newsIcon = new Image("/school/management/system/images/icons8_Purchase_Order_48px.png");
        Image trash = new Image("/school/management/system/images/trash.png");
        Image editIcon = new Image("/school/management/system/images/edit.png");
        ImageView imageView = new ImageView(newsIcon);
        ImageView imageView2 = new ImageView(trash);
        ImageView imageView3 = new ImageView(editIcon);

        public cell() {
            super();
            hbox.getChildren().addAll(imageView, label, pane, edit, delete);
            hbox.setHgrow(pane, Priority.ALWAYS);
            delete.setGraphic(imageView2);
            edit.setGraphic(imageView3);
            imageView3.setFitHeight(16);
            imageView3.setFitWidth(16);
            imageView.setFitHeight(25);
            imageView.setFitWidth(25);
            Insets i = new Insets(0, 0, 0, 10);
            label.setPadding(i);
            hbox.setAlignment(Pos.CENTER);
            hbox.setFillHeight(true);
            delete.setOnAction((ActionEvent event) -> {

                getListView().getSelectionModel().select(getItem());
                String newsText = getListView().getSelectionModel().getSelectedItem();
//                System.out.println(newsText);
                DashboardController dashboardController = new DashboardController();
                try {
                    getListView().getItems().remove(getItem());
                    dashboardController.deleteNewsRecord(newsText);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(StaffDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(StaffDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        public void updateItem(String name, boolean empty) {
            super.updateItem(name, empty);
            setText(null);
            setGraphic(null);

            if (name != null && !empty) {
                label.setText(name);
                setGraphic(hbox);
            }
        }
    }

//    private void openStudbtn(ActionEvent event) {
//        //set title for filechooser
//        fileChooser.setTitle("Open Image");
//
//        //set extension filter
//        try {
//            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All images", "*.jpg", "*.jpeg", "*.png", "*.gif"));
//        } catch (NullPointerException ex) {
//            ex.printStackTrace();
//        }
//
//        //show open file dialog
//        fileChooser.getInitialDirectory();
//        selectedFile = fileChooser.showOpenDialog(null);
//
//        try {
//            if (selectedFile != null) {
//                String path = selectedFile.getPath();
//                selectedFile = new File(path);
//                mainFile = selectedFile;
//                Image image = new Image(selectedFile.toURI().toString());
//                avatarStudView.setImage(image);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//    private void openGuardbtn(ActionEvent event) {
//        //set title for filechooser
//        fileChooser.setTitle("Open Image");
//
//        //set extension filter
//        try {
//            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All images", "*.jpg", "*.jpeg", "*.png", "*.gif"));
//        } catch (NullPointerException ex) {
//            ex.printStackTrace();
//        }
//
//        //show open file dialog
//        fileChooser.getInitialDirectory();
//        selectedFile = fileChooser.showOpenDialog(null);
//
//        try {
//            if (selectedFile != null) {
//                String path = selectedFile.getPath();
//                selectedFile = new File(path);
//                guardianFile = selectedFile;
//                Image image = new Image(selectedFile.toURI().toString());
//                avatarGuardView.setImage(image);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
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

        if (classBtn.getStyleClass().size() == 2 && classIcon.getStyleClass().size() == 2) {

        } else if (classBtn.getStyleClass().size() == 3 && classIcon.getStyleClass().size() == 3) {
            classBtn.getStyleClass().remove(2);
            classIcon.getStyleClass().remove(2);
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

        holderPane.setVisible(false);
        classesPane.setVisible(false);
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

        if (classBtn.getStyleClass().size() == 2 && classIcon.getStyleClass().size() == 2) {

        } else if (classBtn.getStyleClass().size() == 3 && classIcon.getStyleClass().size() == 3) {
            classBtn.getStyleClass().remove(2);
            classIcon.getStyleClass().remove(2);
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
            setNode(studentPane);
        }

        classesPane.setVisible(false);
        dashboardPane.setVisible(false);
        settingsPane.setVisible(false);
        gradesPane.setVisible(false);
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

        if (classBtn.getStyleClass().size() == 2 && classIcon.getStyleClass().size() == 2) {

        } else if (classBtn.getStyleClass().size() == 3 && classIcon.getStyleClass().size() == 3) {
            classBtn.getStyleClass().remove(2);
            classIcon.getStyleClass().remove(2);
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
            setNode(staffPane);
        }

        classesPane.setVisible(false);
        dashboardPane.setVisible(false);
        settingsPane.setVisible(false);
        gradesPane.setVisible(false);
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

        if (classBtn.getStyleClass().size() == 2 && classIcon.getStyleClass().size() == 2) {

        } else if (classBtn.getStyleClass().size() == 3 && classIcon.getStyleClass().size() == 3) {
            classBtn.getStyleClass().remove(2);
            classIcon.getStyleClass().remove(2);
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

        holderPane.setVisible(false);
        classesPane.setVisible(false);
        dashboardPane.setVisible(false);
        gradesPane.setVisible(false);
        coursesPane.setVisible(false);
    }

    @FXML
    private void openClasses(ActionEvent event) {
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

        if (staffBtn.getStyleClass().size() == 2 && staffIcon.getStyleClass().size() == 2) {

        } else if (staffBtn.getStyleClass().size() == 3 && staffIcon.getStyleClass().size() == 3) {
            staffBtn.getStyleClass().remove(2);
            staffIcon.getStyleClass().remove(2);
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

        if (gradeBtn.getStyleClass().size() == 2 && gradeIcon.getStyleClass().size() == 2) {

        } else if (gradeBtn.getStyleClass().size() == 3 && gradeIcon.getStyleClass().size() == 3) {
            gradeBtn.getStyleClass().remove(2);
            gradeIcon.getStyleClass().remove(2);
        }

        if (classBtn.getStyleClass().toString().contains("active") && classIcon.getStyleClass().toString().contains("iconActive")) {

        } else {
            classBtn.getStyleClass().add("active");
            classIcon.getStyleClass().add("iconActive");
            classesPane.setVisible(true);
            FadeTransition fade = new FadeTransition();
            fade.setDuration(Duration.millis(300));
            fade.setNode(classesPane);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play();
        }

        holderPane.setVisible(false);
        coursesPane.setVisible(false);
        gradesPane.setVisible(false);
        dashboardPane.setVisible(false);
        settingsPane.setVisible(false);
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

        if (classBtn.getStyleClass().size() == 2 && classIcon.getStyleClass().size() == 2) {

        } else if (classBtn.getStyleClass().size() == 3 && classIcon.getStyleClass().size() == 3) {
            classBtn.getStyleClass().remove(2);
            classIcon.getStyleClass().remove(2);
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

        holderPane.setVisible(false);
        classesPane.setVisible(false);
        dashboardPane.setVisible(false);
        settingsPane.setVisible(false);
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

        if (classBtn.getStyleClass().size() == 2 && classIcon.getStyleClass().size() == 2) {

        } else if (classBtn.getStyleClass().size() == 3 && classIcon.getStyleClass().size() == 3) {
            classBtn.getStyleClass().remove(2);
            classIcon.getStyleClass().remove(2);
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
            setNode(parentsPane);
        }

        classesPane.setVisible(false);
        dashboardPane.setVisible(false);
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

        if (classBtn.getStyleClass().size() == 2 && classIcon.getStyleClass().size() == 2) {

        } else if (classBtn.getStyleClass().size() == 3 && classIcon.getStyleClass().size() == 3) {
            classBtn.getStyleClass().remove(2);
            classIcon.getStyleClass().remove(2);
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

        holderPane.setVisible(false);
        classesPane.setVisible(false);
        dashboardPane.setVisible(false);
        settingsPane.setVisible(false);
        gradesPane.setVisible(false);

    }

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

    @FXML
    private void addCourse(ActionEvent event) {
        addCourse.setVisible(true);
        dashboardPane.setDisable(true);
        studentPane.setDisable(true);
        staffPane.setDisable(true);
        settingsPane.setDisable(true);
        gradesPane.setDisable(true);
        parentsPane.setDisable(true);
        coursesPane.setDisable(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(addCourse);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    @FXML
    private void editCourses(ActionEvent event) {
        editCourses.setVisible(true);
        dashboardPane.setDisable(true);
        studentPane.setDisable(true);
        staffPane.setDisable(true);
        settingsPane.setDisable(true);
        gradesPane.setDisable(true);
        parentsPane.setDisable(true);
        coursesPane.setDisable(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(editCourses);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    @FXML
    private void dropCourse(ActionEvent event) {
        deleteCourse.setVisible(true);
        dashboardPane.setDisable(true);
        studentPane.setDisable(true);
        staffPane.setDisable(true);
        settingsPane.setDisable(true);
        gradesPane.setDisable(true);
        parentsPane.setDisable(true);
        coursesPane.setDisable(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(deleteCourse);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    @FXML
    private void addGrade(ActionEvent event) {

    }

    @FXML
    private void classesPrimary(ActionEvent event) {
        classesSecondary.setVisible(false);
        classesPrimary.setVisible(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(primary_SecPaneClasses);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((event2) -> {
            primary_SecPaneClasses.setVisible(false);
        });
    }

    @FXML
    private void classesSecondary(ActionEvent event) {
        classesPrimary.setVisible(false);
        classesSecondary.setVisible(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(primary_SecPaneClasses);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((event2) -> {
            primary_SecPaneClasses.setVisible(false);
        });
    }

    @FXML
    private void gradesPrimary(ActionEvent event) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(primary_SecPaneGrades);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((event2) -> {
            primary_SecPaneGrades.setVisible(false);
        });
    }

    @FXML
    private void gradesSecondary(ActionEvent event) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(primary_SecPaneGrades);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((event2) -> {
            primary_SecPaneGrades.setVisible(false);
        });
    }

    private void staffPrimary(ActionEvent event) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(primary_SecPaneStaff);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((event2) -> {
            primary_SecPaneStaff.setVisible(false);
        });
    }

    private void staffSecondary(ActionEvent event) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(primary_SecPaneStaff);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((event2) -> {
            primary_SecPaneStaff.setVisible(false);
        });
    }

    private void parentsPrimary(ActionEvent event) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(primary_SecPaneParents);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((event2) -> {
            primary_SecPaneParents.setVisible(false);
        });
    }

    private void parentsSecondary(ActionEvent event) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(primary_SecPaneParents);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((event2) -> {
            primary_SecPaneParents.setVisible(false);
        });
    }

    @FXML
    private void coursePrimary(ActionEvent event) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(primary_SecPaneCourses);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((event2) -> {
            primary_SecPaneCourses.setVisible(false);
        });
    }

    @FXML
    private void courseSecondary(ActionEvent event) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(primary_SecPaneCourses);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((event2) -> {
            primary_SecPaneCourses.setVisible(false);
        });
    }

    @FXML
    private void exitCourseAddModal(MouseEvent event) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(addCourse);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((ActionEvent event2) -> {
            dashboardPane.setDisable(false);
            studentPane.setDisable(false);
            staffPane.setDisable(false);
            settingsPane.setDisable(false);
            gradesPane.setDisable(false);
            parentsPane.setDisable(false);
            coursesPane.setDisable(false);
            addCourse.setVisible(false);
        });
    }

    @FXML
    private void exitCourseEditModal(MouseEvent event) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(editCourses);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((ActionEvent event2) -> {
            dashboardPane.setDisable(false);
            studentPane.setDisable(false);
            staffPane.setDisable(false);
            settingsPane.setDisable(false);
            gradesPane.setDisable(false);
            parentsPane.setDisable(false);
            coursesPane.setDisable(false);
            editCourses.setVisible(false);
        });
    }

    private void selectStudentSchool(ActionEvent event) {
        primary_SecPaneStudent.setVisible(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(primary_SecPaneStudent);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    private void selectStaffSchool(ActionEvent event) {
        primary_SecPaneStaff.setVisible(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(primary_SecPaneStaff);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    private void selectParentSchool(ActionEvent event) {
        primary_SecPaneParents.setVisible(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(primary_SecPaneParents);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    @FXML
    private void selectGradesSchool(ActionEvent event) {
        primary_SecPaneGrades.setVisible(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(primary_SecPaneGrades);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    @FXML
    private void selectCourseSchool(ActionEvent event) {
        primary_SecPaneCourses.setVisible(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(primary_SecPaneCourses);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
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

    public void birthdays() throws FileNotFoundException {
        String[] names = {"Isaac Ogunleye", "Joy Ajiboye", "Olumide Awodeji", "Daniel Odey", "Edmund Giwa"};
        for (String name : names) {
            Image img = new Image(new FileInputStream("src/school/management/system/images/birthday.png"));
            Label lbl = new Label(name);
            ImageView imgView = new ImageView(img);
            imgView.setFitHeight(25);
            imgView.setFitWidth(25);
            lbl.setGraphicTextGap(10);
            lbl.setGraphic(imgView);
            view.getItems().add(lbl);
        }

        ObservableList<String> news = FXCollections.observableArrayList();
//        newsList.setItems(news);
//        newsList.setCellFactory(param -> new cell());

    }

    public void disposePane() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(addNews);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        fade.setOnFinished((ActionEvent event1) -> {
            addNews.setVisible(false);
            dashboardPane.setDisable(false);
            classesPane.setDisable(false);
            settingsPane.setDisable(false);
            gradesPane.setDisable(false);
            parentsPane.setDisable(false);
        });
    }

    public boolean validateNewsMethod() {
//        if ("".equals(dateField.getValue().toString())) {
//            return false;
//        } else
        if ("".equals(newsTextField.getText())) {
            return false;
        }
        return true;
    }

    //        ObservableList<String> news = FXCollections.observableArrayList("PTA Meeting - 19/11/2019", "Interhouse Sport - 30/24/2019");
    ObservableList<String> news = FXCollections.observableArrayList();
//        newsList.setItems(news);
//        newsList.setCellFactory(param -> new cell());

    public void loadTableData() throws ClassNotFoundException, SQLException {
        newsList.getItems().clear();
        news.removeAll(news);
        populateNewsList();
    }

    public void populateNewsList() throws ClassNotFoundException, SQLException {
        try {
            database.dbConnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StaffDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection connection2 = database.getConnection();
        ResultSet resultSet = connection2.createStatement().executeQuery("SELECT NewsInfoDetails, NewsDate FROM News.NewsDetails");
        while (resultSet.next()) {
            String news1 = resultSet.getString("NewsInfoDetails");
            String news2 = resultSet.getString("NewsDate");
            String concat = news1 + " - " + news2;
            news.add(concat);
        }
        newsList.setItems(news);
        newsList.setCellFactory(param -> new cell());
        resultSet.close();
    }

    public void deleteNewsRecord(String newsText) throws ClassNotFoundException, SQLException {
        try {
            database.dbConnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StaffDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection connection = database.getConnection();

        System.out.println(newsText);
        int leng = newsText.length();
        PreparedStatement preparedStatement = connection.prepareStatement("Delete From News.NewsDetails Where NewsInfoDetails like '%" + newsText.substring(0, 4) + "%'");
        int execute = preparedStatement.executeUpdate();

        if (execute != 0) {
            Notifications notify = Notifications.create()
                    .graphic(new ImageView(successImg))
                    .hideAfter(Duration.seconds(8))
                    .title("Success")
                    .text("Deleted Succesfully")
                    .position(Pos.TOP_CENTER);
            notify.show();
            System.out.println("sucessful");
        } else {
            Notifications notify = Notifications.create()
                    .graphic(new ImageView(errorImg))
                    .hideAfter(Duration.seconds(5))
                    .title("Failed")
                    .text("Unsuccesfully")
                    .position(Pos.TOP_CENTER);
            notify.show();
            System.out.println("unsucessful");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            populateNewsList();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.connection = Database.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            birthdays();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            studentPane = FXMLLoader.load(getClass().getResource("/school/management/system/fxml/Students.fxml"));
            staffPane = FXMLLoader.load(getClass().getResource("/school/management/system/fxml/Staffs.fxml"));
            parentsPane = FXMLLoader.load(getClass().getResource("/school/management/system/fxml/Parents.fxml"));
        } catch (IOException ex) {
        }

        addNewsAction.setOnAction((ActionEvent event) -> {
            if (validateNewsMethod()) {
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("insert into News.NewsDetails(NewsInfoDetails, NewsDate)VALUES(?,?)");

                    preparedStatement.setString(1, newsTextField.getText());
                    preparedStatement.setString(2, dateField.getValue().toString());

                    int save = preparedStatement.executeUpdate();

                    if (save != 0) {
                        Notifications notify = Notifications.create()
                                .graphic(new ImageView(successImg))
                                .hideAfter(Duration.seconds(8))
                                .title("Success")
                                .text("Uploaded Succesfully")
                                .position(Pos.TOP_CENTER);
                        notify.show();
                        System.out.println("sucessful");
                        newsTextField.setText("");
                        dateField.setValue(null);
                        disposePane();
                    } else {
                        Notifications notify = Notifications.create()
                                .graphic(new ImageView(errorImg))
                                .hideAfter(Duration.seconds(5))
                                .title("Failed")
                                .text("Uploaded Unsuccesfully")
                                .position(Pos.TOP_CENTER);
                        notify.show();
                        System.out.println("unsucessful");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(StaffDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    loadTableData();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(StaffDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(StaffDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Notifications notify = Notifications.create()
                        .graphic(new ImageView(errorImg))
                        .hideAfter(Duration.seconds(5))
                        .title("Failed")
                        .text("Insert Something")
                        .position(Pos.TOP_CENTER);
                notify.show();
                System.out.println("unsucessful");
            }
        });

        CancelAddNews.setOnAction((ActionEvent event) -> {
            FadeTransition fade = new FadeTransition();
            fade.setDuration(Duration.millis(300));
            fade.setNode(addNews);
            fade.setFromValue(1);
            fade.setToValue(0);
            fade.play();
            fade.setOnFinished((ActionEvent event1) -> {
                addNews.setVisible(false);
                dashboardPane.setDisable(false);
                classesPane.setDisable(false);
                settingsPane.setDisable(false);
                gradesPane.setDisable(false);
                parentsPane.setDisable(false);
            });
        });
//        populateComboBoxes();
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

        deleteCourseYes.setOnAction((ActionEvent event) -> {
            TrayNotification sd = new TrayNotification("Delete Succesful", "Course Deleted", successImg, Paint.valueOf("#6AC259"));
            sd.setAnimationType(AnimationType.POPUP);
            sd.showAndDismiss(Duration.seconds(2));
        });

        deleteCourseNo.setOnAction((ActionEvent event) -> {
            FadeTransition fade = new FadeTransition();
            fade.setDuration(Duration.millis(300));
            fade.setNode(deleteCourse);
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
                deleteCourse.setVisible(false);
            });
        });

    }

    private void setNode(Node node) {
        holderPane.setVisible(true);
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(300));
        ft.setNode(node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    public void closeStage() {
        ((Stage) mainDashPane.getScene().getWindow()).close();
    }

    private void addTeacher(ActionEvent event) {
        addTeacherPane.setVisible(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(300));
        fade.setNode(addTeacherPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

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

    private double xOffset = 0;
    private double yOffset = 0;

    private void logOutAction() throws IOException {
        closeStage();
        Parent parent = FXMLLoader.load(getClass().getResource("/school/management/system/fxml/Login.fxml"));

        Stage stage = new Stage(StageStyle.TRANSPARENT);
        String path = "/school/management/system/images/CHMS_Icon.png";

        Image img = new Image(path);

        stage.getIcons().add(0, img);
        Scene scene = new Scene(parent);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.setTitle("Login | CHMS");
        stage.initOwner(((Stage) mainDashPane.getScene().getWindow()));
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

        //dragable login stage
        parent.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        parent.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });//end of draggable stage
    }

    // Save student button
    private void saveStudentDetails(ActionEvent event) throws SQLException, FileNotFoundException, ClassNotFoundException {

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

    private void populateStudentTable() throws ClassNotFoundException {
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
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    private void saveEmployeeDetails(ActionEvent event) throws SQLException, FileNotFoundException, ClassNotFoundException {

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

    private void populateStaffTable() throws ClassNotFoundException {
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
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void refreshStaffTable() {
        try {
            teachersTable.getItems().clear();
            obs1.removeAll(obs1);
            populateStaffTable();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refreshStudentTable() throws ClassNotFoundException {
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
