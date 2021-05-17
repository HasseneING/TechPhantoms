package model;


import com.jfoenix.controls.JFXButton;
import controllers.UpdateMeet;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;

public class AppointementCard extends Pane {

    protected final Label name;
    protected final Label link;
    protected final Label label;
    protected final Label date;
    protected final Label pass;
    protected final Label label0;
    protected final Label label1;
    protected JFXButton update;
    protected JFXButton delete;
    private int ID;
    private Connection conn = utils.DataSource.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    public AppointementCard(int Id, String Date, String student) {

        update = new JFXButton();
        delete =new JFXButton();
        name = new Label();
        link = new Label();
        label = new Label();
        pass = new Label();
        date = new Label();
        label0 = new Label();
        label1=new Label();

        setId(Id + "");
        setPrefHeight(280.0);
        setPrefWidth(300.0);
        setStyle("-fx-background-color:#FFF; -fx-border-radius: 10px; -fx-background-radius: 10px;");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setHeight(3);
        dropShadow.setWidth(3);
        dropShadow.setBlurType(BlurType.TWO_PASS_BOX);
        setEffect(dropShadow);

        delete.setText("Delete");
        delete.setLayoutX(30.0);
        delete.setLayoutY(56.0);
        delete.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {

                try {
                    deleteButton(Integer.parseInt(getId()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setContentText("Meeting  "+getId().toString()+" has been deleted");
                alert.showAndWait();

            }
        });
//        photo.setRadius(45.0);
//        photo.setStroke(javafx.scene.paint.Color.valueOf("#d7d7d7"));
//        photo.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);

        date.setAlignment(javafx.geometry.Pos.CENTER);
        date.setContentDisplay(ContentDisplay.CENTER);
        date.setLayoutX(8.0);
        date.setLayoutY(50.0);
        date.setPrefHeight(26.0);
        date.setPrefWidth(289.0);
        date.setText("Reservation details");
        date.setFont(new Font(20.0));
        date.setStyle("-fx-font-weight: bold");


        label1.setAlignment(javafx.geometry.Pos.TOP_RIGHT);
        label1.setContentDisplay(ContentDisplay.CENTER);
        label1.setLayoutX(70.0);
        label1.setLayoutY(100.0);
        label1.setText("Date :");
        label1.setFont(new Font(15.0));

        link.setAlignment(javafx.geometry.Pos.CENTER);
        link.setContentDisplay(ContentDisplay.CENTER);
        link.setLayoutX(120.0);
        link.setLayoutY(100.0);
        link.setText(Date);
        link.setFont(new Font(15.0));

        label.setAlignment(javafx.geometry.Pos.TOP_RIGHT);
        label.setContentDisplay(ContentDisplay.CENTER);
        label.setLayoutX(70.0);
        label.setLayoutY(140.0);
        label.setText("Student :");
        label.setFont(new Font(15.0));

        label0.setAlignment(javafx.geometry.Pos.TOP_RIGHT);
        label0.setContentDisplay(ContentDisplay.CENTER);
        label0.setLayoutX(140.0);
        label0.setLayoutY(140.0);
        label0.setText(student);
        label0.setFont(new Font(15.0));

        delete.setStyle("-fx-background-color:#ff0000;");
        delete.setLayoutY(200);
        delete.setLayoutX(110);
        delete.setTextFill(Color.WHITE);
        delete.setPrefWidth(80.0);
        delete.setPrefHeight(41);



        getStylesheets().add("/css/CardDesign.css");
        getChildren().addAll(delete,name,link,label,date,pass,label0,label1);

    }

    private void deleteButton(int id) throws SQLException {
        String req = "DELETE FROM reservation WHERE reservation_id=?";
        pst = conn.prepareStatement(req);
        pst.setInt(1,id);
        pst.executeUpdate();
        System.out.println("Appointement Deleted "+id);
    }

}