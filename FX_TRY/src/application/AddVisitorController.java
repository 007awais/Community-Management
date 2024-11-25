package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AddVisitorController {

    @FXML
    private TextField visitorNameField;

    @FXML
    private TextField confirmationStatusField;

    @FXML
    private TextField adminIdField;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/community_management";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin007";

    @FXML
    public void handleAddButtonAction(ActionEvent event) {
        String visitorName = visitorNameField.getText();
        String confirmationStatus = confirmationStatusField.getText();
        String adminId = adminIdField.getText();

        if (visitorName.isEmpty() || confirmationStatus.isEmpty() || adminId.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields must be filled out!");
            return;
        }

        try {
            addVisitorToDatabase(visitorName, confirmationStatus, adminId);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Visitor added successfully!");
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to add visitor. Please try again.");
        }
    }

    private void addVisitorToDatabase(String visitorName, String confirmationStatus, String adminId) throws SQLException {
        String query = "INSERT INTO Visitor (visitor_name, confirmation_status, admin_id, visit_date) VALUES (?, ?, ?, ?)";

        // Set the current timestamp for the visit date
        Timestamp visitDate = new Timestamp(System.currentTimeMillis());

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, visitorName);
            preparedStatement.setString(2, confirmationStatus);
            preparedStatement.setString(3, adminId);
            preparedStatement.setTimestamp(4, visitDate);  // Automatically adds the current timestamp
            preparedStatement.executeUpdate();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        visitorNameField.clear();
        confirmationStatusField.clear();
        adminIdField.clear();
    }
}
