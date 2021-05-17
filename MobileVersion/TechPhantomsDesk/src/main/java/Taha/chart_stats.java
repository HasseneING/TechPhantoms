/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Taha;

/**
 *
 * @author t4h4
 */
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class chart_stats extends Application {
    
 

   @Override
   public void start(Stage stage) throws Exception {
      stage.setTitle("JavaFX Chart Demo");
      StackPane pane = new StackPane();
      pane.getChildren().add(createPieChart());
      stage.setScene(new Scene(pane, 400, 200));
      stage.show();
   }

   public PieChart createPieChart() {
      PieChart pie = new PieChart();
      ObservableList<PieChart.Data> data =
         FXCollections.observableArrayList();
      data.addAll(new PieChart.Data("Asia", 30.0),
         new PieChart.Data("Africa", 20.3),
         new PieChart.Data("North America", 16.3),
         new PieChart.Data("South America", 12.0),
         new PieChart.Data("Antartica", 8.9),
         new PieChart.Data("Europe", 6.7),
         new PieChart.Data("Australia", 5.2));

      pie.setData(data);
      pie.setTitle("The Continents: Land Area");
      return pie;
   }
}
    

