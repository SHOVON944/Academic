package datacamp;
import java.util.Scanner;

public class Example1 {
    int a;
    int b;

    Example1(int a, int b){
        this.a = a;
        this.b =  b;
    }

    void display(){
        System.out.println("a = " + this.a + ", b =  " + this.b);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a: ");
        int a = scan.nextInt();
        System.out.print("Enter b: ");
        int b = scan.nextInt();
        Example1 obj = new Example1(a, b);
        obj.display();

        scan.close();
    }
}
