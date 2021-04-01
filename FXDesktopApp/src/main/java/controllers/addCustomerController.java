package controllers;

import com.stripe.Stripe;
import com.stripe.param.*;
import com.stripe.model.*;
import com.stripe.net.*;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class addCustomerController implements Initializable {

    @FXML
    private TextField FullNameTF;
    @FXML
    private TextField EmailTF;
    @FXML
    private TextField PhoneTF;
    @FXML
    private TextField CityTF;
    @FXML
    private TextField PostalCodeTF;
    @FXML
    private Button submit;

    public void alertBox(String text, String Title, String HeaderText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Title);
        alert.setHeaderText(HeaderText);
        alert.setContentText(text);
        alert.showAndWait();

    }

    public void proceedToPaymentBTN() {

        User customer=new User(FullNameTF.getText(), EmailTF.getText(), PhoneTF.getText(),CityTF.getText(), PostalCodeTF.getText());
        authentication stripeService=new authentication();
        customer.setCustomerID(stripeService.addCustomer(customer));
        if(customer.getCustomerID()!=null){
            // Add it in the database
            Stage stage = (Stage) submit.getScene().getWindow();
            stage.close();
            sceneLoader SL=new sceneLoader(submit,"addCard.fxml",customer);
        }




    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
