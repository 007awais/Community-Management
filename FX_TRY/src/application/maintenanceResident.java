package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class maintenanceResident{

    @FXML
    private TextField residentIdTextField;
    @FXML
    private TextField locationTextField;
    @FXML
    private TextField urgencyTextField;
    @FXML
    private Button submitButton;
    @FXML
    private ImageView professionalImageView;  // You can link this ImageView to a selected image if needed

    // This method will be triggered when the button is pressed
    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        // Get data from the text fields
        String residentId = residentIdTextField.getText();
        String location = locationTextField.getText();
        String urgency = urgencyTextField.getText();

        // Insert data into the database
        insertServiceRequestData(residentId, location, urgency);
    }

    // Method to insert data into the database
    private void insertServiceRequestData(String residentId, String location, String urgency) {
        String url = "jdbc:mysql://localhost:3306/community_management"; // Replace with your database URL
        String username = "root"; // Replace with your database username
        String password = "admin007"; // Replace with your database password

        String sql = "INSERT INTO Maintenance_Request (resident_id, location, urgency) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set the values for the prepared statement
            preparedStatement.setInt(1, Integer.parseInt(residentId));
            preparedStatement.setString(2, location);
            preparedStatement.setString(3, urgency);

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Service request submitted successfully.");
            } else {
                System.out.println("Failed to submit service request.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
