package Lab_Problem;

public class Sixten {

    public static boolean isPrime(int a){
        if(a<2) return false;
        for(int i=2; i<=Math.sqrt(a); i++){
            if(a%i==0) return false;
        }
        return true;
    }

    public static void call(){
        for(int i=2; i<100-2; i++){
            if(isPrime(i) && isPrime(i+2)){
                System.out.println("(" + i + ", " + (i+2) + ")");
            }
        }
    }
    public static void main(String[] args) {
        call();
    }
}
