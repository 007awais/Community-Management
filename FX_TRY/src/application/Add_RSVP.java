package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.event.ActionEvent;

public class Add_RSVP {

    @FXML
    private TextField residentIdField; // TextField for Resident ID
    @FXML
    private TextField eventIdField;   // TextField for Event ID
    @FXML
    private TextField statusField;    // TextField for Status
    @FXML
    private Button addButton;         // Button for adding the RSVP

    // JDBC URL, username, and password for your database
    private static final String DB_URL = "jdbc:mysql://localhost:3306/community_management";
    private static final String USER = "root";
    private static final String PASSWORD = "admin007";

    @FXML
    public void initialize() {
        // Initialize any necessary setup or listeners (if needed)
    }

    @FXML
    private void handleAddRSVP(ActionEvent event) {
        // Get the input data
        String residentId = residentIdField.getText();
        String eventId = eventIdField.getText();
        String status = statusField.getText();

        // Validate inputs
        if (residentId.isEmpty() || eventId.isEmpty() || status.isEmpty()) {
            System.out.println("Please fill all fields");
            return; // If any field is empty, exit the method
        }

        // Connection to the database
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "INSERT INTO RSVP (resident_id, event_id, status, rsvp_date) VALUES (?, ?, ?, ?)";
            
            // Prepare the statement
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                // Set the values in the query
                pst.setInt(1, Integer.parseInt(residentId));
                pst.setInt(2, Integer.parseInt(eventId));
                pst.setString(3, status);
                pst.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // Current timestamp for RSVP Date
                
                // Execute the insert statement
                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("RSVP added successfully!");
                } else {
                    System.out.println("Failed to add RSVP.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
