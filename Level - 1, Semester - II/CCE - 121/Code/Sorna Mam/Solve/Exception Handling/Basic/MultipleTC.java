package Basic;


public class MultipleTC {
    public static void main(String[] args) {
        System.out.println("\n");
        String s = null;
        try{
            int data = 100/0;
            int l = s.length();
        } catch(ArithmeticException e){
            System.out.println(e);
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        } catch(NullPointerException np){
            System.out.println("Null point occurs.");
        }
        System.out.println("Rest of code");
    }
}
