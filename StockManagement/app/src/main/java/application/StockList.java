package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.io.File;
import java.util.Scanner;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.*;
import javafx.event.*;

public class StockList implements Initializable
{
    ArrayList<String> stockList = new ArrayList<>();
    boolean initialized;

    @FXML
    private ListView<String> listView;
    @FXML
    private Button backButton;
    

    private void updateStockList()
    {
        try
        {
            File file = new File("src/main/resources/StocksList.txt");
            Scanner inFile = new Scanner(file);

            while(inFile.hasNextLine())
            {
                String stockSymbol = inFile.nextLine();
                stockList.add(stockSymbol);
            }

            inFile.close();
            initialized = true;
        }
        catch(Exception e)
        {
            System.out.println("Stocks could not be added!");
            initialized = false;
        }
    }

    @FXML
	private void goBack() throws IOException 
	{
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("home-page.fxml"));
		Stage window = (Stage) backButton.getScene().getWindow();
		window.setScene(new Scene(root));
	}

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        listView.getItems().clear();
        updateStockList();

        listView.getItems().addAll(stockList);
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click)
            {
                if (click.getClickCount() == 2) 
                {
                   String symbol = listView.getSelectionModel().getSelectedItem();

                   try{ goToStockInfo(symbol); }
                   catch(Exception e) {}
                }
            }
        });
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
