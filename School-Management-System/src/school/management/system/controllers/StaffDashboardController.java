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
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.octicons.OctIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Spark
 */
public class StaffDashboardController implements Initializable {

    @FXML
    private AnchorPane mainDashPane;
    @FXML
    private AnchorPane studentPane;
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
    private JFXComboBox nationality;
    @FXML
    private AnchorPane parentsPane;
    @FXML
    private AnchorPane gradesPane;
    @FXML
    private AnchorPane settingsPane;
    @FXML
    private TextField OldPasswordTxtF;
    @FXML
    private TextField NewPasswordTxtF;
    @FXML
    private TextField ConfirmPasswordTxtF;
    @FXML
    private JFXButton studentBtn;
    @FXML
    private OctIconView stuIcon;
    @FXML
    private JFXButton parentBtn;
    @FXML
    private MaterialDesignIconView parentIcon;
    @FXML
    private JFXButton gradeBtn;
    @FXML
    private OctIconView gradeIcon;
    @FXML
    private JFXButton settingBtn;
    @FXML
    private MaterialDesignIconView settingsIcon;
    @FXML
    private StackPane logoutPane;
    @FXML
    private JFXButton logoutYes;
    @FXML
    private JFXButton logoutNo;

    FileChooser fileChooser = new FileChooser();
    File selectedFile;
    File selectedFile1;
    private File mainFile;
    private File guardianFile;
    private File employeeFile;
    static String teacherID;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox views;
    @FXML
    private StackPane editStudentModals;
    private AnchorPane primary_SecPaneStudent;
    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private JFXListView<?> newsList;
    @FXML
    private JFXListView<?> view;
    @FXML
    private TextField studentSearch1;
    @FXML
    private TableView<?> gradesStudents;
    @FXML
    private TableView<?> gradesTable;
    @FXML
    private AnchorPane primary_SecPaneGrades;
    @FXML
    private AnchorPane classesPane;
    @FXML
    private TextField coursesSearch;
    @FXML
    private Pane classesPrimary;
    @FXML
    private Pane classesSecondary;
    @FXML
    private TableView<?> classesTable;
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
    private GNAvatarView stuImage1;
    @FXML
    private Text studentName1;
    @FXML
    private Text studentPaymentStatus1;
    @FXML
    private JFXButton dashboardBtn;
    @FXML
    private MaterialDesignIconView dashIcon;
    @FXML
    private JFXButton classBtn;
    @FXML
    private MaterialDesignIconView classIcon;
    @FXML
    private StackPane deleteStudentPane;
    @FXML
    private JFXButton deleteStudentYes;
    @FXML
    private JFXButton deleteStudentNo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateComboBoxes();
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
                settingsPane.setDisable(false);
                gradesPane.setDisable(false);
                parentsPane.setDisable(false);
                logoutPane.setVisible(false);
            });
        });

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
    
    
    public void populateComboBoxes() {
        ObservableList genders = FXCollections.observableArrayList(
                "Male", "Female");
        selectGender.setItems(genders);
        ObservableList classes = FXCollections.observableArrayList(
                "JSS1", "JSS2", "JSS3", "SS1", "SS2", "SS3");
        selectClass.setItems(classes);
        ObservableList departments = FXCollections.observableArrayList(
                "Science", "Art", "Commercial", "None");
        selectDepartment.setItems(departments);
        ObservableList religions = FXCollections.observableArrayList(
                "Christianity", "Islamism", "Others");
        selectReligion.setItems(religions);
        ObservableList nationality1 = FXCollections.observableArrayList(
                "Nigerian", "Ghanian", "Cameroonian", "Benin");
        nationality.setItems(nationality1);
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
    private void searchStudentDetails(KeyEvent event) {
    }

    @FXML
    private void saveStudentDetails(ActionEvent event) {
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

    @FXML
    private void studentResetBtn(ActionEvent event) {
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
    private void addParent(ActionEvent event) {
    }

    @FXML
    private void saveAction(ActionEvent event) {
    }

    @FXML
    private void openDashboard(ActionEvent event) {
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

        classesPane.setVisible(false);
        studentPane.setVisible(false);
        parentsPane.setVisible(false);
        settingsPane.setVisible(false);
        gradesPane.setVisible(false);;
    }

    @FXML
    private void openStudent(ActionEvent event) {
        if (dashboardBtn.getStyleClass().size() == 2 && dashIcon.getStyleClass().size() == 2) {

        } else if (dashboardBtn.getStyleClass().size() == 3 && dashIcon.getStyleClass().size() == 3) {
            dashboardBtn.getStyleClass().remove(2);
            dashIcon.getStyleClass().remove(2);
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
       
        if (classBtn.getStyleClass().size() == 2 && classIcon.getStyleClass().size() == 2) {

        } else if (classBtn.getStyleClass().size() == 3 && classIcon.getStyleClass().size() == 3) {
            classBtn.getStyleClass().remove(2);
            classIcon.getStyleClass().remove(2);
        }

        if (parentBtn.getStyleClass().size() == 2 && parentIcon.getStyleClass().size() == 2) {

        } else if (parentBtn.getStyleClass().size() == 3 && parentIcon.getStyleClass().size() == 3) {
            parentBtn.getStyleClass().remove(2);
            parentIcon.getStyleClass().remove(2);
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

        classesPane.setVisible(false);
        dashboardPane.setVisible(false);
        settingsPane.setVisible(false);
        gradesPane.setVisible(false);
        parentsPane.setVisible(false);
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

        classesPane.setVisible(false);
        dashboardPane.setVisible(false);
        studentPane.setVisible(false);
        settingsPane.setVisible(false);
        gradesPane.setVisible(false);
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

        if (parentBtn.getStyleClass().size() == 2 && parentIcon.getStyleClass().size() == 2) {

        } else if (parentBtn.getStyleClass().size() == 3 && parentIcon.getStyleClass().size() == 3) {
            parentBtn.getStyleClass().remove(2);
            parentIcon.getStyleClass().remove(2);
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
        
        parentsPane.setVisible(false);
        gradesPane.setVisible(false);
        dashboardPane.setVisible(false);
        studentPane.setVisible(false);
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

        classesPane.setVisible(false);
        dashboardPane.setVisible(false);
        studentPane.setVisible(false);
        settingsPane.setVisible(false);
        parentsPane.setVisible(false);
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

        if (gradeBtn.getStyleClass().size() == 2 && gradeIcon.getStyleClass().size() == 2) {

        } else if (gradeBtn.getStyleClass().size() == 3 && gradeIcon.getStyleClass().size() == 3) {
            gradeBtn.getStyleClass().remove(2);
            gradeIcon.getStyleClass().remove(2);
        }
       
        if (classBtn.getStyleClass().size() == 2 && classIcon.getStyleClass().size() == 2) {

        } else if (classBtn.getStyleClass().size() == 3 && classIcon.getStyleClass().size() == 3) {
            gradeBtn.getStyleClass().remove(2);
            gradeIcon.getStyleClass().remove(2);
        }

        if (parentBtn.getStyleClass().size() == 2 && parentIcon.getStyleClass().size() == 2) {

        } else if (parentBtn.getStyleClass().size() == 3 && parentIcon.getStyleClass().size() == 3) {
            parentBtn.getStyleClass().remove(2);
            parentIcon.getStyleClass().remove(2);
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

        classesPane.setVisible(false);
        dashboardPane.setVisible(false);
        studentPane.setVisible(false);
        gradesPane.setVisible(false);
        parentsPane.setVisible(false);
    }

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

    public void closeStage() {
        ((Stage) mainDashPane.getScene().getWindow()).close();
    }
    
   @FXML
    private void logOut(ActionEvent event) {
        logoutPane.setVisible(true);
        dashboardPane.setDisable(true);
        studentPane.setDisable(true);
        settingsPane.setDisable(true);
        gradesPane.setDisable(true);
        parentsPane.setDisable(true);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(500));
        fade.setNode(logoutPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    @FXML
    private void exitStudentEditModal(MouseEvent event) {
    }

    @FXML
    private void addGrade(ActionEvent event) {
    }

    @FXML
    private void selectGradesSchool(ActionEvent event) {
    }

    @FXML
    private void gradesPrimary(ActionEvent event) {
    }

    @FXML
    private void gradesSecondary(ActionEvent event) {
    }

}
