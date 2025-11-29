package Lab_Problem;

import java.util.Scanner;

public class Nineten {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the row of the matrix: ");
        int row = scan.nextInt();
        System.out.print("Enter the column of the matrix: ");
        int col = scan.nextInt();

        int[][] mat1 = new int[row][col];
        int[][] mat2 = new int[row][col];
        int[][] sum = new int[row][col];

        System.out.print("Enter 1st Matrix elements: ");
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                mat1[i][j] = scan.nextInt();
            }
        }

        System.out.print("Enter 2nd Matrix elements: ");
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                mat2[i][j] = scan.nextInt();
            }
        }

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                sum[i][j] = mat1[i][j] + mat2[i][j];
            }
        }
        System.out.println("Enter sum of matrix 1 and 2: ");
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                System.out.print(sum[i][j] + " ");
            }
            System.out.println();
        }

        scan.close();
    }
}
