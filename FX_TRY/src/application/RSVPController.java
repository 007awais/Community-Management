

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
import java.sql.*;

public class RSVPController {

    @FXML
    private Text titleText;

    @FXML
    private TableView<RSVP> rsvpTable;

    @FXML
    private TableColumn<RSVP, Integer> rsvpIdColumn;

    @FXML
    private TableColumn<RSVP, Integer> residentIdColumn;

    @FXML
    private TableColumn<RSVP, Integer> eventIdColumn;

    @FXML
    private TableColumn<RSVP, String> statusColumn;

    @FXML
    private TableColumn<RSVP, Date> rsvpDateColumn;

    @FXML
    private Button showRSVPButton;
    
    @FXML
    private Button Add;

    // Connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/community_management";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin007";

    @FXML
    public void initialize() {
        // Set up columns
        rsvpIdColumn.setCellValueFactory(new PropertyValueFactory<>("rsvpId"));
        residentIdColumn.setCellValueFactory(new PropertyValueFactory<>("residentId"));
        eventIdColumn.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        rsvpDateColumn.setCellValueFactory(new PropertyValueFactory<>("rsvpDate"));

        // Set up button action
        showRSVPButton.setOnAction(e -> showRSVPData());
    }

    private void showRSVPData() {
        // Fetch data from database and update the TableView
        ObservableList<RSVP> rsvpList = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM RSVP";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    int rsvpId = rs.getInt("rsvp_id");
                    int residentId = rs.getInt("resident_id");
                    int eventId = rs.getInt("event_id");
                    String status = rs.getString("status");
                    Date rsvpDate = rs.getDate("rsvp_date");

                    // Create RSVP object and add it to the list
                    rsvpList.add(new RSVP(rsvpId, residentId, eventId, status, rsvpDate));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Update the table with fetched data
        rsvpTable.setItems(rsvpList);
    }
    
    
    
    
    @FXML
    public void Gotoupdate() {
        try {
            // Load the new scene's FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_RSVP.fxml"));
            Parent newSceneRoot = loader.load();

            // Get the current stage (window) from the button's scene
            Stage currentStage = (Stage) Add.getScene().getWindow();

            // Set the new scene on the current stage
            Scene newScene = new Scene(newSceneRoot);
            currentStage.setScene(newScene);

            // Optionally, set the title of the new stage (window)
            currentStage.setTitle("Add RSVP");

            // Show the new scene
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the Update Payment screen.");
        }
    }
}
