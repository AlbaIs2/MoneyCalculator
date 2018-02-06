package moneycalculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MoneyCalculator2 {
    
    public static void main(String[] args) throws Exception{
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.execute();        
    }
    
    private Double amount;
    private Double exchangeRate;
    private String currency;
    
    private  void execute() throws Exception{
        input();
        process();
        output();
    }
    
    private void input(){
        System.out.println("Introduzca cantidad");
        Scanner scanner = new Scanner(System.in);
        amount = Double.parseDouble(scanner.next());
        
        System.out.println("Introduzca divisa");
        Scanner input = new Scanner(System.in);
        currency = input.next();
    }
    
    private void process() throws Exception{
        exchangeRate = getExchangeRate(currency, "EUR");
    }
    
    private void output(){
        System.out.println(amount + " " + currency + " equivalen a " + amount * exchangeRate + " EUR");
    }
    
    private static double getExchangeRate(String form, String to) throws Exception{
        URL url = new URL("http://api.fixer.io/latest?base=" + form +"&symbols=" + to);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStreamReader input = new InputStreamReader(connection.getInputStream());
        try (BufferedReader reader = new BufferedReader(input)){
            String line= reader.readLine();
            line = line.substring(line.indexOf(to)+5, line.indexOf("}"));
            return Double.parseDouble(line);
        }
    }
    
}
