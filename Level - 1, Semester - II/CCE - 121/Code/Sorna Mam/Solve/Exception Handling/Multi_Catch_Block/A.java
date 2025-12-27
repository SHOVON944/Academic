package Multi_Catch_Block;
//Write a Java method to print characters between two characters (i.e. A to P ). Go to the editor
//Note: Prints 20 characters per line
import java.util.*;
public class A {
    public static void printchars(char ch1,char ch2,int numberPerLine)
    {
        int count =0;
        for(char c=ch1;c<=ch2;c++)
        {
            System.out.print(c+" ");
            count++;
            if(count%numberPerLine==0)
            {
                System.out.println();

            }

        }
         if (count % numberPerLine != 0) {
            System.out.println();
         }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the starting character: ");
        char ch1 = input.next().charAt(0);

        System.out.print("Enter the ending character: ");
        char ch2 = input.next().charAt(0);

        System.out.print("Enter number of characters per line: ");
        int numberPerLine = input.nextInt();

        System.out.println("\nCharacters from " + ch1 + " to " + ch2 + " are:");
    printchars(ch1, ch2, numberPerLine);


        
    }

    
}