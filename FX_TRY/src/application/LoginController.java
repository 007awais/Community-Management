package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorMessageLabel;

    // Method to handle login button click
    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Here you can add your login validation logic (e.g., checking against a database or predefined values)
        if (isValidCredentials(username, password)) {
            errorMessageLabel.setText("Login successful!");

            // Navigate to the Admin Dashboard after successful login
            try {
                // Load the Admin Dashboard FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_dashboard.fxml"));
                Scene adminDashboardScene = new Scene(loader.load());

                // Get the current stage (login screen) and set the new scene (admin dashboard)
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(adminDashboardScene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace(); // Handle exception if loading the FXML fails
            }
            
           
        } 
        else {
        	if(isValidCredentialsUser(username,password))
        	{
        		 errorMessageLabel.setText("Login successful!");
        		  try {
                      // Load the Admin Dashboard FXML
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("Resident_Dashboard.fxml"));
                      Scene adminDashboardScene = new Scene(loader.load());

                      // Get the current stage (login screen) and set the new scene (admin dashboard)
                      Stage stage = (Stage) loginButton.getScene().getWindow();
                      stage.setScene(adminDashboardScene);
                      stage.show();

                  } catch (IOException e) {
                      e.printStackTrace(); // Handle exception if loading the FXML fails
                  }
        		 
        	}
        	
        }
        
        
        
    }

    private boolean isValidCredentials(String username, String password) {
        // Here, implement the logic for checking credentials.
        // For demonstration purposes, we use static values:
        return "admin".equals(username) && "password".equals(password);
    }
    
    private boolean isValidCredentialsUser(String username,String password)
    {
    	  return "user".equals(username) && "password".equals(password);
    	  
    			  }
}

