/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Taha;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.opencv.core.Core;

import java.io.IOException;

/**
 *
 * @author Administrateur
 */
public class DesktopVer extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        System.load("C:\\Users\\MJ-INFO\\Desktop\\Codename\\ok\\ib\\opencv_java3413.dll");
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Taha/make_review.fxml"));

        

        primaryStage.setTitle("9arrini");

        primaryStage.setScene(new Scene (root,1100,600));

        
        primaryStage.show();
                
    
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
