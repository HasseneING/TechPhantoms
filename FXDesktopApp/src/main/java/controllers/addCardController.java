package controllers;

import com.stripe.model.Customer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import model.paymentCard;

public class addCardController {
    @FXML
    private TextField CardNumberTF;
    @FXML
    private TextField MMTF;
    @FXML
    private TextField YYF;
    @FXML
    private TextField CVCTF;
    @FXML
    private TextField CardHolderTF;
    @FXML
    private TextField CountryTF;
    @FXML
    private TextField AdrLine1TF;
    @FXML
    private TextField AdrLine2TF;
    @FXML
    private TextField CityTF;
    @FXML
    private Button submit;
    @FXML
    private Button cancel;


    public void addCardBTN() {
        paymentCard card = new paymentCard(CardNumberTF.getText(), MMTF.getText(), YYF.getText(), CVCTF.getText(), CardHolderTF.getText(), CountryTF.getText(), AdrLine1TF.getText(), AdrLine2TF.getText(), CityTF.getText());



        authentication stripeService = new authentication();

        Stage stage = (Stage) submit.getScene().getWindow();
        // Step 2
        User user = (User) stage.getUserData();
        //user.setCustomerID("cus_JBnAXvwkN6RAoe");//Just to test quickly
        if (stripeService.addCard(user.getCustomerID(), card) != null) {
            stage.close();
            sceneLoader SL = new sceneLoader(submit, "chargeCustomer.fxml",user);
        }
    }

    public void cancelBtn() {

        // Add it in the database

        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();
        sceneLoader SL = new sceneLoader(submit, "addCustomer.fxml",null);


    }
}
