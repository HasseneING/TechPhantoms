/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Taha;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author t4h4
 */
public class Admin_reviewsController implements Initializable {
 private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    @FXML
    private Button lirebt;
           

    @FXML
    private TableView<students_feedback> tablerevieww;
    @FXML
    private TableColumn<students_feedback, ?> clstudentID;
    @FXML
    private TableColumn<students_feedback,?>  cltutorid;
    @FXML
    private TableColumn<students_feedback, ?>  clcomment;

    @FXML
    private TableColumn<students_feedback, Date> cltodayD;
   
    @FXML
    private TableColumn<students_feedback, ?> clnote;
    @FXML
    private TableColumn<students_feedback, ?> clrecomm;
    @FXML
    private TableColumn<students_feedback, Rating> starsrat;
    @FXML
    private TableColumn<students_feedback, ?> reviewID;
    @FXML
    private Button deletbtn;
    @FXML
    private ChoiceBox  selectshow;
    @FXML
    private Button stats;
    @FXML
    private Button pdff;
    @FXML
    private TextField recherche;
    @FXML
    private Button studentCH;
    @FXML
    public ListView<students_feedback> listt;
     public ObservableList<students_feedback> studentObservableList;
    
     
    

    /**
     * Initializes the controller class.
     */
     
     
      public Admin_reviewsController()  {

       // studentObservableList = FXCollections.observableArrayList();
 //***********students_feedback_service okkk = new students_feedback_service();
      
       
   
//         clstudentID.setCellValueFactory(new PropertyValueFactory("studentID"));
//          cltutorid.setCellValueFactory(new PropertyValueFactory("tutorID"));
//           clcomment.setCellValueFactory(new PropertyValueFactory("comment"));
//            cltodayD.setCellValueFactory(new PropertyValueFactory("todayD"));
//              //clnbstars.setCellValueFactory(new PropertyValueFactory("nbstars"));
//                    clnote.setCellValueFactory(new PropertyValueFactory("note"));
//                 clrecomm.setCellValueFactory(new PropertyValueFactory("recommandation")); 
//                 starsrat.setCellValueFactory(new PropertyValueFactory("starRate"));  
//                   reviewID.setCellValueFactory(new PropertyValueFactory("reviewid"));
                 
                 
               
                 
                 
             
                 
      // *********  listt.setItems(okkk.showReviews());
   
        //add some Students
//        studentObservableList.addAll(
//                new Student("John Doe", Student.GENDER.MALE),
//                new Student("Jane Doe", Student.GENDER.FEMALE),
//                new Student("Donte Dunigan", Student.GENDER.MALE),
//                new Student("Gavin Genna", Student.GENDER.MALE),
//                new Student("Darin Dear", Student.GENDER.MALE),
//                new Student("Pura Petty", Student.GENDER.FEMALE),
//                new Student("Herma Hines", Student.GENDER.FEMALE)
//        );
     
      }
      students_feedback_service okkk = new students_feedback_service();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  liretutors(); tutors data show
      
     
                 studentObservableList = FXCollections.observableArrayList();

      listt.setItems(okkk.showdreviewsSS());
         listt.setCellFactory( studentListView -> new StudentListViewCell());
    
//       students_feedback_service okkk = new students_feedback_service();  
//      
//      
//        studentObservableList = FXCollections.observableArrayList();
//
//        //add some Students
//     
////         studentObservableList.addAll(
////                new students_feedback("eeee") 
////        );
//
//           
//                 
//        listt.setItems(okkk.showdreviewsSS());
//         listt.setCellFactory( studentListView -> new StudentListViewCell());
//      
        
       //  listt.getItems().remove(listt.getSelectionModel().getSelectedItem());
         
         
       lire() ; //student show
       
       ObservableList<String> selectshowList = FXCollections.observableArrayList("Show students reviews","Show tutors reviews");
       selectshow.setItems(selectshowList);
       selectshow.setValue("Show students reviews") ; 
       
       selectshow.setOnAction(event -> {
    if(selectshow.getValue()=="Show students reviews") lire() ; 
    else   liretutors(); 
});
    }   
    
    
    
     @FXML
    public void lire(){  
    students_feedback_service ok = new students_feedback_service();
      
       
   
         clstudentID.setCellValueFactory(new PropertyValueFactory("studentID"));
          cltutorid.setCellValueFactory(new PropertyValueFactory("tutorID"));
           clcomment.setCellValueFactory(new PropertyValueFactory("comment"));
            cltodayD.setCellValueFactory(new PropertyValueFactory("todayD"));
              //clnbstars.setCellValueFactory(new PropertyValueFactory("nbstars"));
                    clnote.setCellValueFactory(new PropertyValueFactory("note"));
                 clrecomm.setCellValueFactory(new PropertyValueFactory("recommandation")); 
                 starsrat.setCellValueFactory(new PropertyValueFactory("starRate"));  
                   reviewID.setCellValueFactory(new PropertyValueFactory("reviewid"));
                 
                 
               
                 
                 
                 
                 
        tablerevieww.setItems(ok.showReviews());
        tablerevieww.refresh();
}

    @FXML
    public  void deletselected() {

    ObservableList<students_feedback> itemlist ;
itemlist = tablerevieww.getSelectionModel().getSelectedItems() ;  
//System.out.print(itemlist.get(0).getReviewid()) ; 
students_feedback_service ok = new students_feedback_service();
   ok.deletedrow(itemlist.get(0).getReviewid()) ;
    lire() ; 

 
  
}


    @FXML
    private void statsss(ActionEvent event) {
                 PieChart pie = new PieChart();
      ObservableList<PieChart.Data> data =
         FXCollections.observableArrayList();
      data.addAll(new PieChart.Data("5 Stars (3 Courses)", 30.0),
         new PieChart.Data("4 Stars (8 Courses)", 20.3),
         new PieChart.Data("3 Stars (6 Courses)", 16.3),
         new PieChart.Data("2 Stars ( 5 Courses)", 16.3),
        
         new PieChart.Data("1 Star (9 Courses)", 5.2));

      pie.setData(data);
      pie.setTitle("Stars rating on Training courses");
      //return pie;
      Stage stagee = new Stage();
      stagee.setTitle("Stars rating on Training courses");
      StackPane pane = new StackPane();
    pane.getChildren().add(pie);
      stagee.setScene(new Scene(pane, 1100, 600));
      stagee.show();
    }

    @FXML
    private void pdffimp(ActionEvent event) throws IOException,FileNotFoundException, DocumentException, SQLException  { 
         String file_name="D:\\reviews.pdf";
            Document document = new Document();
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            
            document.open();
            DataSource db = new DataSource();
            Connection cnx = db.getCnx();
            PreparedStatement ps =null;
            ResultSet rs =null;
            String req = "Select * from matiere ";
            ps = cnx.prepareCall(req);
            rs=ps.executeQuery();
            PdfPTable t = new PdfPTable(8);
            PdfPCell c1 = new PdfPCell(new Phrase("Id"));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("Comment"));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("Note"));
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("Recommandation ")); 
            t.addCell(c4);
             PdfPCell c5 = new PdfPCell(new Phrase("Rating"));
            t.addCell(c5);
            PdfPCell c6 = new PdfPCell(new Phrase("Tutor_id"));
            t.addCell(c6);
            PdfPCell c7 = new PdfPCell(new Phrase("Student_id"));
            t.addCell(c7);
             PdfPCell c8 = new PdfPCell(new Phrase("Date"));
            t.addCell(c8);
            t.setHeaderRows(1);
            
            ObservableList<students_feedback> itemlist ;
itemlist = tablerevieww.getItems() ;  
//System.out.print(itemlist.get(0).getReviewid()) ; 

   //ok.deletedrow(itemlist.get(0).getReviewid()) ;
   
int i =0 ;
        try {
            //while(rs.next()){
            while( i <  tablerevieww.getItems().size() ){
                t.addCell(String.valueOf(itemlist.get(i).getReviewid()));
                t.addCell(itemlist.get(i).getComment());
                t.addCell(String.valueOf(itemlist.get(i).getNote()));
                t.addCell(String.valueOf(itemlist.get(i).getRecommandation()));
                t.addCell(String.valueOf(itemlist.get(i).getNbstars()));
                t.addCell(String.valueOf(itemlist.get(i).getTutorID()));
                t.addCell(String.valueOf(itemlist.get(i).getStudentID()));
                t.addCell(String.valueOf(itemlist.get(i).getTodayD()));
              i++ ; 
            }  document.add(t);
            document.close();
            System.out.println("finished");
        } catch (DocumentException ex) {
            System.out.println(ex); 
        }
    }

    @FXML
    private void rechercher(KeyEvent event) {
        System.out.print("ddd");
         students_feedback_service ok = new students_feedback_service();
      
       
   
         clstudentID.setCellValueFactory(new PropertyValueFactory("studentID"));
          cltutorid.setCellValueFactory(new PropertyValueFactory("tutorID"));
           clcomment.setCellValueFactory(new PropertyValueFactory("comment"));
            cltodayD.setCellValueFactory(new PropertyValueFactory("todayD"));
              //clnbstars.setCellValueFactory(new PropertyValueFactory("nbstars"));
                    clnote.setCellValueFactory(new PropertyValueFactory("note"));
                 clrecomm.setCellValueFactory(new PropertyValueFactory("recommandation")); 
                 starsrat.setCellValueFactory(new PropertyValueFactory("starRate"));  
                   reviewID.setCellValueFactory(new PropertyValueFactory("reviewid"));
                 
                 
               
                 
                 
                 
                 
        tablerevieww.setItems(ok.showReviewsrecher());
        tablerevieww.refresh();
        
    }

    

    
  public void liretutors(){  

       
   
         clstudentID.setCellValueFactory(new PropertyValueFactory("studentID"));
          cltutorid.setCellValueFactory(new PropertyValueFactory("tutorID"));
           clcomment.setCellValueFactory(new PropertyValueFactory("comment"));
            cltodayD.setCellValueFactory(new PropertyValueFactory("todayD"));
              //clnbstars.setCellValueFactory(new PropertyValueFactory("nbstars"));
                    clnote.setCellValueFactory(new PropertyValueFactory("note"));
                 clrecomm.setCellValueFactory(new PropertyValueFactory("recommandation")); 
                 starsrat.setCellValueFactory(new PropertyValueFactory("starRate"));  
                   reviewID.setCellValueFactory(new PropertyValueFactory("reviewid"));
                 
                 
                   tablerevieww.refresh();
}

  
   public ObservableList<Series<String, Double>>
         getDummyChartData() {
      ObservableList<Series<String, Double>> data =
         FXCollections.observableArrayList();
      Series<String, Double> as = new Series<>();
      Series<String, Double> bs = new Series<>();
      
    as.setName("A-Series");
     bs.setName("B-Series");
    

      double r = 5.00 ; 

      for (int i = 2000; i < 2017; i += 1) {

       as.getData().add(new XYChart.Data<>
        (Integer.toString(i), r));
         bs.getData().add(new XYChart.Data<>
         (Integer.toString(i), r));
        
      }
      data.addAll(as ,  bs) ;
   // data.addAll(bs);
      return data;
   }
         
         
    @FXML
    private void studentChart(ActionEvent event) {
        
//         //defining the axes
//        final CategoryAxis xAxis = new CategoryAxis(); // we are gonna plot against time
//        final NumberAxis yAxis = new NumberAxis();
//        xAxis.setLabel("Time/s");
//        xAxis.setAnimated(false); // axis animations are removed
//        yAxis.setLabel("Value");
//        yAxis.setAnimated(false); // axis animations are removed
//
//        //creating the line chart with two axis created above
//        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
//        lineChart.setTitle("Realtime JavaFX Charts");
//        lineChart.setAnimated(false); // disable animations
//      //return pie;
//       //defining a series to display data
//        XYChart.Series<String, Number> series = new XYChart.Series<>();
//        series.setName("Data Series");
//
//        // add series to chart
//        lineChart.getData().add(series);
//        
//        // this is used to display time in HH:mm:ss format
//        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
//        
//        // setup a scheduled executor to periodically put data into the chart
//        ScheduledExecutorService scheduledExecutorService;
//        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//
//        // put dummy data onto graph per second
//        scheduledExecutorService.scheduleAtFixedRate(() -> {
//            // get a random integer between 0-10
//            Integer random = ThreadLocalRandom.current().nextInt(10);
//
//            // Update the chart
//            Platform.runLater(() -> {
//                // get current time
//                Date now = new Date();
//                // put random number with current time
//                series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), random));
//            });
//        }, 0, 1, TimeUnit.SECONDS);
//        
//        
//        
//      Stage stagee = new Stage();
//      stagee.setTitle("Stars rating on Training courses");
//      StackPane pane = new StackPane();
//    pane.getChildren().add(lineChart);
//      stagee.setScene(new Scene(pane, 1100, 600));
//      stagee.show();


      CategoryAxis xAxis = new CategoryAxis();
      NumberAxis yAxis = new NumberAxis();
      StackedAreaChart sac = new StackedAreaChart<>(xAxis, yAxis);
      sac.setData(getDummyChartData());
      sac.setTitle("Stacked area chart on random data");
    //  return sac;
      Stage stagee = new Stage();
      stagee.setTitle("Stars rating on Training courses");
      StackPane pane = new StackPane();
    pane.getChildren().add(sac);
      stagee.setScene(new Scene(pane, 1100, 600));
      stagee.show();

   

    }
    
    public  void showcells() {
         System.out.print("eddd") ; 
    //     listt.getItems().removeAll();
//          listt.setItems(okkk.showdreviewsSS());
//         listt.setCellFactory( studentListView -> new StudentListViewCell());
    }
}












