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
import java.util.List;

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

    private final ImageLoader imageLoader = new ImageLoader();

    @FXML
    private void initialize() {
        // Initialize table columns
        cameraColumn.setCellValueFactory(new PropertyValueFactory<>("camera"));
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("event"));
    }

    @FXML
    private void onViewLiveFeed() {
        // Show images and hide the table
        toggleLogTableVisibility(false);

        List<Image> images = imageLoader.loadImagesFromFolder("/Users/apple/Downloads/SDA_Images");
        displayImages(images);
    }

    @FXML
    private void onViewLog() {
        // Hide images and show the table
        toggleLogTableVisibility(true);

        // Get log data and populate the table
        ObservableList<LogEntry> logData = LogDataProvider.getLogData();
        logTable.setItems(logData);
    }

    private void toggleLogTableVisibility(boolean showLogTable) {
        logTable.setVisible(showLogTable);
        image1.setVisible(!showLogTable);
        image2.setVisible(!showLogTable);
        image3.setVisible(!showLogTable);
    }

    private void displayImages(List<Image> images) {
        if (images.size() > 0) {
            image1.setImage(images.get(0));
        }
        if (images.size() > 1) {
            image2.setImage(images.get(1));
        }
        if (images.size() > 2) {
            image3.setImage(images.get(2));
        }
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

// Utility class for loading images
class ImageLoader {

    public List<Image> loadImagesFromFolder(String folderPath) {
        File folder = new File(folderPath);
        File[] imageFiles = folder.listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".png"));

        if (imageFiles != null) {
            return FXCollections.observableArrayList(
                new Image(imageFiles[0].toURI().toString()),
                imageFiles.length > 1 ? new Image(imageFiles[1].toURI().toString()) : null,
                imageFiles.length > 2 ? new Image(imageFiles[2].toURI().toString()) : null
            ).filtered(img -> img != null);
        }

        System.out.println("No images found in the folder!");
        return FXCollections.observableArrayList();
    }
}

// Utility class for providing log data
class LogDataProvider {

    public static ObservableList<Survillence.LogEntry> getLogData() {
        return FXCollections.observableArrayList(
            new Survillence.LogEntry("Camera 1", "Person is entering Gate 1"),
            new Survillence.LogEntry("Camera 2", "Car is entering from Gate 4"),
            new Survillence.LogEntry("Camera 3", "Suspicious movement detected near Gate 2"),
            new Survillence.LogEntry("Camera 4", "Delivery van exiting Gate 3"),
            new Survillence.LogEntry("Camera 1", "Person is loitering near the main entrance"),
            new Survillence.LogEntry("Camera 2", "Crowd gathering at Gate 1"),
            new Survillence.LogEntry("Camera 3", "Bike parked near restricted area"),
            new Survillence.LogEntry("Camera 4", "Unauthorized access attempt at Gate 4"),
            new Survillence.LogEntry("Camera 1", "Maintenance staff entering Gate 3"),
            new Survillence.LogEntry("Camera 2", "Security guard patrolling near Gate 2")
        );
    }
}
