
package controllers.Student;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author USER
 */
public class TableController {

    @FXML
    private TableView<ModelTable> table;
    @FXML
    private TableColumn<ModelTable, Integer> col_id;
    @FXML
    private TableColumn<ModelTable, String> col_name;
    @FXML
    private TableColumn<ModelTable, String> col_last;
    @FXML
    private TableColumn<ModelTable, String> col_email;
    @FXML
    private TableColumn<ModelTable, Integer> col_tutor;
    @FXML
    private TableColumn<ModelTable, String> col_reasons;
    @FXML
    private TableColumn<ModelTable, String> col_other;
    
    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    @FXML
    private TextField filterField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        try {
            Connection con = DBConnector.getConnection();
            
            ResultSet rs = con.createStatement().executeQuery("select * from rec");
            
            while (rs.next()){
                oblist.add(new ModelTable(rs.getInt("id"), rs.getString("name"), rs.getString("lastName"), rs.getString("email"),
                rs.getInt("TutorID"), rs.getString("reasons"), rs.getString("other_reasons")));
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_last.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            col_tutor.setCellValueFactory(new PropertyValueFactory<>("TutorID"));
            col_reasons.setCellValueFactory(new PropertyValueFactory<>("reasons"));
            col_other.setCellValueFactory(new PropertyValueFactory<>("other_reasons"));
            
            table.setItems(oblist);
        

            FilteredList<ModelTable> filteredData = new FilteredList<>(oblist, b -> true);
            
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(e -> {
                    
                    //if text is empty, display all reclamations
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if(e.getName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    }
                    
                    else if(e.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    }
                    
                    else if(e.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    }
                    
                    else if(String.valueOf(e.TutorID).indexOf(lowerCaseFilter) != -1){
                        return true;
                    }
                    
                    else if(e.getReasons().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    }
                    
                    else if(e.getOther_reasons().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    }
                    
                else
                        return false; //does not match
                
                });
          
            });
            
            SortedList<ModelTable> sortedData = new SortedList<>(filteredData);
            
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            
            table.setItems(sortedData);
            
    }*/
    
}
