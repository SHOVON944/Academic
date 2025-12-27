package utils;

public class Animation {

    public static void spinner(int seconds) {
        String[] spinner = {"|", "/", "-", "\\"};
        try {
            for (int i = 0; i < seconds * 10; i++) {
                System.out.print("\r" + spinner[i % spinner.length] + " Loading...");
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.print("\r✓ Done!           \n");
    }

    public static void printWithAnimation(String text, int delayMillis) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }
}
