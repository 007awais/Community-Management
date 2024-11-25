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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DisputeController {

    @FXML
    private TableView<Dispute> disputeTable;

    @FXML
    private TableColumn<Dispute, Integer> disputeIdColumn;

    @FXML
    private TableColumn<Dispute, String> firstPartyColumn;

    @FXML
    private TableColumn<Dispute, String> secondPartyColumn;

    @FXML
    private TableColumn<Dispute, String> descriptionColumn;

    @FXML
    private TableColumn<Dispute, String> filedDateColumn;

    @FXML
    private Button showDisputesButton;
    
    @FXML
    public Button Resolve;

    // Database connection details
    private final String DB_URL = "jdbc:mysql://localhost:3306/community_management";
    private final String USER = "root";
    private final String PASS = "admin007";

    // ObservableList to hold dispute data
    private ObservableList<Dispute> disputeList = FXCollections.observableArrayList();

    /**
     * GRASP Concept: Controller
     * The DisputeController class acts as a Controller in the MVC architecture,
     * handling user interactions, coordinating between the UI and the data.
     */
    @FXML
    public void initialize() {
        // Initialize the table columns
        /**
         * GRASP Concept: Low Coupling
         * Table column setup is encapsulated here to avoid dependencies
         * on other classes for configuring the table view.
         */
        disputeIdColumn.setCellValueFactory(new PropertyValueFactory<>("disputeId"));
        firstPartyColumn.setCellValueFactory(new PropertyValueFactory<>("firstPartyName"));
        secondPartyColumn.setCellValueFactory(new PropertyValueFactory<>("secondPartyName"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        filedDateColumn.setCellValueFactory(new PropertyValueFactory<>("filedDate"));

        // Automatically load disputes when the scene is displayed
        loadDisputes();

        // Attach button action for reloading disputes
        /**
         * GRASP Concept: Polymorphism
         * The button's action is dynamically set here, allowing the same controller
         * to handle different actions depending on the button clicked.
         */
        if (showDisputesButton != null) {
            showDisputesButton.setOnAction(event -> loadDisputes());
        }
    }

    /**
     * GRASP Concept: Information Expert
     * The loadDisputes method is responsible for retrieving data from the database,
     * as it has the knowledge of how to query and populate dispute data.
     */
    private void loadDisputes() {
        disputeList.clear(); // Clear existing data
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            String query = "SELECT dispute_id, first_party_name, second_party_name, description, filed_date FROM Dispute";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                // Add each dispute to the list
                /**
                 * GRASP Concept: High Cohesion
                 * The Dispute class focuses on encapsulating dispute-related data, 
                 * keeping this data model cohesive and reusable.
                 */
                disputeList.add(new Dispute(
                        rs.getInt("dispute_id"),
                        rs.getString("first_party_name"),
                        rs.getString("second_party_name"),
                        rs.getString("description"),
                        rs.getString("filed_date")
                ));
            }

            // Populate the table view
            disputeTable.setItems(disputeList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * GRASP Concept: Controller
     * The Resolve_Dispute method manages user interaction to transition to a new scene,
     * allowing dispute resolution while maintaining UI separation.
     */
    public void Resolve_Dispute() {
        try {
            // Load the new scene's FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Resolve_Dispute.fxml"));
            Parent newSceneRoot = loader.load();

            // Get the current stage (window) from the button's scene
            Stage currentStage = (Stage) Resolve.getScene().getWindow();

            // Set the new scene on the current stage
            Scene newScene = new Scene(newSceneRoot);
            currentStage.setScene(newScene);

            // Optionally, set the title of the new stage (window)
            currentStage.setTitle("Resolve Dispute");

            // Show the new scene
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load new scene.");
        }
    }
}

