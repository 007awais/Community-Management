package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class Survillence {

    @FXML
    private ImageView image1, image2, image3;

    @FXML
    private Button feed;

    @FXML
    private Button viewLog;

    @FXML
    private TableView<LogEntry> logTable;

    @FXML
    private TableColumn<LogEntry, String> cameraColumn;

    @FXML
    private TableColumn<LogEntry, String> eventColumn;

    @FXML
    private void initialize() {
        // Initialize table columns
        cameraColumn.setCellValueFactory(new PropertyValueFactory<>("camera"));
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("event"));
    }

    @FXML
    private void onViewLiveFeed() {
        // Show images and hide the table
        logTable.setVisible(false);
        image1.setVisible(true);
        image2.setVisible(true);
        image3.setVisible(true);
      
        File folder = new File("/Users/apple/Downloads/SDA_Images");

        File[] imageFiles = folder.listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".png"));
        if (imageFiles != null && imageFiles.length > 0) {
            if (imageFiles.length > 0) {
                image1.setImage(new Image(imageFiles[0].toURI().toString()));
                image1.setVisible(true);
            }
            if (imageFiles.length > 1) {
                image2.setImage(new Image(imageFiles[1].toURI().toString()));
                image2.setVisible(true);
            }
            if (imageFiles.length > 2) {
                image3.setImage(new Image(imageFiles[2].toURI().toString()));
                image3.setVisible(true);
            }
        } else {
            System.out.println("No images found in the folder!");
        }

    }

    @FXML
    private void onViewLog() {
        // Hide images and show the table
        image1.setVisible(false);
        image2.setVisible(false);
        image3.setVisible(false);
       

        // Create dummy data for the log table
        ObservableList<LogEntry> logData = FXCollections.observableArrayList(
            new LogEntry("Camera 1", "Person is entering Gate 1"),
            new LogEntry("Camera 2", "Car is entering from Gate 4"),
            new LogEntry("Camera 3", "Suspicious movement detected near Gate 2"),
            new LogEntry("Camera 4", "Delivery van exiting Gate 3"),
            new LogEntry("Camera 1", "Person is loitering near the main entrance"),
            new LogEntry("Camera 2", "Crowd gathering at Gate 1"),
            new LogEntry("Camera 3", "Bike parked near restricted area"),
            new LogEntry("Camera 4", "Unauthorized access attempt at Gate 4"),
            new LogEntry("Camera 1", "Maintenance staff entering Gate 3"),
            new LogEntry("Camera 2", "Security guard patrolling near Gate 2")
        );

        // Populate the table and make it visible
        logTable.setItems(logData);
        logTable.setVisible(true);
    }

    // Class for log entries
    public static class LogEntry {
        private final String camera;
        private final String event;

        public LogEntry(String camera, String event) {
            this.camera = camera;
            this.event = event;
        }

        public String getCamera() {
            return camera;
        }

        public String getEvent() {
            return event;
        }
    }
}