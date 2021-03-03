/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopver;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class LoginPageController implements Initializable {

    @FXML
    private TextField Login;
    @FXML
    private TextField password;
    @FXML
    private Button submitBtn;
    @FXML
    private Button passwordForgotbtn;
    @FXML
    private Label showPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        submitBtn.setOnAction(e -> {
            Login.getText();
            password.getText();
        });
        passwordForgotbtn.setOnAction(e1->{
            showPass.setText(Login.getText()+" "+password.getText());
        });

    }

}
