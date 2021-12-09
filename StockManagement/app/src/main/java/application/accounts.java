package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.io.File;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class accounts implements Initializable
{
	@FXML
	private Label label;
	@FXML
	protected Button withdraw_button;
	
	@FXML
	protected Button deposit_button;
	@FXML
	protected Button back_button;
	@FXML
	private Label balance_label;

	@FXML
	protected TextField enter_amount;
	
	String amountToDepositString;
	String amountToWithdrawString;
	Double amountToDeposit;
	Double amountToWithdraw;
	static double balance;
	static int location;
	
	
	@FXML
	public void onBackButton() throws IOException 
	{
		String line;
		String user;
		int i = 0;
		
		// input the (modified) file content to the StringBuffer "input"
		BufferedReader file = new BufferedReader(new FileReader("src/main/resources/UserBalance.csv"));
		StringBuffer inputBuffer = new StringBuffer();

		while ((line = file.readLine()) != null) {
			if (i == location) 
			{	
				line = Double.toString(balance);
				inputBuffer.append(line);
				inputBuffer.append("\n");
			}
			i++;

		}
		file.close();

		// write the new string with the replaced line OVER the same file
		FileOutputStream fileOut = new FileOutputStream("UserBalance.csv");
		fileOut.write(inputBuffer.toString().getBytes());
		fileOut.close();
		
		Parent rootEight = FXMLLoader.load(getClass().getClassLoader().getResource("home-page.fxml"));
		Stage windowEight = (Stage) back_button.getScene().getWindow();
		windowEight.setScene(new Scene(rootEight)); 
				
	}
	protected void updateDeposit(Double deposit_amount) 
	{
		balance += deposit_amount;
		
	}
	
	protected void updateWithdraw(Double withdraw_amount) 
	{
		balance += withdraw_amount;
	}
	
	@FXML
	protected void onDepositButtonClick() 
	{
		amountToDepositString=enter_amount.getText();
		amountToDeposit=Double.parseDouble(amountToDepositString);
		updateDeposit(amountToDeposit);
		if (amountToDepositString.matches("[0-9.]+")) {
			label.setText("Depositing $" + amountToDepositString +" to your account");
			balance_label.setText("Account Balance: $" + balance); //this will need to access account balance and update
			enter_amount.clear();
		}
		else
		{
			label.setText("Not a valid entry");
		}
		
		
	}
	
	
	
	@FXML
	protected void onWithdrawButtonClick() 
	{
		amountToWithdrawString=enter_amount.getText();
		amountToWithdraw = Double.parseDouble(amountToWithdrawString);
		updateWithdraw(amountToWithdraw);
		if (amountToWithdrawString.matches("[0-9.]+")){
			label.setText("Withdrawing $" + amountToWithdrawString +" from your account");
			balance_label.setText("Account Balance: "+ balance);
			enter_amount.clear();//this will need to access account balance and update
		}
		else
		{
			label.setText("Not a valid entry");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		balance_label.setText("Account Balance: $" + balance);
	}
}

