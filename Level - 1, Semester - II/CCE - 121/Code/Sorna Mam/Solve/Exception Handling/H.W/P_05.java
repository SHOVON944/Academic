/*
Write a Java program to create a method that reads a file and throws an exception if the 
file is not found. 
Sample Output: 
Error: The file 'sample.txt' was not found. 
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class P_05 {

    static void readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);

        System.out.println("File content:");
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
        sc.close();
    }

    public static void main(String[] args) {
        try {
            readFile("sample.txt");
        } 
        catch (FileNotFoundException e) {
            System.out.println("Error: The file 'sample.txt' was not found.");
        }
    }
}
