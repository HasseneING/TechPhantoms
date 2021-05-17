package controllers.student;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import model.CustomerCard;
import model.Meet;
import model.SearchTutor;
import model.Tutor;
import utils.DataSource;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author SUWIMA
 */
public class CoursesController implements Initializable {
    @FXML
    private GridPane cardHolder;
    ObservableList<SearchTutor> list = FXCollections.observableArrayList();
    private Connection conn = DataSource.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        onSearch();

    }

    @FXML
    public void onSearch() {
        int count = 0;
        for (int i = 0; i <list.size()/2; i++) {
            for (int j = 0; j < 2; j++) {
                cardHolder.add(list.get(count), j, i);
                count++;
                //System.out.println(i + " " + j);
            }
        }

    }


    public List<Tutor> readAll() throws SQLException {
        List<Tutor> meetList = new ArrayList<>();
        String req = "select * from tutor ";
        ste = conn.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
            Tutor tutor = new Tutor(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("subject"),
                    rs.getString("profile_picture")
            );
            meetList.add(tutor);
        }
        return meetList;
    }

    public void show() throws SQLException {
        List<Tutor> tutorList = new ArrayList<Tutor>();
        tutorList=readAll();
        if (tutorList.size()==0){
            // Action you want to do
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setContentText("You don't have upcoming meetings");
            alert.showAndWait();
        }


        for(int i=0;i<tutorList.size();i++){

            list.add(new SearchTutor(tutorList.get(i).getId(),tutorList.get(i).getFirstName(),tutorList.get(i).getLastName(),tutorList.get(i).getSubject(),tutorList.get(i).getPic()));
        }
        cardHolder.setAlignment(Pos.CENTER);
        cardHolder.setVgap(20.00);
        cardHolder.setHgap(80.00);
        cardHolder.setStyle("-fx-padding:10px;-fx-border-color:transparent");
    }
}
