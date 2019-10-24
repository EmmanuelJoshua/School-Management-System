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
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.octicons.OctIconView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
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
    static String teacherID;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox views;
    private AnchorPane primary_SecPaneStudent;
    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private JFXListView<String> newsList;
    @FXML
    private JFXListView<Label> view;
    @FXML
    private TableView<?> gradesStudents;
    @FXML
    private TableView<?> gradesTable;
    @FXML
    private AnchorPane classesPane;
    @FXML
    private Pane classesPrimary;
    @FXML
    private Pane classesSecondary;
    @FXML
    private TableView<?> classesTable;
    @FXML
    private JFXButton dashboardBtn;
    @FXML
    private MaterialDesignIconView dashIcon;
    @FXML
    private JFXButton classBtn;
    @FXML
    private MaterialDesignIconView classIcon;
    @FXML
    private TableColumn<?, ?> registrationNumber;
    @FXML
    private TableColumn<?, ?> fullName;
    @FXML
    private TableColumn<?, ?> dob;
    @FXML
    private TableColumn<?, ?> gender;
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
    private JFXComboBox<?> classesSearch;
    @FXML
    private TextField classes_nameSearch;
    @FXML
    private JFXComboBox<?> classes_genderSearch;
    @FXML
    private JFXComboBox<?> classes_paymentSearch;
    @FXML
    private TextField parents_nameSearch;
    @FXML
    private JFXComboBox<?> parents_genderSearch;
    @FXML
    private JFXComboBox<?> parentsSearch;
    @FXML
    private TextField grades_nameSearch;
    @FXML
    private JFXComboBox<?> grades_genderSearch;
    @FXML
    private JFXComboBox<?> gradesSearch;
    @FXML
    private StackPane addNews;
    @FXML
    private JFXButton addNewsAction;
    @FXML
    private JFXButton CancelAddNews;

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
            delete.setOnAction(e -> getListView().getItems().remove(getItem()));
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            birthdays();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StaffDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                settingsPane.setDisable(false);
                gradesPane.setDisable(false);
                parentsPane.setDisable(false);
                logoutPane.setVisible(false);
            });
        });

        addNewsAction.setOnAction((ActionEvent event) -> {

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

        ObservableList<String> news = FXCollections.observableArrayList("PTA Meeting - 19/11/2019", "Interhouse Sport - 30/24/2019");
        newsList.setItems(news);
        newsList.setCellFactory(param -> new cell());

    }

    private void populateComboBoxes() {
        ObservableList classesSearchOptions = FXCollections.observableArrayList(
                "Name", "Gender", "Payment Status");
        classesSearch.setItems(classesSearchOptions);
        ObservableList parentsSearchOptions = FXCollections.observableArrayList(
                "Name", "Gender");
        gradesSearch.setItems(parentsSearchOptions);
        parentsSearch.setItems(parentsSearchOptions);
        ObservableList genderOptions = FXCollections.observableArrayList(
                "Male", "Female");
        grades_genderSearch.setItems(genderOptions);
        classes_genderSearch.setItems(genderOptions);
        parents_genderSearch.setItems(genderOptions);
        ObservableList paymentOptions = FXCollections.observableArrayList(
                "Fully Paid", "Pending", "Not paid");
        classes_paymentSearch.setItems(paymentOptions);
    }

    @FXML
    public void handleClassesSearch(ActionEvent event) {
        if (classesSearch.getValue().equals("Name")) {
            classes_nameSearch.setVisible(true);
            classes_nameSearch.clear();
            classes_genderSearch.setVisible(false);
            classes_paymentSearch.setVisible(false);
        } else if (classesSearch.getValue().equals("Gender")) {
            classes_genderSearch.setVisible(true);
            classes_genderSearch.getSelectionModel().clearSelection();
            classes_nameSearch.setVisible(false);
            classes_paymentSearch.setVisible(false);
        } else if (classesSearch.getValue().equals("Payment Status")) {
            classes_paymentSearch.setVisible(true);
            classes_paymentSearch.getSelectionModel().clearSelection();
            classes_genderSearch.setVisible(false);
            classes_nameSearch.setVisible(false);
        }
    }

    @FXML
    public void handleParentsSearch(ActionEvent event) {
        if (parentsSearch.getValue().equals("Name")) {
            parents_nameSearch.setVisible(true);
            parents_nameSearch.clear();
            parents_genderSearch.setVisible(false);
        } else if (parentsSearch.getValue().equals("Gender")) {
            parents_genderSearch.setVisible(true);
            parents_genderSearch.getSelectionModel().clearSelection();
            parents_nameSearch.setVisible(false);
        }
    }

    @FXML
    public void handleGradesSearch(ActionEvent event) {
        if (gradesSearch.getValue().equals("Name")) {
            grades_nameSearch.setVisible(true);
            grades_nameSearch.clear();
            grades_genderSearch.setVisible(false);
        } else if (gradesSearch.getValue().equals("Gender")) {
            grades_genderSearch.setVisible(true);
            grades_genderSearch.getSelectionModel().clearSelection();
            grades_nameSearch.setVisible(false);
        }
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
    private void openDashboard(ActionEvent event) {

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
        parentsPane.setVisible(false);
        settingsPane.setVisible(false);
        gradesPane.setVisible(false);;
    }

    @FXML
    void openParents(ActionEvent event) {
        if (dashboardBtn.getStyleClass().size() == 2 && dashIcon.getStyleClass().size() == 2) {

        } else if (dashboardBtn.getStyleClass().size() == 3 && dashIcon.getStyleClass().size() == 3) {
            dashboardBtn.getStyleClass().remove(2);
            dashIcon.getStyleClass().remove(2);
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
        settingsPane.setVisible(false);
    }

    @FXML
    void openGrade(ActionEvent event) {
        if (dashboardBtn.getStyleClass().size() == 2 && dashIcon.getStyleClass().size() == 2) {

        } else if (dashboardBtn.getStyleClass().size() == 3 && dashIcon.getStyleClass().size() == 3) {
            dashboardBtn.getStyleClass().remove(2);
            dashIcon.getStyleClass().remove(2);
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
    private void addGrade(ActionEvent event) {
    }

    @FXML
    private void searchStudentDetails(KeyEvent event) {
    }

    @FXML
    private void saveAction(ActionEvent event) {
    }

}
