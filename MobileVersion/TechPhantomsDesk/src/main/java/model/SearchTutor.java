package model;


import com.jfoenix.controls.JFXButton;
import controllers.UpdateMeet;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;

public class SearchTutor extends Pane {
    protected final ImageView photo;
    protected final Image image;
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

    public SearchTutor(int Id, String Name, String Link, String Pass, String pic) {
       image =new Image("icons/"+pic);
        photo=new ImageView();
        update = new JFXButton();
        delete = new JFXButton();
        name = new Label();
        link = new Label();
        label = new Label();
        pass = new Label();
        date = new Label();
        label0 = new Label();
        label1 = new Label();

        setId(Id + "");
        setPrefHeight(280.0);
        setPrefWidth(300.0);
        setStyle("-fx-background-color:#FFF; -fx-border-radius: 10px; -fx-background-radius: 10px;");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setHeight(3);
        dropShadow.setWidth(3);
        dropShadow.setBlurType(BlurType.TWO_PASS_BOX);
        setEffect(dropShadow);


        update.setText("           Book\n    Appointement");
        update.setLayoutX(120.0);
        update.setLayoutY(56.0);
        update.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                // Stage previous = (Stage) update.getScene().getWindow();
                //  previous.close();
                FXMLLoader loder = new FXMLLoader();
                loder.setLocation(this.getClass().getClassLoader().getResource("fxml/student/BookAppointement.fxml"));
                // root = FXMLLoader.load(this.getClass().getClassLoader().getResource("UpdateMeet.fxml"));
                try {
                    loder.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent parent = loder.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }
        });

        delete.setText("Delete");
        delete.setLayoutX(30.0);
        delete.setLayoutY(56.0);
        delete.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                FXMLLoader loder=new FXMLLoader();
                loder.setLocation(this.getClass().getClassLoader().getResource("fxml/student/BookAppointement.fxml"));
                // root = FXMLLoader.load(this.getClass().getClassLoader().getResource("UpdateMeet.fxml"));
                try {
                    loder.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent parent = loder.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }
        });
   //     photo.setRadius(45.0);
    //    photo.setStroke(javafx.scene.paint.Color.valueOf("#d7d7d7"));
    //    photo.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);



        // apply a shadow effect.
        photo.setEffect(new DropShadow(20, Color.BLACK));
        photo.setFitWidth(100.0);
        photo.setFitHeight(100.0);
        photo.setX(100.0);
        photo.setY(20.0);
        photo.setImage(image);



        pass.setAlignment(javafx.geometry.Pos.CENTER);
        pass.setContentDisplay(ContentDisplay.CENTER);
        pass.setLayoutX(90.0);
        pass.setLayoutY(140.0);
        pass.setText(Name+"  "+Link);
        pass.setFont(new Font(20.0));
        pass.setStyle("-fx-font-weight: bold");


        label1.setAlignment(javafx.geometry.Pos.TOP_RIGHT);
        label1.setContentDisplay(ContentDisplay.CENTER);
        label1.setLayoutX(50.0);
        label1.setLayoutY(170.0);
        label1.setText("Course :");
        label1.setFont(new Font(16.0));

        link.setAlignment(javafx.geometry.Pos.CENTER);
        link.setContentDisplay(ContentDisplay.CENTER);
        link.setLayoutX(120.0);
        link.setLayoutY(170.0);
        link.setText(Pass);
        link.setFont(new Font(16.0));


        update.setStyle("-fx-background-color:#1B6CD7;");
        update.setLayoutY(210);
        update.setLayoutX(60);
        update.setTextFill(Color.WHITE);
        update.setPrefWidth(190.0);
        update.setPrefHeight(48);


        getStylesheets().add("/css/CardDesign.css");
        getChildren().addAll(update, name, link, label, date, pass, label0, label1,photo);

    }

    private void deleteButton(int id) throws SQLException {
        String req = "DELETE FROM meet WHERE meet_id=?";
        pst = conn.prepareStatement(req);
        pst.setInt(1, id);
        pst.executeUpdate();
        System.out.println("Room Deleted " + id);
    }

}