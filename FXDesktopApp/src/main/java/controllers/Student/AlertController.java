package controllers.Student;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class AlertController {
@FXML
JFXButton btnvalid;
@FXML
JFXButton btninvalid;



    public void btnValid(){
        Stage stage = (Stage) btnvalid.getScene().getWindow();
        stage.close();
    }
    public void btnInValid() throws IOException {
        Stage stage = (Stage) btninvalid.getScene().getWindow();
        stage.close();

    }


}
