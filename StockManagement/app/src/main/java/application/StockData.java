package application;

import java.util.*;

import com.crazzyghost.alphavantage.*;
import com.crazzyghost.alphavantage.fundamentaldata.response.CompanyOverviewResponse;
import com.crazzyghost.alphavantage.parameters.*;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import com.crazzyghost.alphavantage.timeseries.response.StockUnit;

public class StockData
{
    static String apiKey = "31AGPCA8HSGPDJKR";
    static Config cfg = Config.builder()
        .key(apiKey)
        .timeOut(10)
        .build();

    public static TimeSeriesResponse getTimeSeriesData(String stockName)
    {
        AlphaVantage.api().init(cfg);
        TimeSeriesResponse response = null;

        try
        {
            //Intraday data - 9:00-18:00 for the past couple months
            response = AlphaVantage.api()
                .timeSeries()
                .intraday()
                .forSymbol(stockName)
                .interval(Interval.SIXTY_MIN)
                .outputSize(OutputSize.FULL)
                .fetchSync();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        return response;
    }

    public static QuoteResponse getStockQuote(String stockName)
    {
        AlphaVantage.api().init(cfg);
        QuoteResponse response = null;

        try
        {
            response = AlphaVantage.api()
                .timeSeries()
                .quote()
                .forSymbol(stockName)
                .fetchSync();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return response;
    }

    public static String getCompanyName(String symbol)
    {
        AlphaVantage.api().init(cfg);
        CompanyOverviewResponse response = null;

        try
        {
            response = AlphaVantage.api()
                .fundamentalData()
                .companyOverview()
                .forSymbol(symbol)
                .fetchSync();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return response.getOverview().getName();
    }

    private void printData(TimeSeriesResponse response)
    {
        List<StockUnit> stockData = response.getStockUnits();

        for(int i = 0; i < stockData.size(); i++)
        {
            StockUnit unit = stockData.get(i);
            System.out.printf("\nOpen = %.2f, Close = %.2f, High = %.2f, Low = %.2f, Date = %s", unit.getOpen(), unit.getClose(), unit.getHigh(), unit.getLow(), unit.getDate());
        }
    }
}
