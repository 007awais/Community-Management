package application;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PayForEventController {

    @FXML
    private TextField eventIdTextField;

    @FXML
    private TextField residentIdTextField;

    @FXML
    private TextField transactionIdTextField;

    @FXML
    private Button submitButton;

    // Method that gets called when the Submit button is clicked
    @FXML
    private void handleSubmitButtonAction() {
        // Retrieve data from the text fields
        String eventId = eventIdTextField.getText();
        String residentId = residentIdTextField.getText();
        String transactionId = transactionIdTextField.getText();

        // Validate input
        if (eventId.isEmpty() || residentId.isEmpty() || transactionId.isEmpty()) {
            showAlert("Input Error", "Please fill all the fields.");
            return;
        }

        // Database connection and insertion logic
        try {
            // Database credentials (adjust with your own)
            String url = "jdbc:mysql://localhost:3306/community_management";  // Change the database URL
            String user = "root"; // Replace with your DB username
            String password = "admin007"; // Replace with your DB password

            // Establish connection to the database
            Connection conn = DriverManager.getConnection(url, user, password);

            // SQL Insert query
            String sql = "INSERT INTO Event_Fee (event_id, resident_id, amount) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Set parameters
            pstmt.setInt(1, Integer.parseInt(eventId));
            pstmt.setInt(2, Integer.parseInt(residentId));
            pstmt.setBigDecimal(3, new java.math.BigDecimal(transactionId));  // Assuming the transaction ID is the amount here, adjust as needed.

            // Execute the insert
            int rowsAffected = pstmt.executeUpdate();

            // Show success or error message based on the result
            if (rowsAffected > 0) {
                showAlert("Success", "Event fee recorded successfully.");
            } else {
                showAlert("Error", "Failed to record event fee.");
            }

            // Close the connection
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "An error occurred while connecting to the database.");
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid numbers for Event ID and Resident ID.");
        }
    }

    // Utility method to show alert messages
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
