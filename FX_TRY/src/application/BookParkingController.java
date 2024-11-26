package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BookParkingController {

    @FXML
    private TextField vehicleIdField;

    @FXML
    private TextField residentIdField;

    @FXML
    private TextField bookingStatusField;

    @FXML
    private TextField timeDurationField;

    @FXML
    private Button bookButton;

    // Database connection parameters (adjust with your own settings)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/community_management"; // Replace with your DB URL
    private static final String DB_USER = "root"; // Replace with your DB username
    private static final String DB_PASSWORD = "admin007"; // Replace with your DB password

    @FXML
    public void initialize() {
        // Set the action for the book button
        bookButton.setOnAction(event -> bookParkingSlot());
    }

    private void bookParkingSlot() {
        // Read values from the input fields
        String vehicleId = vehicleIdField.getText();
        String residentId = residentIdField.getText();
        String bookingStatus = bookingStatusField.getText();
        String timeDuration = timeDurationField.getText();

        // Validate input fields
        if (vehicleId.isEmpty() || residentId.isEmpty() || bookingStatus.isEmpty() || timeDuration.isEmpty()) {
            showAlert(AlertType.ERROR, "Validation Error", "Please fill in all fields.");
            return;
        }

        try {
            // Establish a database connection
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Prepare the SQL query to insert data into the Parking table
            String sql = "INSERT INTO Parking (vehicle_id, resident_id, booking_status, booking_time) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vehicleId);
            pstmt.setString(2, residentId);
            pstmt.setString(3, bookingStatus);
            pstmt.setString(4, timeDuration);

            // Execute the query
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                showAlert(AlertType.INFORMATION, "Success", "Parking slot booked successfully!");
            } else {
                showAlert(AlertType.ERROR, "Failure", "Failed to book the parking slot.");
            }

            // Close the database connection
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Database Error", "An error occurred while booking the parking slot.");
        }
    }

    // Helper method to display alerts
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
