package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdatePaymentController {

    @FXML
    private TextField residentIdField; // Field for Resident ID input

    @FXML
    private TextField statusField; // Field for new Status input

    @FXML
    private Button saveButton; // Button for updating the record

    private final String DB_URL = "jdbc:mysql://localhost:3306/community_management";
    private final String USER = "root";
    private final String PASS = "admin007";

    @FXML
    public void handleSave() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE PaymentRecord SET status = ? WHERE resident_id = ?")) {

            // Get inputs
            int residentId = Integer.parseInt(residentIdField.getText().trim());
            String newStatus = statusField.getText().trim();

            // Set query parameters
            stmt.setString(1, newStatus);
            stmt.setInt(2, residentId);

            // Execute update query
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Status updated successfully for Resident ID: " + residentId);
            } else {
                System.out.println("No record found for Resident ID: " + residentId);
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid Resident ID.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to update the status. Try again.");
        }
    }
}
