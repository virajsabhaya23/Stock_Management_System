package application;

import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SignUpController {
	
	@FXML 
	public Button back_login_button;
	@FXML
	public Button secondSignUpButton;
	@FXML 
	public TextField firstname_field;
	@FXML
	public TextField lastname_field;
	@FXML
	public TextField email_field;
	@FXML 
	protected TextField signup_password_field;
	
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String password;
	
	
	@FXML 
	public void signupUser() throws IOException{  
		
        firstName = firstname_field.getText();
        lastName = lastname_field.getText();
        email = email_field.getText();
        password = signup_password_field.getText();
        boolean success;
        if(email.contains("@") || email.contains(".")){
	        FileWriter csvWriter = new FileWriter(("test.csv"), true);
	        csvWriter.append(firstName);
	        csvWriter.append(",");
	        csvWriter.append(lastName);
	        csvWriter.append(",");
	        csvWriter.append(email);
	        csvWriter.append(",");
	        csvWriter.append(password);
	        csvWriter.append("\r\n");
	        csvWriter.close();
            Parent rootFour = FXMLLoader.load(getClass().getClassLoader().getResource("home-page.fxml"));
            Stage windowFour = (Stage) secondSignUpButton.getScene().getWindow();
            windowFour.setScene(new Scene(rootFour));
        }
        else {
        	//wrong signup here
        	
        }


    }
	
	
	@FXML
	public void backToLogin() throws IOException
	{
		Parent rootFive = FXMLLoader.load(getClass().getClassLoader().getResource("log-in.fxml"));
		Stage windowFive = (Stage) back_login_button.getScene().getWindow();
		windowFive.setScene(new Scene(rootFive));
				
				
			
	}		
}
