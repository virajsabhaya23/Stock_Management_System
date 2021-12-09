package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.input.*;
import javafx.event.*;

public class HomePageController implements Initializable
{
	
	@FXML
	private Button logout_button;
	@FXML
	private Button account_button;
	@FXML
	private Button stockListButton;
	@FXML
	private ListView<String> listView;
	
	private static ArrayList<String> purchasedStocks = new ArrayList<String>();

	@FXML
	public void goToAccount() throws IOException 
	{
		Parent rootSix = FXMLLoader.load(getClass().getClassLoader().getResource("account-page.fxml"));
        Stage windowSix = (Stage) account_button.getScene().getWindow();
        windowSix.setScene(new Scene(rootSix));
	}
	
	@FXML
	public void goToStockList() throws IOException 
	{
		Parent rootTen = FXMLLoader.load(getClass().getClassLoader().getResource("stocklist-page.fxml"));
        Stage windowTen = (Stage) stockListButton.getScene().getWindow();
        windowTen.setScene(new Scene(rootTen));
	}
	
	@FXML
	public void logout() throws IOException 
	{
		Parent rootSeven = FXMLLoader.load(getClass().getClassLoader().getResource("log-in.fxml"));
        Stage windowSeven = (Stage) logout_button.getScene().getWindow();
        windowSeven.setScene(new Scene(rootSeven));
	}

	public static void addPurchasedstock(String stockAddition)
	{
		purchasedStocks.add(stockAddition);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		if(purchasedStocks.size() > 0)
		{
			listView.getItems().clear();
			listView.getItems().addAll(purchasedStocks);

			listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent click)
				{
					if (click.getClickCount() == 2) 
					{
						String currentSelectedItem = listView.getSelectionModel().getSelectedItem();
						String[] parsedStr = currentSelectedItem.split(" ");
						String symbol = parsedStr[0].trim();

						try{ goToStockInfo(symbol); }
						catch(Exception e) {}
					}
				}
			});
		}
		else
		{
			listView.setVisible(false);
		}
	}

	@FXML
    public void goToStockInfo(String symbol) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("stockinfo-page.fxml"));
        Parent root = loader.load();

        StockInfoController controller = loader.getController();
        controller.setSymbol(symbol);
        controller.displayStockInfo();

        Stage window = (Stage) listView.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
