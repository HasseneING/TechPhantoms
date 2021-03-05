/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopver;

import entite.students_feedback;
import entite.tutors_feedback;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.controlsfx.control.Rating;
import service.students_feedback_service;
import service.tutors_feedback_service;

/**
 * FXML Controller class
 *
 * @author t4h4
 */
public class Tutor_make_reviewController implements Initializable {

    @FXML
    private Rating starsTT;
    @FXML
    private TextArea commtt;
    @FXML
    private RadioButton RY;
    @FXML
    private RadioButton RN;
    @FXML
    private Button subTT;
    @FXML
    private TextField notett;
    @FXML
    private Text labeltext1;
    @FXML
    private ImageView sademoji2;
    @FXML
    private Text emoji_rate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addTTreview() {
         Date da = java.util.Calendar.getInstance().getTime();
      
java.sql.Date d = new java.sql.Date(da.getTime());
         boolean rec ; 
       rec = RY.isSelected(); 
       if (commtt.getText().length()<10) labeltext1.setText("Write a comment please ! ") ; 
       else if ((Integer.parseInt(notett.getText())<1) || (Integer.parseInt(notett.getText())>10) ) labeltext1.setText("Erreur Note Field ! ") ; 
else if ((!(RY.isSelected())) && (!(RN.isSelected()) )) labeltext1.setText("Please choose recommandation ! ") ; 
else {
         tutors_feedback_service newR = new tutors_feedback_service();
         
        newR.ajoutertutorReview(new tutors_feedback(5, 6,commtt.getText(),d,starsTT.getRating() ,Integer.parseInt(notett.getText()),rec));
        //comment.getText(), stars.getRating() , Integer.parseInt(note.getText()),rec)
        System.out.println("lireeeeeeee");
labeltext1.setText("Review submitted with success ! ") ;}
     }   
    @FXML
     private void smiley2() {
         FileInputStream input = null;
        try {
            // TODO
            input = new FileInputStream("E:\\9raya 3eme\\PiDEVGIT\\TechPhantoms\\DesktopVersion\\DesktopVer\\src\\desktopver\\1111.png");
            Image image1 = new Image(input);
             FileInputStream input2 = new FileInputStream("E:\\9raya 3eme\\PiDEVGIT\\TechPhantoms\\DesktopVersion\\DesktopVer\\src\\desktopver\\2222.png");
            Image image2 = new Image(input2);
             FileInputStream input3 = new FileInputStream("E:\\9raya 3eme\\PiDEVGIT\\TechPhantoms\\DesktopVersion\\DesktopVer\\src\\desktopver\\3333.png");
            Image image3 = new Image(input3);
             FileInputStream input4 = new FileInputStream("E:\\9raya 3eme\\PiDEVGIT\\TechPhantoms\\DesktopVersion\\DesktopVer\\src\\desktopver\\4444.png");
            Image image4 = new Image(input4);
             FileInputStream input5 = new FileInputStream("E:\\9raya 3eme\\PiDEVGIT\\TechPhantoms\\DesktopVersion\\DesktopVer\\src\\desktopver\\5555.png");
            Image image5 = new Image(input5);
            //ImageView imageView = new ImageView(image);
             double  nbStars = starsTT.getRating()  ;
      if (nbStars == 1 )  { emoji_rate.setText("Bad " ) ; sademoji2.setImage(image1); }
      else if (nbStars == 2 )   {emoji_rate.setText("Accepted " ) ; sademoji2.setImage(image2); }
      else if (nbStars == 3 ) { emoji_rate.setText("Normal " ) ; sademoji2.setImage(image3); }
      else if (nbStars == 4 )  { emoji_rate.setText("Good" ) ; sademoji2.setImage(image4); }
      else  { emoji_rate.setText("Excellent ! " ) ; sademoji2.setImage(image5); }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Make_reviewController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(Make_reviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }}
}
    

