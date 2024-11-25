package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import application.EventRecord;

public class EventController {

    @FXML
    private TableView<EventRecord> eventTable;

    @FXML
    private TableColumn<EventRecord, Integer> eventIdColumn;

    @FXML
    private TableColumn<EventRecord, String> eventNameColumn;

    @FXML
    private TableColumn<EventRecord, String> eventDescriptionColumn;

    @FXML
    private TableColumn<EventRecord, Integer> eventManagerColumn;

    @FXML
    private Text statusText;

    @FXML
    private TextField eventIdTextField;

    @FXML
    private Button searchButton;

    @FXML 
    private Button AddEvent;

    private ObservableList<EventRecord> eventData = FXCollections.observableArrayList();

    // Database credentials
    private final String DB_URL = "jdbc:mysql://localhost:3306/community_management";
    private final String USER = "root";
    private final String PASS = "admin007";

    /**
     * GRASP Concept: Controller
     * This class (EventController) acts as the Controller, handling user input 
     * (e.g., button clicks) and coordinating interactions between the UI and the model/data layer.
     */
    @FXML
    public void initialize() {
        System.out.println("Event Controller initialized.");

        /**
         * GRASP Concept: Low Coupling
         * Assigns responsibilities such that the event table and columns are only configured here,
         * reducing dependencies between classes.
         */
        eventIdColumn.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        eventDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("eventDescription"));
        eventManagerColumn.setCellValueFactory(new PropertyValueFactory<>("eventManager"));

        eventTable.setItems(eventData);
    }

    /**
     * GRASP Concept: Information Expert
     * Responsibility for querying and validating the event data is assigned to this controller,
     * as it knows how to interact with the database to retrieve event records.
     */
    @FXML
    public void handleSearchEvent() {
        String eventIdInput = eventIdTextField.getText().trim();
        if (!eventIdInput.isEmpty()) {
            try {
                int eventId = Integer.parseInt(eventIdInput);
                loadEventData(eventId); // Delegate work to the loadEventData method
            } catch (NumberFormatException e) {
                statusText.setText("Invalid Event ID. Please enter a valid number.");
            }
        } else {
            statusText.setText("Please enter an Event ID.");
        }
    }

    /**
     * GRASP Concept: Information Expert
     * The `loadEventData` method is responsible for retrieving data from the database,
     * as it has the knowledge of how to query the database.
     */
    public void loadEventData(int eventId) {
        eventData.clear(); // Clear existing data

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT event_id, event_name, description, event_manager_id FROM Event WHERE event_id = ?")) {

            stmt.setInt(1, eventId); // Set event_id parameter in query
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                do {
                    int eventID = rs.getInt("event_id");
                    String eventName = rs.getString("event_name");
                    String eventDescription = rs.getString("description");
                    int eventManager = rs.getInt("event_manager_id");

                    /**
                     * GRASP Concept: High Cohesion
                     * The EventRecord class focuses on encapsulating event data, 
                     * keeping the data model cohesive and reusable.
                     */
                    eventData.add(new EventRecord(eventID, eventName, eventDescription, eventManager));
                } while (rs.next());

                statusText.setText("Event data loaded successfully.");
            } else {
                statusText.setText("No records found for Event ID: " + eventId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            statusText.setText("Failed to load event data. Please try again.");
        }
    }

    /**
     * GRASP Concept: Controller
     * The AddEvent method handles user interaction (button click) and transitions
     * to a new screen for adding an event, managing scene changes for the UI.
     */
    public void AddEvent() {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Create_Event.fxml"));
            loader.setController(new Create_Event_Controller());  // Manually set the controller
            loader.load();
            System.out.println("FXML and Controller Loaded Successfully.");

            // Create a new scene with the loaded FXML content
            Scene createEventScene = new Scene(loader.getRoot());

            // Get the current stage (the stage of the current scene)
            Stage currentStage = (Stage) AddEvent.getScene().getWindow(); 

            // Set the new scene
            currentStage.setScene(createEventScene);

            // Show the new scene
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


