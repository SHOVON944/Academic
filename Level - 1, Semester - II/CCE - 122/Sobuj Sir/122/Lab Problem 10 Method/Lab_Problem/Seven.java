package Lab_Problem;

public class Seven {

    public static void pentagonalCall(){
        for(int i=1; i<=50; i++){
            int formula = (int)(3*Math.pow(i,2) - i)/2;
            System.out.print(formula + " ");
            if(i%10==0) System.out.println();
        }
    }
    public static void main(String[] args) {
        System.out.println("First 50 pentagonal number is: ");
        pentagonalCall();
    }
}
