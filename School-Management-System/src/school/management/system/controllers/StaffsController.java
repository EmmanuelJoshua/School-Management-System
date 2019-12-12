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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Spark
 */
public class StaffsController implements Initializable {

    @FXML
    private AnchorPane staffPane;
    @FXML
    private TableView<?> teachersTable;
    @FXML
    private TableColumn<?, ?> teacherID;
    @FXML
    private TableColumn<?, ?> teacherFullName;
    @FXML
    private TableColumn<?, ?> teacherDob;
    @FXML
    private TableColumn<?, ?> teacherGender;
    @FXML
    private TableColumn<?, ?> teacherQualification;
    @FXML
    private TableColumn<?, ?> teacherAddress;
    @FXML
    private TableColumn<?, ?> teacherDesignation;
    @FXML
    private GNAvatarView teacherImage;
    @FXML
    private Text teacherName;
    @FXML
    private Text staffMaleGenderCount;
    @FXML
    private Text staffFemaleGenderCount;
    @FXML
    private Text staffTotalGenderCount;
    @FXML
    private TextField teacherSearch;
    @FXML
    private AnchorPane addTeacherPane;
    @FXML
    private JFXButton staffResetBtn;
    @FXML
    private JFXButton chooseEmployeePhoto;
    @FXML
    private GNAvatarView avatarEmployeeView;
    @FXML
    private Hyperlink viewTeacher;
    @FXML
    private JFXTextField employeeID;
    @FXML
    private JFXTextField employeeFirstName;
    @FXML
    private JFXTextField employeeLastName;
    @FXML
    private JFXTextField employeeMiddleName;
    @FXML
    private JFXComboBox<?> selectQualification;
    @FXML
    private JFXComboBox<?> selectTeacherGender;
    @FXML
    private JFXTextField employeePhoneNumber;
    @FXML
    private JFXDatePicker employeDOB;
    @FXML
    private JFXComboBox<?> selectMaritalStatus;
    @FXML
    private JFXTextField employeeResidence;
    @FXML
    private JFXTextField employeeEmail;
    @FXML
    private JFXTextField employeeNationality;
    @FXML
    private JFXComboBox<?> selectDesignation;
    @FXML
    private JFXComboBox<?> employeeReligion;
    @FXML
    private AnchorPane primary_SecPaneStaff;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addTeacher(ActionEvent event) {
    }

    @FXML
    private void editTeacher(ActionEvent event) {
    }

    @FXML
    private void deleteTeacher(ActionEvent event) {
    }

    @FXML
    private void searchTeacherDetails(KeyEvent event) {
    }

    @FXML
    private void saveEmployeeDetails(ActionEvent event) {
    }

    @FXML
    private void staffResetBtn(ActionEvent event) {
    }

    @FXML
    private void chooseEmployeeImage(ActionEvent event) {
    }

    @FXML
    private void backFromAddTeacher(ActionEvent event) {
    }

    @FXML
    private void selectStaffSchool(ActionEvent event) {
    }

    @FXML
    private void staffPrimary(ActionEvent event) {
    }

    @FXML
    private void staffSecondary(ActionEvent event) {
    }
    
}
