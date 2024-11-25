package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ResolveDispute {

    @FXML
    private TextField firstPartyIdField;

    @FXML
    private TextField secondPartyIdField;

    @FXML
    private TextField guiltyPartyIdField;

    @FXML
    private TextField removeDisputeIdField;

    @FXML
    private RadioButton activeDisputeRadioButton;

    @FXML
    private Button removeButton;

    // Database connection details
    private final String DB_URL = "jdbc:mysql://localhost:3306/community_management";
    private final String USER = "root";
    private final String PASS = "admin007";

    @FXML
    public void initialize() {
        // Attach listeners to the UI elements
        removeButton.setOnAction(event -> removeDispute());
        activeDisputeRadioButton.setOnAction(event -> toggleActiveDisputes());
    }

    /**
     * Method to remove a dispute based on the provided ID.
     */
    private void removeDispute() {
        String disputeId = removeDisputeIdField.getText();
        if (disputeId.isEmpty()) {
            System.out.println("Please enter a Dispute ID to remove.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            String query = "DELETE FROM Dispute WHERE dispute_id = " + disputeId;
            int rowsAffected = stmt.executeUpdate(query);

            if (rowsAffected > 0) {
                System.out.println("Dispute with ID " + disputeId + " removed successfully.");
            } else {
                System.out.println("No dispute found with ID " + disputeId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to toggle active disputes based on the radio button selection.
     */
    private void toggleActiveDisputes() {
        if (activeDisputeRadioButton.isSelected()) {
            System.out.println("Displaying active disputes...");
            // Logic to filter and display active disputes
            // This can be implemented to fetch only active disputes from the database.
        } else {
            System.out.println("Inactive disputes mode selected.");
        }
    }
}
