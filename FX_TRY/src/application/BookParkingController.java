package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
            System.out.println("Validation Error: Please fill in all fields.");
            return;
        }

        System.out.println("Time Duration: " + timeDuration);  // Debugging line

        try {
            // Parse or compute booking_time
            java.sql.Timestamp bookingTime;
            if (timeDuration.matches("\\d+")) { // Check if the timeDuration is a number
                long currentTimeMillis = System.currentTimeMillis();
                long durationMillis = Long.parseLong(timeDuration) * 3600 * 1000; // Convert hours to milliseconds
                bookingTime = new java.sql.Timestamp(currentTimeMillis + durationMillis);
            } else {
                System.out.println("Invalid Time Format: Time duration should be in numeric hours.");
                return;
            }

            // Establish a database connection
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Prepare the SQL query to insert data into the Parking table
            String sql = "INSERT INTO Parking (vehicle_id, resident_id, booking_status, booking_time) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vehicleId);
            pstmt.setString(2, residentId);
            pstmt.setString(3, bookingStatus);
            pstmt.setTimestamp(4, bookingTime); // Correct datatype

            // Execute the query
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Success: Parking slot booked successfully!");
            } else {
                System.out.println("Failure: Failed to book the parking slot.");
            }

            // Close the database connection
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database Error: An error occurred while booking the parking slot.");
        }
    }

}

