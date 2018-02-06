package moneycalculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MoneyCalculator {

    public static void main(String[] args) throws Exception{
        System.out.println("Introduzca una cantidad de dolares");
        Scanner scanner = new Scanner(System.in);
        double amount = Double.parseDouble(scanner.next());
        double exchangeRate = getExchangeRate("USD", "EUR");
        System.out.println(amount + " USD equivalen a " + amount * exchangeRate + "EUR");
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

    void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
