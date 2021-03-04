/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Administrateur
 */
public class AlertBoxPaiement {

    public static void display(String title, String message) throws FileNotFoundException {

        InputStream stream = new FileInputStream("H:\\Desktop Admin\\PiDevGIT\\TechPhantoms\\DesktopVersion\\DesktopVer\\src\\utils\\TeacherQR.png");
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        //Setting image to the image view
        imageView.setImage(image);

        imageView.setX(10);
        imageView.setY(10);
        imageView.setFitWidth(575);
        imageView.setPreserveRatio(true);
      Group root = new Group(imageView);

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(600);
        window.setMinHeight(700);

        Label label = new Label();
        label.setText(message);

        Scene scene = new Scene(root, 700, 600);
        window.setScene(scene);
        window.showAndWait();

    }
}

      //Setting the Scene object
