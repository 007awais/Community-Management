package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;

public class Create_Event_Controller {

    // FXML fields
    @FXML private TextField eventNameTextField;
    @FXML private TextField eventDescriptionTextField;
    @FXML private TextField eventManagerTextField;
    @FXML private Button submitEventButton;

    // Database credentials (make sure these are configured correctly)
    private final String DB_URL = "jdbc:mysql://localhost:3306/community_management";
    private final String USER = "root";
    private final String PASS = "admin007";

    // Handle Add Event button click
    @FXML
    public void handleAddEvent() {
        // Get the data from the text fields
        String eventName = eventNameTextField.getText().trim();
        String eventDescription = eventDescriptionTextField.getText().trim();
        String eventManagerStr = eventManagerTextField.getText().trim();

        // Check if the fields are not empty
        if (eventName.isEmpty() || eventDescription.isEmpty() || eventManagerStr.isEmpty()) {
            System.out.println("All fields must be filled out.");
            return;
        }

        try {
            int eventManager = Integer.parseInt(eventManagerStr);
            
            // Call the function to add the new event
            addNewEvent(eventName, eventDescription, eventManager);

        } catch (NumberFormatException e) {
            // Handle invalid event manager ID
            System.out.println("Invalid event manager ID. Please enter a number.");
        }
    }

    // Method to insert the new event into the database
    public void addNewEvent(String eventName, String eventDescription, int eventManager) {
        String query = "INSERT INTO Event (event_name, description, event_manager_id) VALUES (?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the values for the query
            stmt.setString(1, eventName);
            stmt.setString(2, eventDescription);
            stmt.setInt(3, eventManager);

            // Execute the update (insert the new event)
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("New event added successfully.");
            } else {
                System.out.println("Failed to add the new event.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while adding the event.");
        }
    }
}

