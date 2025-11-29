package Lab_Problem;

public class Nine {

    public static void charCall(int x){
        char ch = (char)(x);
        System.out.print(ch + " ");
        if(x%20==0) System.out.println();
    }
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        System.out.print("Enter the 1st number: ");
        int a = scan.nextInt();
        System.out.print("Enter the 2nd number: ");
        int b = scan.nextInt();
        for(int i=a; i<=b; i++){
            charCall(i);
        }

        scan.close();
    }
}
