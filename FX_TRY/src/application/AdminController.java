package application;
import application.Admin;
import application.TrackPayment;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class AdminController {

    public Button goBackButton;
    public Button TrackPayment;
    public Button ManageEvent;
    public Button Notifications;
    public Button Dispute;
    public Button Visitor;
    public Button Survillence;
    public Button Maintenance;
    private AnchorPane searchPane;

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
    public void handleTrackPayment() {
        try {
            // Load the Track_Payment.fxml view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Track_Payment.fxml"));
            
            // Dynamically set the controller for the scene
           
            
            // Load the scene
            Scene trackPaymentScene = new Scene(loader.load());

            // Get the current stage (admin dashboard) and set the new scene
            Stage stage = (Stage) goBackButton.getScene().getWindow();  // Use goBackButton to get the current stage
            stage.setScene(trackPaymentScene);  // Change the scene
            stage.show();
            // Optionally add logic to handle after scene is displayed
            System.out.println("Track Payment scene displayed, controller switched.");

        } catch (IOException e) {
            e.printStackTrace();  // Handle exception if loading fails
        }
    }
    
    
    public void handleCreateEvent() {
        try {
            // Load the DisplayEvent.fxml view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayEvent.fxml"));
            
            // Dynamically set the controller for the scene
            EventController displayEventController = new EventController();  // Create a new instance of the controller
            loader.setController(displayEventController);  // Set the controller
            
            // Load the scene
            Scene displayEventScene = new Scene(loader.load());

            // Get the current stage (admin dashboard) and set the new scene
            Stage stage = (Stage) goBackButton.getScene().getWindow();  // Use goBackButton to get the current stage
            stage.setScene(displayEventScene);  // Change the scene
            stage.show();

            // Optionally add logic to handle after scene is displayed
            System.out.println("Display Event scene displayed, controller switched.");

        } catch (IOException e) {
            e.printStackTrace();  // Handle exception if loading fails
        }
    }
   
    
    public void handleCreateNotifications()
    {
    	
    	
    	  try {
              // Load the DisplayEvent.fxml view
              FXMLLoader loader = new FXMLLoader(getClass().getResource("AutomateNotifications.fxml"));
              
              // Dynamically set the controller for the scene
              NotificationController displaynotificatioController = new NotificationController();  // Create a new instance of the controller
              loader.setController(displaynotificatioController);  // Set the controller
              
              // Load the scene
              Scene displayEventScene = new Scene(loader.load());

              // Get the current stage (admin dashboard) and set the new scene
              Stage stage = (Stage) goBackButton.getScene().getWindow();  // Use goBackButton to get the current stage
              stage.setScene(displayEventScene);  // Change the scene
              stage.show();

              // Optionally add logic to handle after scene is displayed
              System.out.println("Display Event scene displayed, controller switched.");

          } catch (IOException e) {
              e.printStackTrace();  // Handle exception if loading fails
          }
    	
    }
    
    public void handleDispute() {
        try {
            // Load the Dispute.fxml view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dispute.fxml"));

            // Create and set the controller for the scene
            DisputeController disputeController = new DisputeController();
            loader.setController(disputeController);

            // Load the scene
            Scene disputeScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) goBackButton.getScene().getWindow(); 
            stage.setScene(disputeScene);
            stage.show();

            // Optionally log success
            System.out.println("Dispute scene displayed, controller dynamically assigned.");

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load Dispute.fxml or set its controller.");
        }
    }
    
    
    public void handleVisitors()
    {
    	try {
            // Load the Dispute.fxml view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AutomateVisitors.fxml"));

            // Create and set the controller for the scene
            VisitorController visitorController = new VisitorController();
            loader.setController(visitorController);

            // Load the scene
            Scene disputeScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) goBackButton.getScene().getWindow(); 
            stage.setScene(disputeScene);
            stage.show();

            // Optionally log success
            System.out.println("Visitor Scene  displayed, controller dynamically assigned.");

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load the Visitor scene");
        }
    }
    
    
    
    public void handleSurvillence()
    {
    	try {
            // Load the Dispute.fxml view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AutomateSurvellince.fxml"));

            // Create and set the controller for the scene
           Survillence survillence = new Survillence();
            loader.setController(survillence);

            // Load the scene
            Scene disputeScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) goBackButton.getScene().getWindow(); 
            stage.setScene(disputeScene);
            stage.show();

            // Optionally log success
            System.out.println("Survillence Scene  displayed, controller dynamically assigned.");

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load the Visitor scene");
        }
    }
    
    

    public void handleMaintenance()
    {
    	try {
            // Load the Dispute.fxml view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MaintenanceRequest.fxml"));

            // Create and set the controller for the scene
         MaintenanceRequestController survillence = new MaintenanceRequestController();
            loader.setController(survillence);

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

    }
   
    
    
    
    

    

    

   
    

