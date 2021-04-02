package controllers.Student;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VideoCallController implements Initializable {

    @FXML
    private Button btnMeet;
    @FXML
    private Button btnServer;



    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == btnMeet) {
            loadStage("JoinMeet.fxml");
        } else if (mouseEvent.getSource() == btnServer) {
            loadStage("JoinServer.fxml");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void loadStage(String fxml) {
        try {
            URL root_url = new File("src/main/java/fxml/Student/"+fxml).toURI().toURL();
            URL icon_url = new File("src/main/java/icons/logoo.png").toURI().toURL();
            Parent root = FXMLLoader.load(root_url);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image(String.valueOf(icon_url)));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}