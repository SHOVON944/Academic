package utils;

import java.util.Random;

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


    // print text with delay...
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

    // print text with delay and random colour animation.
    public static void printWithRandomColorAnimation(String text, int delay) {
        Random random = new Random();
        for (char c : text.toCharArray()) {
            if (c != '\n' && c != ' ') {
                String color = ColorPrint.COLORS[random.nextInt(ColorPrint.COLORS.length)]; //length dia mot koita clr ase, random.nextInt dia 0 theke highest clr sonkhar random index beche nicce, ColorPrint.COLORS[] dia sei index er clr code...
                System.out.print(color + c + ColorPrint.ANSI_RESET);
            } else {
                System.out.print(c);
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }
}