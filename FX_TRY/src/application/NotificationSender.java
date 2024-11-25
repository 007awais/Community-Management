package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class NotificationSender {

    @FXML
    private TextField titleField;

    @FXML
    private TextField messageField;

    @FXML
    private TextField receiverIdField;

  
    
    
    @FXML 
    private Button send;

    // Database connection details
    private final String DB_URL = "jdbc:mysql://localhost:3306/community_management";
    private final String USER = "root";
    private final String PASS = "admin007";

    @FXML
    public void sendNotification() {
        // Get the values entered by the user
        String title = titleField.getText();
        String message = messageField.getText();
        String receiverIdInput = receiverIdField.getText();

        // Validate the input fields
        if (title.isEmpty() || message.isEmpty() || receiverIdInput.isEmpty()) {
           System.out.println("All fields must be filled!");
            return;
        }

        try {
            // Parse the receiverId
            int receiverId = Integer.parseInt(receiverIdInput);

            // Get the current timestamp
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Connect to the database
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                // Insert notification into the database
                String query = "INSERT INTO Notification (title, timestamp, message, receiver_id) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, title);
                    stmt.setTimestamp(2, timestamp);
                    stmt.setString(3, message);
                    stmt.setInt(4, receiverId);

                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                    	System.out.println("Notification sent successfully.");
                    } else {
                    	System.out.println("Failed to send notification.");
                    }
                }
            }

        } catch (NumberFormatException e) {
        	System.out.println("Invalid Receiver ID. Please enter a valid number.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error sending notification.");
        }
    }
}
