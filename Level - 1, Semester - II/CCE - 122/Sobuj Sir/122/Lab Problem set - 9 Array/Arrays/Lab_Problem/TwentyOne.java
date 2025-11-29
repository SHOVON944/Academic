package Lab_Problem;
import java.util.ArrayList;

public class TwentyOne {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        String[] arr = new String[list.size()];
        list.toArray(arr);

        System.out.println("Array elements:");
        for (String item : arr) {
            System.out.println(item);
        }
    }
}
