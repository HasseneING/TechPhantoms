package Connection;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.json.JSONException;
import org.json.JSONObject;
import utils.DataSource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClaimsController implements Initializable {
    @FXML
    JFXDatePicker start_date;
    @FXML
    JFXTextField meet_name;
    @FXML
    JFXTextField meet_pass;
    @FXML
    JFXComboBox<String> student;
    @FXML
    JFXComboBox<String> tutor;
    @FXML
    JFXTimePicker start_time;


    ObservableList<String> stu = FXCollections.observableArrayList();

    private final Connection conn= utils.DataSource.getInstance().getCnx();
    private ResultSet result;
    private PreparedStatement pst;
    private Statement st;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map<String,String> list = new HashMap<String,String>();
        try {
            list=getStudent();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (Map.Entry<String, String> c : list.entrySet()) {
            stu.add(c.getValue());
        }
        student.setItems(stu);
    }


    @FXML
    public void btnSubmit() throws Exception {
        System.out.println("ok");
        String link;
        String token =token();
        System.out.println(token);
        link=CreateMeeting(token,meet_name.getText(),"2","","60","",meet_pass.getText());
            String req = "insert into meet (meet_name,meet_link,meet_pass,meet_date,tutorid_id,studentid_id) values (?,?,?,?,?,?)";
        Map<String,String> list = new HashMap<String,String>();
        list=getStudent();
        Optional<Map.Entry<String, String>> found =
                list.entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(student.getSelectionModel().getSelectedItem()))
                .max(Map.Entry.comparingByKey());

        LocalDate cs =start_date.getValue();
        LocalTime ts= start_time.getValue();
        LocalDateTime meet_date = LocalDateTime.of(cs.getYear(), cs.getMonth(), cs.getDayOfMonth(), ts.getHour(), ts.getMinute(), ts.getSecond());
        Timestamp meetDate = Timestamp.valueOf(meet_date);

        pst = conn.prepareStatement(req);
        pst.setString(1,meet_name.getText());
        pst.setString(2,link);
        pst.setString(3,"2");
        pst.setTimestamp(4, meetDate);
        System.out.println(found.get().getKey());
        pst.setInt(5,1);
        pst.setInt(6,Integer.parseInt(found.get().getKey()));
        pst.executeUpdate();

        Notifications.create().title("Done").text("Your complaint has been received").showInformation();


    }

    public Map<String,String> getStudent() throws SQLException {
        Map<String,String> tutorset = new HashMap<String,String>();
        String req = "select id,username from student";
        st = conn.createStatement();
        result = st.executeQuery(req);
        while (result.next()) {
            tutorset.put(result.getString("id"),result.getString("username"));

        }
        return tutorset;
    }

    public static String CreateMeeting(String token, String topic, String type, String start_time, String duration, String timezone, String password) throws Exception {
        String meet_code="";
        String url = "https://api.zoom.us/v2/users/me/meetings";
        URL serverUrl = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setRequestProperty("Authorization", "Bearer " + token);
        String d = "{\"topic\": \"" + topic + "\",\"type\": \"" + type + "\",\"start_time\": \"" + start_time + "\",\"duration\": \"" + duration + "\",\"timezone\": \"" + timezone + "\",\"password\": \"" + password + "\"}";
        BufferedWriter httpRequestBodyWriter = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
        httpRequestBodyWriter.write(d);
        httpRequestBodyWriter.close();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        // int responseCode = urlConnection.getResponseCode();
        StringBuilder results = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            JSONObject obj = new JSONObject(line);
            meet_code = obj.optString("id");
        }
        urlConnection.disconnect();
        return meet_code;
    }

    private static String token() throws SQLException, JSONException {
        Statement ste;
        ResultSet rs;
        Connection conn = DataSource.getInstance().getCnx();
        String token="";
        String req = "select access_token from token WHERE id=1 ";
        ste = conn.createStatement();
        rs = ste.executeQuery(req);
        while (rs.next()) {
            token=rs.getString("access_token");
            JSONObject obj = new JSONObject(token);
            String pageName = obj.getString("access_token");
            token=pageName;
        }
        return token;
    }
}
