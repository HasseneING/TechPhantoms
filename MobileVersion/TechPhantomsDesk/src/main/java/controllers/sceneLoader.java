package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class sceneLoader {

    public sceneLoader(Button submit, String FXMLPage, Object o){

     /*   try {

            Stage primaryStage=(Stage) submit.getScene().getWindow();
            URL url = new File(FXMLPage).toURI().toURL();
            Parent root = FXMLLoader.load(url);
            primaryStage.setScene(new Scene(root, 970, 601));
            primaryStage.setUserData(o);
            primaryStage.setTitle("TeachMe Desktop Application");
            primaryStage.show();
        }catch (IOException io){
            io.printStackTrace();
        }*/
        try {
            URL root_url = new File("src/main/java/fxml/Student/"+FXMLPage).toURI().toURL();
            URL icon_url = new File("src/main/java/icons/logoo.png").toURI().toURL();
            Parent root = FXMLLoader.load(root_url);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setUserData(o);
            stage.getIcons().add(new Image(String.valueOf(icon_url)));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
