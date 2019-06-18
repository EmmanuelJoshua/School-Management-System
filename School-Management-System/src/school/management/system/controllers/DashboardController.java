/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.management.system.controllers;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private JFXButton dashboardBtn;
    @FXML
    private JFXButton studentBtn;
    @FXML
    private FontAwesomeIconView stuIcon;
    @FXML
    private MaterialDesignIconView dashIcon;

    @FXML
    private AnchorPane mainDashPane;

    @FXML
    private void openDashboard(ActionEvent event) {
        studentBtn.getStyleClass().remove(2);
        stuIcon.getStyleClass().remove(2);
        dashboardBtn.getStyleClass().add("active");
        dashIcon.getStyleClass().add("iconActive");
        dashboardPane.setVisible(true);
        studentPane.setVisible(false);
    }

    @FXML
    private void openStudent(ActionEvent event) {
        dashboardBtn.getStyleClass().remove(2);
        dashIcon.getStyleClass().remove(2);
        studentBtn.getStyleClass().add("active");
        stuIcon.getStyleClass().add("iconActive");
        studentPane.setVisible(true);
        dashboardPane.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection con = da.getConnection();
            ResultSet res = con.createStatement().executeQuery("SELECT RegistrationNumber,FirstName+' '+MiddleName+' '+LastName AS 'FullName',DOB,Gender,s.Class,ResidentialAddress,PaymentStatus FROM Students.StudentDetails s\n"
                    + "INNER JOIN Students.PaymentDetails p ON p.StudentId=s.StudentId;");

            while (res.next()) {
                obs.add(new AdminStudentsTable(res.getString("RegistrationNumber"), res.getString("FullName"),
                        res.getString("DOB"), res.getString("Gender"), res.getString("Class"),
                        res.getString("ResidentialAddress"), res.getString("PaymentStatus")));
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
    }

    public void closeStage() {
        ((Stage) mainDashPane.getScene().getWindow()).close();
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {
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
