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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Main Controller Class
public class TrackPayment {

    @FXML
    private TableView<PaymentRecord> paymentTable;

    @FXML
    private TableColumn<PaymentRecord, Integer> paymentIdColumn;

    @FXML
    private TableColumn<PaymentRecord, String> statusColumn;

    @FXML
    private TableColumn<PaymentRecord, Double> amountColumn;

    @FXML
    private TableColumn<PaymentRecord, Integer> residentIdColumn;

    @FXML
    private Button searchButton;

    @FXML
    private Text statusText;

    @FXML
    private TextField residentIdTextField;

    @FXML
    private Button UpdateData; // Button for navigation

    private ObservableList<PaymentRecord> paymentData = FXCollections.observableArrayList();
    private PaymentService paymentService = new PaymentService(); // Service object

    // Initialize method
    @FXML
    public void initialize() {
        System.out.println("Track Payment Controller initialized.");

        // Set up table columns
        paymentIdColumn.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        residentIdColumn.setCellValueFactory(new PropertyValueFactory<>("residentId"));

        paymentTable.setItems(paymentData);
    }

    // Search button click handler
    @FXML
    public void handleSearchPayment() {
        String residentIdInput = residentIdTextField.getText();
        try {
            int residentId = Integer.parseInt(residentIdInput);
            paymentData.setAll(paymentService.getPaymentRecordsByResidentId(residentId));

            if (paymentData.isEmpty()) {
                statusText.setText("No records found for Resident ID: " + residentId);
            } else {
                statusText.setText("Data loaded successfully.");
            }
        } catch (NumberFormatException e) {
            statusText.setText("Invalid Resident ID. Please enter a number.");
        }
    }

    // Navigation function to go to the Update Payment screen
    @FXML
    public void Gotoupdate() {
        try {
            // Load the new scene's FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Update_Payment.fxml"));
            Parent newSceneRoot = loader.load();

            // Get the current stage (window) from the button's scene
            Stage currentStage = (Stage) UpdateData.getScene().getWindow();

            // Set the new scene on the current stage
            Scene newScene = new Scene(newSceneRoot);
            currentStage.setScene(newScene);

            // Optionally, set the title of the new stage (window)
            currentStage.setTitle("Update Payment Status");

            // Show the new scene
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            statusText.setText("Failed to load the Update Payment screen.");
        }
    }

    // Nested Class: PaymentService (handles database logic)
    private class PaymentService {
        private final String DB_URL = "jdbc:mysql://localhost:3306/community_management";
        private final String USER = "root";
        private final String PASS = "admin007";

        public ObservableList<PaymentRecord> getPaymentRecordsByResidentId(int residentId) {
            ObservableList<PaymentRecord> paymentData = FXCollections.observableArrayList();

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 PreparedStatement stmt = conn.prepareStatement("SELECT payment_id, status, amount, resident_id FROM PaymentRecord WHERE resident_id = ?")) {

                stmt.setInt(1, residentId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int paymentId = rs.getInt("payment_id");
                    String status = rs.getString("status");
                    double amount = rs.getDouble("amount");
                    int dbResidentId = rs.getInt("resident_id");

                    paymentData.add(new PaymentRecord(paymentId, status, amount, dbResidentId));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return paymentData;
        }
    }

    // Nested Class: PaymentRecord (Data Model)
    public static class PaymentRecord {
        private int paymentId;
        private String status;
        private double amount;
        private int residentId;

        public PaymentRecord(int paymentId, String status, double amount, int residentId) {
            this.paymentId = paymentId;
            this.status = status;
            this.amount = amount;
            this.residentId = residentId;
        }

        public int getPaymentId() {
            return paymentId;
        }

        public String getStatus() {
            return status;
        }

        public double getAmount() {
            return amount;
        }

        public int getResidentId() {
            return residentId;
        }
    }
}


//"TrackPayment acts as the Controller, handling user input and coordinating between the UI and the business logic."
//"PaymentService is the Information Expert, as it has the knowledge to query payment data from the database."
//"Separate classes (e.g., PaymentRecord, PaymentService) reduce interdependencies, improving maintainability."
//"Each class focuses on a single responsibility: TrackPayment handles UI, PaymentService handles database logic, and PaymentRecord models data."
//"The TableView uses PropertyValueFactory, which employs polymorphism to dynamically bind model properties."

