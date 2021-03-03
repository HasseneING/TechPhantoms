/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entite.dateEmploi;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.scheduleService;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class AvailabilityScheduleController implements Initializable {

    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();

    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private ComboBox<String> startHourCombo;
    @FXML
    private ComboBox<String> startMinCombo;
    @FXML
    private ComboBox<String> endHourCombo;
    @FXML
    private ComboBox<String> endMinCombo;
    @FXML
    private TableView<dateEmploi> scheduleView;
    @FXML
    private TableColumn<dateEmploi, Integer> dateIDCol;
    @FXML
    private TableColumn<dateEmploi, Timestamp> startTimeCol;
    @FXML
    private TableColumn<dateEmploi, Timestamp> endTimeCol;
    @FXML
    private TableColumn<dateEmploi, Integer> availableCol;
   

    @FXML
    public void btnAddAvailableDateClicked() {
        System.out.println(startDatePicker.getValue() + " " + endDatePicker.getValue() + " " + startHourCombo.getValue() + " " + startMinCombo.getValue());

        /*Date to 
    Timestamp */
        LocalDate startDate = startDatePicker.getValue();
        String startHour = startHourCombo.getValue();
        String startMin = startMinCombo.getValue();
        LocalDate endDate = endDatePicker.getValue();
        String endHour = endHourCombo.getValue();
        String endMin = endMinCombo.getValue();
        LocalDateTime ldtStart = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), Integer.valueOf(startHour), Integer.valueOf(startMin));
        LocalDateTime ldtEnd = LocalDateTime.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(), Integer.valueOf(endHour), Integer.valueOf(endMin));
        Timestamp tsS = Timestamp.valueOf(ldtStart);
        Timestamp tsE = Timestamp.valueOf(ldtEnd);

        scheduleService addDate = new scheduleService();
        addDate.ajouterDate(new dateEmploi(tsS, tsE));
        System.out.println("start:  " + ldtStart + " end: " + ldtEnd);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hours.addAll("09", "10", "11", "12", "13", "14", "15", "16", "17");
        minutes.addAll("00", "15", "30", "45");
        startHourCombo.setItems(hours);
        startHourCombo.setValue("00");
        startMinCombo.setItems(minutes);
        startMinCombo.setValue("00");
        endHourCombo.setItems(hours);
        endHourCombo.setValue("00");
        endMinCombo.setItems(minutes);
        endMinCombo.setValue("00");
        scheduleService sS = new scheduleService();
        
        scheduleView.setItems(sS.getDatesForSchedule(1));
        scheduleView.refresh();
    }

}
