package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Controller class for handling Maintenance Requests
public class MaintenanceRequestController {

    @FXML
    private TableView<MaintenanceRequest> tableView;

    @FXML
    private TableColumn<MaintenanceRequest, Integer> requestIdColumn;

    @FXML
    private TableColumn<MaintenanceRequest, Integer> residentIdColumn;

    @FXML
    private TableColumn<MaintenanceRequest, String> statusColumn;

    @FXML
    private TableColumn<MaintenanceRequest, String> locationColumn;

    @FXML
    private TableColumn<MaintenanceRequest, String> urgencyColumn;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/community_management";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin007";

    // Initialize method called after FXML components are loaded
    @FXML
    private void initialize() {
        // GRASP: Controller
        // The controller handles the UI logic and business logic, setting up the table and loading data from the database
        configureTableColumns();
        loadDataFromDatabase();
    }

    /**
     * Configures the TableView columns with appropriate data bindings.
     */
    private void configureTableColumns() {
        // GRASP: Information Expert
        // The logic for configuring the table view columns is handled by the controller as it has knowledge of the structure of the data to display.
        requestIdColumn.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        residentIdColumn.setCellValueFactory(new PropertyValueFactory<>("residentId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        urgencyColumn.setCellValueFactory(new PropertyValueFactory<>("urgency"));
    }

    /**
     * Loads data from the database and populates the TableView.
     * GRASP: Controller, Information Expert
     */
    private void loadDataFromDatabase() {
        // GRASP: Information Expert
        // The method is responsible for fetching data from the database, and thus it is an expert in the task of obtaining and processing data.
        ObservableList<MaintenanceRequest> data = FXCollections.observableArrayList();
        String query = "SELECT request_id, resident_id, status, Location, urgency FROM Maintenance_Request";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int requestId = resultSet.getInt("request_id");
                int residentId = resultSet.getInt("resident_id");
                String status = resultSet.getString("status");
                String location = resultSet.getString("Location");
                String urgency = resultSet.getString("urgency");

                data.add(new MaintenanceRequest(requestId, residentId, status, location, urgency));
            }

            // Set the data in the TableView
            tableView.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading data from database.");
        }
    }

    /**
     * MaintenanceRequest class to represent individual rows in the table.
     * GRASP: Information Expert
     * The MaintenanceRequest class is the expert on its own data, encapsulating the request data.
     */
    public static class MaintenanceRequest {
        private final int requestId;
        private final int residentId;
        private final String status;
        private final String location;
        private final String urgency;

        public MaintenanceRequest(int requestId, int residentId, String status, String location, String urgency) {
            this.requestId = requestId;
            this.residentId = residentId;
            this.status = status;
            this.location = location;
            this.urgency = urgency;
        }

        public int getRequestId() {
            return requestId;
        }

        public int getResidentId() {
            return residentId;
        }

        public String getStatus() {
            return status;
        }

        public String getLocation() {
            return location;
        }

        public String getUrgency() {
            return urgency;
        }
    }
}



// Desing Patterns we Used:

//Model View Controller:
//The Controller (MaintenanceRequestController) handles the interaction between the View (TableView) and the Model (MaintenanceRequest data objects).
// Data Access Object:
//The loadDataFromDatabase method resembles the behavior of a DAO by handling database interactions.
//Observer:
//The ObservableList automatically updates the TableView when data changes. This reflects the Observer pattern, where TableView observes changes in the ObservableList and updates the UI accordingly.
//Factory Method:
//Singelton:
//Driver Manager class is implemented as the singleton class
// AdminController class is aslo implemented from where all the redirection are provided



