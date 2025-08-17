import java.util.Scanner;

public class P_17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter time (e.g.,10:45 PM): ");
        String time = sc.next();
        String ampm = sc.next();

        String[] t = time.split(":");
        int h = Integer.parseInt(t[0]);
        int m = Integer.parseInt(t[1]);

        if(ampm.equalsIgnoreCase("PM") && (h!=12)) h += 12;
        if (ampm.equalsIgnoreCase("AM") && (h==12)) h = 0;

        System.out.printf("%02d:%02d\n", h, m);
    }
}
