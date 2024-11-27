package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BillAdjustmentController {

    @FXML
    private TextField complaintIdField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField statusField;

    @FXML
    private Button submitButton;

    // Database connection parameters (adjust with your own settings)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/community_management"; // Replace with your DB URL
    private static final String DB_USER = "root"; // Replace with your DB username
    private static final String DB_PASSWORD = "admin007"; // Replace with your DB password

    @FXML
    public void initialize() {
        // Set the action for the submit button
        submitButton.setOnAction(event -> submitComplaint());
    }

    private void submitComplaint() {
        // Read values from the input fields
        String complaintId = complaintIdField.getText();
        String description = descriptionField.getText();
        String status = statusField.getText();

        // Validate input fields
        if (description.isEmpty() || status.isEmpty()) {
            showAlert(AlertType.ERROR, "Validation Error", "Please fill in the description and status.");
            return;
        }

        try {
            // Establish a database connection
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Prepare the SQL query to insert data into the Bill_Adjustment table
            String sql = "INSERT INTO Bill_Adjustment (description, status) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, description);
            pstmt.setString(2, status);

            // Execute the query
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                showAlert(AlertType.INFORMATION, "Success", "Complaint submitted successfully!");
            } else {
                showAlert(AlertType.ERROR, "Failure", "Failed to submit the complaint.");
            }

            // Close the database connection
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Database Error", "An error occurred while submitting the complaint.");
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

