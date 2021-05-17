package controllers.student;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Home implements Initializable {
    @FXML
    private PieChart meetchart;

    @FXML
    private PieChart appointchart;

    @FXML
    private PieChart paychart;

    @FXML
    private PieChart reviewchart;

    @FXML
    private BorderPane bp;

    @FXML
    private AnchorPane ap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadPage("Courses");
    }

    @FXML
    void home(MouseEvent event) {
        loadPage("Courses");
    }

    @FXML
    void page1(MouseEvent event) {
        loadPage("Profile");
    }


    @FXML
    void page3(MouseEvent event) {
        loadPage("Chat");
    }

    @FXML
    void page4(MouseEvent event) {
        loadPage("Schedule");
    }

    @FXML
    void page5(MouseEvent event) {
        loadPage("Claims");
    }

    @FXML
    void page6(MouseEvent event) {
        loadPage("Meetings");
    }

    @FXML
    void page7(MouseEvent event) {
        loadPage("Reviews");
    }

    @FXML
    void page8(MouseEvent event) {
        loadPage("JoinMeet");
    }

    private void loadPage(String page) {
        Parent root = null;

        try {
            root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/student/"+page+".fxml"));

        } catch (IOException e) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, e);
        }

        bp.setCenter(root);
    }


}