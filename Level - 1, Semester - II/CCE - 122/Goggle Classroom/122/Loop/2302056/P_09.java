import java.util.Scanner;

public class P_09 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a: ");
        double a = scan.nextDouble();
        System.out.print("Enter b: ");
        double b = scan.nextDouble();
        System.out.print("Enter c: ");
        double c = scan.nextDouble();

        double det, x1, x2, realPart, imagPart;
        det = Math.pow(b,b) - 4*a*c;

        if(det>0){
            x1 = (-b+Math.sqrt(det)) / (2 * a);
            x2 = (-b-Math.sqrt(det)) / (2*a);
            System.out.printf("Two distinct real roots: %.2f and %.2f\n", x1, x2);
        }
        else if(det==0){
            x1 = x2 = -b/(2*a);
            System.out.printf("One real root (repeated): %.2f and %.2f\n", x1, x2);
        }
        else{
            realPart = -b/(2*a);
            imagPart = Math.sqrt(-det)/(2*a);
            System.out.printf("Complex roots: %.2f + %.2fi and %.2f - %.2fi\n",
                    realPart, imagPart, realPart, imagPart);
        }
    }
}
