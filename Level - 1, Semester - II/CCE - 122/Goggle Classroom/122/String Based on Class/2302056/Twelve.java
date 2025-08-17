import java.util.Scanner;

public class Twelve {

    public static String compress(String s){
        if( (s == null)  ||  (s.isEmpty())){
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for(int i=0; i<n; ){
            char ch = s.charAt(i);
            int j = i;
            while((j<n)  &&  (s.charAt(j) == ch)){
                j++;
            }
            int count = j - i;
            sb.append(ch);
            if(count>1){
                sb.append(count);
            }
            i = j;
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = scan.nextLine();

        String compressed = compress(s);
        System.out.println("Compressed: " + compressed);

        scan.close();
    }
}
