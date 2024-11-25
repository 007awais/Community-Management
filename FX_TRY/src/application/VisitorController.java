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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class VisitorController {

    @FXML
    private TextField visitorIdField; // For entering Visitor ID

    @FXML
    private Button searchButton; // Button for triggering search

    @FXML
    private Button Addvisitor; // Button for adding a visitor

    @FXML
    private TableView<Visitor> visitorTable; // Table to display visitor data

    @FXML
    private TableColumn<Visitor, Integer> visitorIdColumn; // Column for Visitor ID

    @FXML
    private TableColumn<Visitor, String> visitorNameColumn; // Column for Visitor Name

    @FXML
    private TableColumn<Visitor, String> visitDateColumn; // Column for Visit Date

    @FXML
    private TableColumn<Visitor, String> confirmationStatusColumn; // Column for Confirmation Status

    @FXML
    private TableColumn<Visitor, Integer> adminIdColumn; // Column for Admin ID

    // Database connection details
    private final String DB_URL = "jdbc:mysql://localhost:3306/community_management";
    private final String USER = "root";
    private final String PASS = "admin007";

    // ObservableList to store data for the TableView
    private ObservableList<Visitor> visitorList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up TableView columns
        visitorIdColumn.setCellValueFactory(new PropertyValueFactory<>("visitorId"));
        visitorNameColumn.setCellValueFactory(new PropertyValueFactory<>("visitorName"));
        visitDateColumn.setCellValueFactory(new PropertyValueFactory<>("visitDate"));
        confirmationStatusColumn.setCellValueFactory(new PropertyValueFactory<>("confirmationStatus"));
        adminIdColumn.setCellValueFactory(new PropertyValueFactory<>("adminId"));

        // Attach event handler for the Search button
        searchButton.setOnAction(event -> fetchVisitorDetails());
    }

    /**
     * Fetches visitor details from the database and populates the TableView.
     * GRASP: Controller - Handles interaction between the UI and business logic.
     */
    private void fetchVisitorDetails() {
        String visitorIdInput = visitorIdField.getText();
        if (visitorIdInput.isEmpty()) {
            System.out.println("Please enter a Visitor ID.");
            return;
        }

        visitorList.clear(); // Clear existing data

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            String query = "SELECT * FROM Visitor WHERE visitor_id = " + visitorIdInput;
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int visitorId = rs.getInt("visitor_id");
                String visitorName = rs.getString("visitor_name");
                String visitDate = rs.getString("visit_date");
                String confirmationStatus = rs.getString("confirmation_status");
                int adminId = rs.getInt("admin_id");

                // Add data to the ObservableList
                visitorList.add(new Visitor(visitorId, visitorName, visitDate, confirmationStatus, adminId));
            }

            // Update the TableView with fetched data
            visitorTable.setItems(visitorList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the AddVisitor view for adding a new visitor.
     * GRASP: Creator - This method creates a new scene for adding a visitor.
     */
    public void AddVisitor() {
        try {
            // Load the new scene's FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddVisitor.fxml"));
            Parent newSceneRoot = loader.load();

            // Get the current stage (window) from the button's scene
            Stage currentStage = (Stage) Addvisitor.getScene().getWindow();

            // Set the new scene on the current stage
            Scene newScene = new Scene(newSceneRoot);
            currentStage.setScene(newScene);

            // Optionally, set the title of the new stage (window)
            currentStage.setTitle("Add a Visitor");

            // Show the new scene
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load new scene.");
        }
    }
}

