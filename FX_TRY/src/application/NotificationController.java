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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class NotificationController {

    @FXML
    private TableView<Notification> notificationTable; // Updated to Notification table

    @FXML
    private TableColumn<Notification, Integer> notificationIdColumn; // Notification ID column

    @FXML
    private TableColumn<Notification, String> titleColumn; // Title column

    @FXML
    private TableColumn<Notification, String> timestampColumn; // Timestamp column

    @FXML
    private TableColumn<Notification, String> messageColumn; // Message column

    @FXML
    private TableColumn<Notification, Integer> receiverIdColumn; // Receiver ID column

    @FXML
    private Text statusText;

    @FXML
    private Button update; // Button to navigate to create notification screen

    // Database connection details
    private final String DB_URL = "jdbc:mysql://localhost:3306/community_management";
    private final String USER = "root";
    private final String PASS = "admin007";

    // ObservableList to hold notification data
    private ObservableList<Notification> notificationList = FXCollections.observableArrayList();

    // **Controller**: Responsible for managing the interaction between UI and business logic
    @FXML
    public void initialize() {
        // Initialize the table columns
        notificationIdColumn.setCellValueFactory(new PropertyValueFactory<>("notificationId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        receiverIdColumn.setCellValueFactory(new PropertyValueFactory<>("receiverId"));

        // Load data from the database
        loadNotifications();
    }

    // **Information Expert**: This method knows how to fetch data since it has access to the database
    public void loadNotifications() {
        notificationList.clear(); // Clear existing data
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            String query = "SELECT notification_id, title, timestamp, message, receiver_id FROM Notification";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                // Add each notification to the list
                notificationList.add(new Notification(
                        rs.getInt("notification_id"),
                        rs.getString("title"),
                        rs.getString("timestamp"),
                        rs.getString("message"),
                        rs.getInt("receiver_id")
                ));
            }

            notificationTable.setItems(notificationList); // Populate the table view

            // **Low Coupling**: Status updates are kept separate from business logic
            statusText.setText("Notifications loaded successfully.");

        } catch (Exception e) {
            e.printStackTrace();

            // **Low Coupling**: Error handling is localized here
            statusText.setText("Error loading notifications.");
        }
    }

    // **Controller**: Handles user actions and transitions between UI views
    public void sendNotification() {
        try {
            // Load the new scene's FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateNotification.fxml"));
            Parent newSceneRoot = loader.load();

            // Get the current stage (window) from the button's scene
            Stage currentStage = (Stage) update.getScene().getWindow();

            // Set the new scene on the current stage
            Scene newScene = new Scene(newSceneRoot);
            currentStage.setScene(newScene);

            // **Low Coupling**: Responsibility of setting the title is kept separate
            currentStage.setTitle("Send Notification");

            // Show the new scene
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();

            // **Protected Variations**: Handles failures gracefully and informs the user
            statusText.setText("Failed to load new scene.");
        }
    }
}

