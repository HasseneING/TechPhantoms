package Taha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.Rating;

import java.io.IOException;

import static java.lang.Integer.parseInt;

/**
 * t4h44
 *
 */

public class StudentListViewCell extends ListCell<students_feedback> {

@FXML
private Label label1;
@FXML
private GridPane ok;
@FXML
private Label labelnote;
@FXML
private Label labelrec;
@FXML
private Label labeldate;
@FXML
private Label labeltutor;  
@FXML
private Label labelstudent; 
 @FXML
 private Rating  st ;   

 @FXML
private Label idrev; 
 @FXML
 private Button btndld ; 
    private FXMLLoader mLLoader;

   Admin_reviewsController controller = new Admin_reviewsController();

    
    @FXML
    private void dlbtn(ActionEvent event) {
        students_feedback_service ok = new students_feedback_service();
        
ok.deletedrow(parseInt(idrev.getText())) ;   
controller.showcells();


    }
    
    @Override
    protected void updateItem(students_feedback student, boolean empty) {
        super.updateItem(student, empty);

        if(empty || student == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Taha/cell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            label1.setText(student.getComment());
             st.setRating(student.getNbstars()); 
             labelnote.setText(String.valueOf(student.getNote()));
          
             if (student.getRecommandation())   labelrec.setText("Yes" ) ; 
             else labelrec.setText("No" ) ; 
              labeltutor.setText(String.valueOf(student.getTutorID()));
              labelstudent.setText(String.valueOf(student.getStudentID()));
                  labeldate.setText(String.valueOf(student.getTodayD()));
                  idrev.setText(String.valueOf(student.getReviewid())) ;
                  
                  
                  

            setText(null);
           setGraphic(ok);
        }

    }
}
