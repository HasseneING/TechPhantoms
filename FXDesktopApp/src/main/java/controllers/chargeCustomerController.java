package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.User;

public class chargeCustomerController {


    @FXML
    private Button submit;

    public void payMe() {

        Stage stage = (Stage) submit.getScene().getWindow();
        User user = (User) stage.getUserData();

        authentication stripeService = new authentication();
        if (stripeService.chargeCustomer("cus_JBnAXvwkN6RAoe", 10L) != null)
        //  if (stripeService.chargeCustomer(user.getCustomerID(), 10L) != null)
        {
            stage.close();
            sceneLoader SL = new sceneLoader(submit, "Home.fxml", null);
        }// Add it in the database
        else{
            sceneLoader SL = new sceneLoader(submit, "addCustomer.fxml", null);
            }

    }
}
