//<<<<<<< Updated upstream
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package desktopver;
//import entite.dateEmploi;
//import desktopver.students_feedback;
//import java.net.URL;
//import java.sql.Timestamp;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.ResourceBundle;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.RadioButton;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.text.Text;
////import org.controlsfx.control.Rating;
//import desktopver.students_feedback_service;
//
//
///**
// * FXML Controller class
// *
// * @author t4h4
// */
//public class Make_reviewController implements Initializable {
//
//    @FXML
//    private Button submitrev;
//    @FXML
//    private Text labeltext ,  emoji_rate ;
//     @FXML
//  //  private Rating stars;
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
//
//    @FXML
//    private void addreview() {
//        labeltext.setText(" Oui MR" ); 
//        stars.setRating(5);
//    }
//        @FXML
//
//    private void smiley() {
//      double  nbStars = stars.getRating()  ;
//      if (nbStars == 1 )  emoji_rate.setText("very bad " ) ;
//      else if (nbStars == 2 )  emoji_rate.setText("Accepted " ) ;
//      else if (nbStars == 3 )  emoji_rate.setText("Normal " ) ;
//      else if (nbStars == 4 )  emoji_rate.setText("Good" ) ;
//      else  emoji_rate.setText("Excellent ! " ) ;
//        
//        
//    }
//     @FXML
//    private TextField note;
//      @FXML
//    private TextArea comment;
//      @FXML
//    private RadioButton rc_ys;
//          @FXML
//
//    private void submitreview() { 
//     // String hh=   comment.getText();
//     //   stars.getRating()  ;
//   //      note.getValue();
//      Date da = java.util.Calendar.getInstance().getTime();
//      // java.util.Date utilStartDate = jDateChooserStart.getDate();
//java.sql.Date d = new java.sql.Date(da.getTime());
//     
//       boolean rec ; 
//       rec = rc_ys.isSelected(); 
//
//         students_feedback_service newR = new students_feedback_service();
//        newR.ajouterStudentReview(new students_feedback (5, 6,"eeee",d,6,6,true));
//        //comment.getText(), stars.getRating() , Integer.parseInt(note.getText()),rec)
//        System.out.println("lireeeeeeee");
//     }   
//}
//        
//=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Taha;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    @FXML
    private RadioButton rec_no;
   // Image image = new Image(new FileInputStream("E:\\9raya 3eme\\PiDEVGIT\\TechPhantoms\\DesktopVersion\\DesktopVer\\src\\desktopver\\111.png"));
   // @FXML
   // private ImageView= new  immg(image);
    @FXML
    private ImageView sademoji;
    @FXML
    private Button vidd;
 
 

      
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    private void addreview() {
        labeltext.setText(" Oui MR" ); 
        stars.setRating(5);
    }
        @FXML

    private void smiley() {
         FileInputStream input = null;
        try {
            // TODO
            input = new FileInputStream("C:\\Users\\MJ-INFO\\Desktop\\Codename\\TechPhantomsDesk\\src\\main\\resources\\icons\\1111.png");
            Image image1 = new Image(input);
             FileInputStream input2 = new FileInputStream("C:\\Users\\MJ-INFO\\Desktop\\Codename\\TechPhantomsDesk\\src\\main\\resources\\icons\\2222.png");
            Image image2 = new Image(input2);
             FileInputStream input3 = new FileInputStream("C:\\Users\\MJ-INFO\\Desktop\\Codename\\TechPhantomsDesk\\src\\main\\resources\\icons\\3333.png");
            Image image3 = new Image(input3);
             FileInputStream input4 = new FileInputStream("C:\\Users\\MJ-INFO\\Desktop\\Codename\\TechPhantomsDesk\\src\\main\\resources\\icons\\4444.png");
            Image image4 = new Image(input4);
             FileInputStream input5 = new FileInputStream("C:\\Users\\MJ-INFO\\Desktop\\Codename\\TechPhantomsDesk\\src\\main\\resources\\icons\\5555.png");
            Image image5 = new Image(input5);
            //ImageView imageView = new ImageView(image);
             double  nbStars = stars.getRating()  ;
      if (nbStars == 1 )  { emoji_rate.setText("Bad " ) ; sademoji.setImage(image1); }
      else if (nbStars == 2 )   {emoji_rate.setText("Accepted " ) ; sademoji.setImage(image2); }
      else if (nbStars == 3 ) { emoji_rate.setText("Normal " ) ; sademoji.setImage(image3); }
      else if (nbStars == 4 )  { emoji_rate.setText("Good" ) ; sademoji.setImage(image4); }
      else  { emoji_rate.setText("Excellent ! " ) ; sademoji.setImage(image5); }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Make_reviewController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(Make_reviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
        
        
      
      
      
        
        
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
      
java.sql.Date d = new java.sql.Date(da.getTime());
     
       boolean rec ; 
       rec = rc_ys.isSelected(); 
       if (comment.getText().length()<10) labeltext.setText("Write a comment please ! ") ; 
       else if ((Integer.parseInt(note.getText())<1) || (Integer.parseInt(note.getText())>10) ) labeltext.setText("Erreur Note Field ! ") ; 
else if (!(rc_ys.isSelected()) && !(rec_no.isSelected()) ) labeltext.setText("Please choose recommandation ! ") ; 
else {
         students_feedback_service newR = new students_feedback_service();
        newR.ajouterStudentReview(new students_feedback (5, 6,comment.getText(),d,stars.getRating() ,Integer.parseInt(note.getText()),rec));
        //comment.getText(), stars.getRating() , Integer.parseInt(note.getText()),rec)
        System.out.println("lireeeeeeee");
labeltext.setText("Review submitted with success ! ") ;}
     }   

    @FXML
    private void vidUpload(ActionEvent event) {
        
//
        System.load("C:\\Users\\MJ-INFO\\Desktop\\Codename\\ok\\ib\\opencv_java3413.dll");
        FXHelloCV viddok = new FXHelloCV() ;
      Stage stageVid = new Stage();
//      
      viddok.start(stageVid);
//        
        
       
        
    }


    public void makereview(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("fxml/Taha/make_review.fxml"));

            loader.load();
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
}
        
//>>>>>>> Stashed changes
