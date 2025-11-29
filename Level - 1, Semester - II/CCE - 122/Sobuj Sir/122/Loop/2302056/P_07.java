import java.util.Scanner;

public class P_07 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the units: ");
        float unit = scan.nextFloat();
        float bill = 0;

        if(unit <= 99){
            bill = unit * 1.5f;
        } else if(unit >= 100 && unit <= 199){
            bill = unit * 1.75f;
        } else if(unit >= 200 && unit <= 299){
            bill = unit * 1.85f;
        } else if(unit >= 300 && unit <= 499){
            bill = unit * 1.90f;
        } else if(unit >= 500 && unit <= 799){
            bill = unit * 2.00f;
        } else if(unit >= 800 && unit <= 1000){
            bill = unit * 2.10f;
        } else if(unit > 1000){
            bill = unit * 2.10f;
            bill = bill + bill * 0.10f;
            if(bill < 100){
                bill = 100;
            }
        }

        System.out.println("The bill is : " + bill);
    }
}
