package controllers.Student;





import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.controlsfx.control.Rating;
import DataBase.DataSource;
/**
 *
 * @author t4h4
 */
public class students_feedback_service {
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public students_feedback_service() {
        conn = DataSource.getInstance().getCnx();
    }

    public void ajouterStudentReview(students_feedback stud_rev) {
        String req = "insert into students_feedback (comment,note,recommandation,rating,id_tutor,id_sutdent) values (?,?,?,?,?,?)";

        try {

            pst = conn.prepareStatement(req);
            pst.setString(1, stud_rev.getComment());
            pst.setInt(2, stud_rev.getNote());
            pst.setBoolean(3, stud_rev.getRecommandation());
            // pst.setDate(4, (java.sql.Date) stud_rev.getTodayD());
            pst.setDouble(4, stud_rev.getNbstars());
            pst.setInt(5, stud_rev.getTutorID());
            pst.setInt(6, stud_rev.getStudentID());
            pst.executeUpdate();

        } catch (SQLException ex) {
          //  Logger.getLogger(matiere_service.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<students_feedback> showReviews() { //From Session Variable ?
        String req = "SELECT * from students_feedback ";
        Date da = java.util.Calendar.getInstance().getTime();

        ObservableList<students_feedback> reviewList = FXCollections.observableArrayList();

        try {
            pst = conn.prepareStatement(req);
            //   pst.setInt(1, 1);
            rs = pst.executeQuery();

            while (rs.next()) {
                int stuID = rs.getInt("id_sutdent");
                int tutoID = rs.getInt("id_tutor");
                boolean recom = rs.getBoolean("recommandation");
                int rat = rs.getInt("rating");
                int not  = rs.getInt("note");
                String com = rs.getString("comment");
                Date di = rs.getDate("Date") ;
                int REVID = rs.getInt("id") ;


                java.sql.Date d = new java.sql.Date(da.getTime());

                reviewList.add(new students_feedback(REVID,stuID,tutoID,com,da,rat,not,recom ));
                //public students_feedback( int studentID, int tutorID, String comment , Date todayD ,  double nbstars, int note,boolean recommandation )
                System.out.println("ffff" + d);

            }
        } catch (SQLException ex) {
//            Logger.getLogger(matiere_service.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());

        }
        return reviewList;
    }



    public ObservableList<students_feedback> showdreviewsSS() { //From Session Variable ?
        String req = "SELECT * from students_feedback ";
        Date da = java.util.Calendar.getInstance().getTime();

        ObservableList<students_feedback> reviewList = FXCollections.observableArrayList();

        try {
            pst = conn.prepareStatement(req);
            //   pst.setInt(1, 1);
            rs = pst.executeQuery();

            while (rs.next()) {
                int stuID = rs.getInt("id_sutdent");
                int tutoID = rs.getInt("id_tutor");
                boolean recom = rs.getBoolean("recommandation");
                int rat = rs.getInt("rating");
                int not  = rs.getInt("note");
                String com = rs.getString("comment");
                Date di = rs.getDate("Date") ;
                int REVID = rs.getInt("id") ;


                java.sql.Date d = new java.sql.Date(da.getTime());

                reviewList.add(new students_feedback(REVID,stuID,tutoID,com,da,rat,not,recom ));
                //public students_feedback( int studentID, int tutorID, String comment , Date todayD ,  double nbstars, int note,boolean recommandation )
                System.out.println("ffff" + d);

            }
        } catch (SQLException ex) {
  //          Logger.getLogger(matiere_service.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());

        }
        return reviewList;
    }


    public void deletedrow(int i ) {


        String req = "DELETE FROM  students_feedback WHERE id=? ";

        try {

            pst = conn.prepareStatement(req);
            pst.setInt(1,i);

            pst.executeUpdate();

        } catch (SQLException ex) {
           // Logger.getLogger(matiere_service.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());

        }
    }

    public ObservableList<students_feedback> showReviewsrecher() { //From Session Variable ?
        String req = "SELECT * from students_feedback  where id=7 ";
        Date da = java.util.Calendar.getInstance().getTime();

        ObservableList<students_feedback> reviewList = FXCollections.observableArrayList();

        try {
            pst = conn.prepareStatement(req);
            //   pst.setInt(1, 1);
            rs = pst.executeQuery();

            while (rs.next()) {
                int stuID = rs.getInt("id_sutdent");
                int tutoID = rs.getInt("id_tutor");
                boolean recom = rs.getBoolean("recommandation");
                int rat = rs.getInt("rating");
                int not  = rs.getInt("note");
                String com = rs.getString("comment");
                Date di = rs.getDate("Date") ;
                int REVID = rs.getInt("id") ;


                java.sql.Date d = new java.sql.Date(da.getTime());

                reviewList.add(new students_feedback(REVID,stuID,tutoID,com,da,rat,not,recom ));
                //public students_feedback( int studentID, int tutorID, String comment , Date todayD ,  double nbstars, int note,boolean recommandation )
                System.out.println("ffff" + d);

            }
        } catch (SQLException ex) {
        //    Logger.getLogger(matiere_service.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());

        }
        return reviewList;
    }

}


