package controllers.teacher;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import model.CustomerCard;
import model.Meet;
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
public class MeetingController implements Initializable {
    @FXML
    private GridPane cardHolder;
    ObservableList<CustomerCard> list = FXCollections.observableArrayList();
    private Connection conn = DataSource.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            refresh();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    public void onSearch() {
        int count = 0;
        if(list.size()!=0) {

            for (int i = 0; i < list.size() / 2; i++) {
                for (int j = 0; j < 2; j++) {
                    cardHolder.add(list.get(count), j, i);
                    count++;
                    System.out.println("XXXXXXXX");
                }
            }
        }
        else {
            System.out.println("AAAAAAAA");
        }
    }


    public List<Meet> readAll() throws SQLException {
        List<Meet> meetList = new ArrayList<>();
        String req = "select username,meet_id,tutorid_id,studentid_id,meet_link,meet_date,meet_pass,meet_name from meet INNER JOIN student ON student.id = meet.studentid_id where tutorid_id=1";
        ste = conn.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
            Meet meets = new Meet(
                    rs.getInt("meet_id"),
                    rs.getString("meet_name"),
                    rs.getString("meet_pass"),
                    rs.getString("meet_link"),
                    rs.getInt("tutorid_id"),
                    rs.getString("username"),
                    rs.getDate("meet_date"));
            meetList.add(meets);
        }
        return meetList;
    }

    public void show() throws SQLException {
        List<Meet> meetList = new ArrayList<Meet>();
        meetList=readAll();
        if (meetList.size()==0){
            // Action you want to do
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setContentText("You don't have upcoming meetings");
            alert.showAndWait();
        }


        for(int i=0;i<meetList.size();i++){
            String date =meetList.get(i).getMeet_date().toString();

            list.add(new CustomerCard(meetList.get(i).getMeet_id(),meetList.get(i).getMeet_name(),meetList.get(i).getMeet_link(),meetList.get(i).getMeet_pass(),date));
        }
        cardHolder.setAlignment(Pos.CENTER);
        cardHolder.setVgap(20.00);
        cardHolder.setHgap(80.00);
        cardHolder.setStyle("-fx-padding:10px;-fx-border-color:transparent");
    }


    public void refresh() throws SQLException {
        show();
        onSearch();
    }

    @FXML
    void abc(ActionEvent event)  {
        try {
            show();
            cardHolder.getChildren().clear();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        onSearch();
    }

}
