package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.opencv.core.Core;
import java.io.File;
import java.net.URL;
public class Main extends Application {
    public static boolean isSplashLoaded = false;



    @Override
    public void start(Stage primaryStage) throws Exception{
        URL root_url = new File("src/main/java/fxml/Student/make_review.fxml").toURI().toURL();
        Parent parent = FXMLLoader.load(root_url);
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle("TalkTo");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

