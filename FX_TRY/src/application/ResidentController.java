package application;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ResidentController {
    // Define @FXML-annotated methods or fields if needed for dashboard functionality
	
	
	public Button goBackButton;
	public Button maintenance;
	public Button fee;
	public Button RSVP;
	public Button Parking;
	public Button Bill;

    // Method to handle "Go Back" button click
    public void handleGoBack() {
        try {
            // Load the login screen FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Scene loginScene = new Scene(loader.load());

            // Get the current stage (admin dashboard) and set the new scene (login screen)
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            stage.setScene(loginScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception if loading the FXML fails
        }
    }
    public void handleMaintenanceRequest()
    {
    	try {
            // Load the Dispute.fxml view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("maintenanceResident.fxml"));

            // Create and set the controller for the scene
       

            // Load the scene
            Scene disputeScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) goBackButton.getScene().getWindow(); 
            stage.setScene(disputeScene);
            stage.show();

            // Optionally log success
            System.out.println("Maintenance  Scene  displayed, controller dynamically assigned.");

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load the Visitor scene");
        }
    }
    
    public void handleFee()
    {
    	try {
            // Load the Dispute.fxml view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PayForEvent.fxml"));

            // Create and set the controller for the scene
        

            // Load the scene
            Scene disputeScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) goBackButton.getScene().getWindow(); 
            stage.setScene(disputeScene);
            stage.show();

            // Optionally log success
            System.out.println("Payment  Scene  displayed, controller dynamically assigned.");

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load the Visitor scene");
        }
    }
    
    public void handleRSVP()
    {
    	try {
            // Load the Dispute.fxml view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RSVP.fxml"));

            

            // Load the scene
            Scene disputeScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) goBackButton.getScene().getWindow(); 
            stage.setScene(disputeScene);
            stage.show();

            // Optionally log success
            System.out.println("Payment  Scene  displayed, controller dynamically assigned.");

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load the Visitor scene");
        }
    }
    
    
    public void handleParking()
    {
    	try {
            // Load the Dispute.fxml view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AutomateParking.fxml"));

            // Create and set the controller for the scene
        

            // Load the scene
            Scene disputeScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) goBackButton.getScene().getWindow(); 
            stage.setScene(disputeScene);
            stage.show();

            // Optionally log success
            System.out.println("Parking Scene  displayed, controller dynamically assigned.");

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load the Visitor scene");
        }
    }
    
    public void handleBill()
    {
    	try {
            // Load the Dispute.fxml view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BillAdjustement.fxml"));

            // Create and set the controller for the scene
        

            // Load the scene
            Scene disputeScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) goBackButton.getScene().getWindow(); 
            stage.setScene(disputeScene);
            stage.show();

            // Optionally log success
            System.out.println("Bill Adjustement Scene  displayed, controller dynamically assigned.");

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load the Visitor scene");
        }
    }
    
    
    
    
    
    
    
    
}
