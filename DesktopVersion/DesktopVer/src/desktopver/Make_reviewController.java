/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopver;
import entite.dateEmploi;
import entite.students_feedback;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.controlsfx.control.Rating;
import service.students_feedback_service;


/**
 * FXML Controller class
 *
 * @author t4h4
 */
public class Make_reviewController implements Initializable {

    @FXML
    private Button submitrev;
    @FXML
    private Text labeltext ,  emoji_rate ;
     @FXML
    private Rating stars;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addreview() {
        labeltext.setText(" Oui MR" ); 
        stars.setRating(5);
    }
        @FXML

    private void smiley() {
      double  nbStars = stars.getRating()  ;
      if (nbStars == 1 )  emoji_rate.setText("very bad " ) ;
      else if (nbStars == 2 )  emoji_rate.setText("Accepted " ) ;
      else if (nbStars == 3 )  emoji_rate.setText("Normal " ) ;
      else if (nbStars == 4 )  emoji_rate.setText("Good" ) ;
      else  emoji_rate.setText("Excellent ! " ) ;
        
        
    }
     @FXML
    private TextField note;
      @FXML
    private TextArea comment;
      @FXML
    private RadioButton rc_ys;
          @FXML

    private void submitreview() { 
     // String hh=   comment.getText();
     //   stars.getRating()  ;
   //      note.getValue();
      Date da = java.util.Calendar.getInstance().getTime();
      // java.util.Date utilStartDate = jDateChooserStart.getDate();
java.sql.Date d = new java.sql.Date(da.getTime());
     
       boolean rec ; 
       rec = rc_ys.isSelected(); 

         students_feedback_service newR = new students_feedback_service();
        newR.ajouterStudentReview(new students_feedback (5, 6,"eeee",d,6,6,true));
        //comment.getText(), stars.getRating() , Integer.parseInt(note.getText()),rec)
        System.out.println("lireeeeeeee");
     }   
}
        
