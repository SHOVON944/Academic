package Lab_Problem;
import java.util.Scanner;

public class Eight {

    public static double formulaCall(double p, double r, int n){
        double monthyRateI = (r/100)/12;    // yearly rate -> monthly rate
        int month = n * 12;                 // year -> month
        double calculate = p * Math.pow((1+monthyRateI), month);
        return calculate;
    }   
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the investment amount: ");
        double p = scan.nextDouble();
        System.out.print("Enter the rate of interest: ");
        double r = scan.nextDouble();
        System.out.print("Enter number of years: ");
        int n = scan.nextInt();
        System.out.println("Years FutureValue: ");
        for(int i=1; i<=n; i++){
            double call = formulaCall(p, r, i);
            System.out.printf("%d %.2f\n", i, call);
        }

        scan.close();
    }
}
