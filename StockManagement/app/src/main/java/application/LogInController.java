package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LogInController {
	@FXML 
	public Button login_button;
	@FXML 
	public Button first_signup_button;
	@FXML
	public TextField username_field;
	@FXML 
	protected PasswordField password_field;
	@FXML 
	public Label wrongLogin;
	
	
	
	
	@FXML 
	public void login() throws IOException 
	{
		try {
		String username= username_field.getText();
		String password= password_field.getText();
		boolean success;
	    String user;
	    String pass;
		String line;	
	    FileReader csvFile = new FileReader("src/main/resources/UserInformation.txt");
		BufferedReader reader=new BufferedReader(csvFile);
			
	        while((line= reader.readLine()) != null)
	        {	
	        	user=line.split(",")[2];
	        	pass=line.split(",")[3];
	        	
	           
	                if (user.equals(username) && pass.equals(password)) 
	                {	
	                	accounts currentUser = new accounts();
	                	//currentUser.setUser(username);
	                	
	                	success=true;
	                    Parent rootTwo = FXMLLoader.load(getClass().getClassLoader().getResource("home-page.fxml"));
	                    Stage windowTwo = (Stage) login_button.getScene().getWindow();
	                    windowTwo.setScene(new Scene(rootTwo));
	                }
	                else if(username.isEmpty() && password.isEmpty())
	                {
	                    wrongLogin.setText("Please enter your credentials");
	                    success=false;
	                }
	                else 
	                {
	                    wrongLogin.setText("Wrong username/password");
	                    success=false;

	                }
	            }
	            reader.close();
	        }
	        catch (Exception e) 
			{
	            System.out.println("File does not exist on the specified path.");
	        }
		
		
		
		
	}
	
	@FXML 
	public void signup() throws IOException 
	{
		Parent rootThree = FXMLLoader.load(getClass().getClassLoader().getResource("sign-up.fxml"));
		Stage windowThree = (Stage) first_signup_button.getScene().getWindow();
		windowThree.setScene(new Scene(rootThree));
		
	}

}
