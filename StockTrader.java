//They lost money.
//The attacks on the world trade centers in New York. The timing makes sense because the initial shock caused resulted in economic instability, primarily affecting airlines, travel companies, entertainment, and more.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class StockTrader {
	
	public static void loadData(Scanner in, int[] years, int[] months, double[] price){
		for(int  i = 0; i<years.length; i++){
			years[i] = in.nextInt();
			months[i] = in.nextInt();
			price[i] = in.nextDouble();
		}
	}
	public static String monthName(int month){
		String[] monthNom = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		return monthNom[month-1];
	}
	
	public static int indexOfLowest(double[] prices){
		int res = 0;
		double lastP = prices[0];
		for ( int i = 0; i<prices.length; i++){
			if (prices[i]<lastP){
				res = i;
				lastP = prices[i];
			}
		}return res;
	}
	
	
	public static int indexOfHighest(double[] prices){
		int res = 0;
		double lastP = prices[0];
		for ( int i = 0; i<prices.length; i++){
			if (prices[i]>lastP){
				res = i;
				lastP = prices[i];
			}
		}return res;
	}
	
	public static void printData(int[] years, int[] months, double[] prices){
		
		for(int i =0; i<years.length; i++){
		System.out.print(monthName(months[i])+" ");
		System.out.print(years[i]+" ");
		System.out.printf("$" + "%.2f", prices[i]);
		System.out.println();
	}
}

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("tiny.txt"));
        String symbol = in.next();
        int n = in.nextInt();

        // TODO: create the three arrays, each with n elements
		int[] years = new int[n];
		int[] months = new int[n];
		double[] price = new double[n];
		
        // TODO: call loadData, then printData
        loadData(in, years, months, price);
        printData(years, months, price);
        
        int high = indexOfHighest(price);
        int low = indexOfLowest(price);
        System.out.println("HIGHEST PRICE: $" + price[high] +" "+ monthName(months[high]) +" "+ years[high]);
        System.out.println("LOWEST PRICE: $" + price[low] +" "+ monthName(months[low]) +" "+ years[low]);
    
    double result = simulate(years, months, price);
System.out.printf("Final value: $%,.2f%n", result);
    
    }
    
    public static final double START_CASH = 10000.0;
public static final double FEE = 10.0;

public static double simulate(int[] years, int[] months, double[] prices) {
    double cash = START_CASH;
    int shares = 0;
    double boughtAt = 0.0;

    for (int i = 0; i < prices.length; i++) {
        double[] history = Arrays.copyOf(prices, i + 1);
        String action = decide(history, shares > 0, boughtAt);

        if (action.equals("BUY") && shares == 0) {
            int canBuy = (int) ((cash - FEE) / prices[i]);
            if (canBuy > 0) {
                shares = canBuy;
                cash = cash - shares * prices[i] - FEE;
                boughtAt = prices[i];
                System.out.printf("%s %d: BUY %d shares at $%.2f%n",
                        monthName(months[i]), years[i], shares, prices[i]);
            }
        } else if (action.equals("SELL") && shares > 0) {
            cash = cash + shares * prices[i] - FEE;
            System.out.printf("%s %d: SELL %d shares at $%.2f%n",
                    monthName(months[i]), years[i], shares, prices[i]);
            shares = 0;
            boughtAt = 0.0;
        }
    }
    return cash + shares * prices[prices.length - 1];
}

public static String decide(double[] history, boolean holding, double boughtAt) {
    return dipBuyer(history, holding, boughtAt);   // change this line to switch strategies
}

public static String buyAndHold(double[] history, boolean holding, double boughtAt) {
	return "BUY";

}

public static String dipBuyer(double[] history, boolean holding, double boughtAt) {
	
	for (int i = 2; i<history.length; i++){
		if (history[i]<history[i-1] && history[i-1]<history[i-2]){
			return "BUY";
		}
		if (holding==true && boughtAt*1.1<history[i]){
			return "SELL";
		}
} return "HOLD";
}
}
