package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import application.Parking;

public class ParkingController {

    @FXML
    private TableView<Parking> parkingTable;

    @FXML
    private TableColumn<Parking, Integer> slotNumberColumn;

    @FXML
    private TableColumn<Parking, String> vehicleIdColumn;

    @FXML
    private TableColumn<Parking, Integer> residentIdColumn;

    @FXML
    private TableColumn<Parking, String> bookingStatusColumn;

    @FXML
    private TableColumn<Parking, String> bookingTimeColumn;

    private ObservableList<Parking> parkingList;

    @FXML
    public void initialize() {
        // Initialize the TableView columns with the corresponding property names
        slotNumberColumn.setCellValueFactory(new PropertyValueFactory<>("parkingSlotNumber"));
        vehicleIdColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        residentIdColumn.setCellValueFactory(new PropertyValueFactory<>("residentId"));
        bookingStatusColumn.setCellValueFactory(new PropertyValueFactory<>("bookingStatus"));
        bookingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("bookingTime"));

        // Fetch data from the database
        loadParkingData();
    }

    private void loadParkingData() {
        parkingList = FXCollections.observableArrayList();

        try {
            // Database credentials (adjust with your own)
            String url = "jdbc:mysql://localhost:3306/community_management"; // Change with your DB URL
            String user = "root"; // Replace with your DB username
            String password = "admin007"; // Replace with your DB password

            // Establish connection to the database
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();

            // SQL query to fetch data from the Parking table
            String sql = "SELECT * FROM Parking";
            ResultSet rs = stmt.executeQuery(sql);

            // Loop through the result set and create Parking objects
            while (rs.next()) {
                int slotNumber = rs.getInt("parking_slot_number");
                String vehicleId = rs.getString("vehicle_id");
                int residentId = rs.getInt("resident_id");
                String bookingStatus = rs.getString("booking_status");
                String bookingTime = rs.getString("booking_time");

                // Add each Parking object to the list
                parkingList.add(new Parking(slotNumber, vehicleId, residentId, bookingStatus, bookingTime));
            }

            // Close the database connection
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the data into the TableView
        parkingTable.setItems(parkingList);
    }
}
