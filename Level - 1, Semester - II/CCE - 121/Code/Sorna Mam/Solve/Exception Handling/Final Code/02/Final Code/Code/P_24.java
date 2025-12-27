/*
24. Stock Trading Platform 
 Implement a method buyShares(String stock, int quantity, double 
price) that throws: 
o InvalidStockException if stock symbol is not recognized. 
o InsufficientFundsException if account balance < total purchase price. 
o InvalidQuantityException if quantity ≤ 0.
*/

import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;


class InvalidStockException extends Exception{
    InvalidStockException(String s){
        super(s);
    }
}
class InsufficientFundsException extends Exception{
    InsufficientFundsException(String s){
        super(s);
    }
}
class InvalidQuantityException extends Exception{
    InvalidQuantityException(String s){
        super(s);
    }
}
class ZeroBalanceException extends Exception{
    ZeroBalanceException(String s){
        super(s);
    }
}

class Stock{
    double accountBalance;
    Set <String> validStock = new HashSet<>(Arrays.asList("AAPL", "GOOG", "AMZN", "MSFT", "TSLA"));
    Stock(double accountBalance){
        this.accountBalance = accountBalance;
    }
    void buyShares(String symbol, int quantity, double price) throws InvalidStockException, InsufficientFundsException, InvalidQuantityException, ZeroBalanceException{
        if(accountBalance == 0){
            throw new ZeroBalanceException("Your balance is zero.");
        }
        if(!validStock.contains(symbol.toUpperCase())){
            throw new InvalidStockException("Invalid stock symbol: " + symbol);
        }
        if(quantity<=0){
            throw new InvalidQuantityException("Quantity must be greater than 0");
        }
        double totalCost = quantity*price;
        if( (totalCost) > accountBalance){
            throw new InsufficientFundsException("Insufficient balance! Need $" + totalCost + " but have $" + accountBalance);
        }
        accountBalance -= totalCost;
        System.out.println("✅ Purchase successful!");
        System.out.println("Bought " + quantity + " shares of " + symbol + " at $" + price + " each.");
        System.out.println("Remaining Balance: $" + accountBalance);
    }
}

public class P_24 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your account Balance: ");
        double balance = scan.nextDouble();
        scan.nextLine();
        Stock st = new Stock(balance);
        while(true){
            System.out.println("Enter stock symbol to buy (or 'exit' to quit): ");
            String stockName = scan.nextLine();
            if(stockName.contains("exit")) break;
            System.out.println("Enter quantity: ");
            int quantity = scan.nextInt();
            System.out.println("Enter price per stock: ");
            double price = scan.nextDouble();
            scan.nextLine();

            try{
                st.buyShares(stockName, quantity, price);
            } catch(InvalidStockException | InsufficientFundsException | InvalidQuantityException e){
                System.out.println("Error: " + e.getMessage());
            } catch(ZeroBalanceException e){
                System.out.println(e.getMessage());
                break;
            }
        }

        scan.close();
    }
}
