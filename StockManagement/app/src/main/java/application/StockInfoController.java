package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class StockInfoController
{
    @FXML
    private Label stockSymbol;
    @FXML
    private Label stockCompany;
    @FXML
    private Label price;
    @FXML
    private Label open;
    @FXML
    private Label volume;
    @FXML
    private Label high;
    @FXML
    private Label low;
    @FXML
    private Button backButton;
    @FXML
    private TextField amtInvest;
    @FXML
    private Button purchaseButton;
    @FXML
    private Label numOfShares;
    
    private String stockSymbolStr;
    private String stockCompStr;
    private double priceVal;
    private double openVal;
    private double volumeVal;
    private double highVal;
    private double lowVal;
    private double amtInvestVal;
    private double numShares;

    @FXML
    public void displayStockInfo()
    {
        stockSymbol.setText(stockSymbolStr);
        stockCompStr = StockData.getCompanyName(stockSymbolStr);
        stockCompany.setText(stockCompStr);

        QuoteResponse response = StockData.getStockQuote(stockSymbolStr);
        priceVal = response.getPrice();
        openVal = response.getOpen();
        volumeVal = response.getVolume();
        highVal = response.getHigh();
        lowVal = response.getLow();

        price.setText(String.format("%.2f", priceVal));
        open.setText(String.format("%.2f", openVal));
        volume.setText(String.format("%.1f", volumeVal));
        high.setText(String.format("%.2f", highVal));
        low.setText(String.format("%.2f", lowVal));
    }

    public void setSymbol(String symbol)
    {
        stockSymbolStr = symbol;
    }

    @FXML
	private void goBack() throws IOException 
	{
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("home-page.fxml"));
		Stage window = (Stage) backButton.getScene().getWindow();
		window.setScene(new Scene(root));
	}

    @FXML
    private void purchaseStock()
    {
        amtInvestVal = Double.parseDouble(amtInvest.getText());

        if(accounts.balance != 0 && Double.compare(accounts.balance, amtInvestVal) >= 0)
        {
            accounts.balance -= amtInvestVal;
            numShares = amtInvestVal/priceVal;
            numOfShares.setText("Number of shares: " + String.format("%.2f", numShares));
            HomePageController.addPurchasedstock(stockSymbolStr + " - " + String.format("%.2f", numShares));
        }
        else
        {
            numOfShares.setText("Not enough balance!");
        }
    }
}
