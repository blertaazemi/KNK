package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import org.w3c.dom.Text;
import service.ConnectionUtil;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Pane pane1;

    @FXML
    private Pane pane2;

    @FXML
    private BarChart<String, Integer> barChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     loadData();


}
private void loadData(){
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();

    try {
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();

        //pie chart query
        ResultSet resultSet = statement.executeQuery("SELECT bursa_id, COUNT(*) AS application_count FROM tbl_aplikimet GROUP BY bursa_id");

        //bar chart query
        String sql ="SELECT DATE(date_submitted) AS submission_date, COUNT(*) AS application_count FROM tbl_aplikimet GROUP BY submission_date";

        //pie chart logic:
        while (resultSet.next()) {
            int bursaId = resultSet.getInt("bursa_id");
            int applicationCount = resultSet.getInt("application_count");

            // Assuming the scholarship ID 1 corresponds to "STEM", and ID 2 corresponds to "Universitare"
            String scholarshipName = (bursaId == 1) ? "STEM" : "Universitare";

            list.add(new PieChart.Data(scholarshipName, applicationCount));
        }

        // Create a new PieChart
        PieChart bursatStats = new PieChart(list);
        bursatStats.setTitle("e-BURSA");

         // Set the size of the PieChart to match the size of the pane
        bursatStats.setPrefSize(pane1.getWidth(), pane1.getHeight());


        // Set custom labels for the legend entries
        String stemLabel = "STEM";
        String universitareLabel = "Universitare";
        bursatStats.getData().get(0).setName(stemLabel);
        bursatStats.getData().get(1).setName(universitareLabel);


        bursatStats.setPrefSize(600, 400);

        // Clear the pane and add the PieChart to it
        pane1.getChildren().clear();
        pane1.getChildren().add(bursatStats);

        // bar chart logic:
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        XYChart.Series<String, Integer> chartData = new XYChart.Series<>();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            LocalDate submissionDate = rs.getDate("submission_date").toLocalDate();
            int applicationCount = rs.getInt("application_count");

            String formattedDate = submissionDate.format(formatter);
            chartData.getData().add(new XYChart.Data<>(formattedDate, applicationCount));
        }

        barChart.getData().add(chartData);




        // Close the database resources
        resultSet.close();
        statement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


}




