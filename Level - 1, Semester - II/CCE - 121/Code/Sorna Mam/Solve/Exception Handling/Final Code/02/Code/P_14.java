/*
 Employee Payroll System 
 Input employee salary and tax rate. 
 Throw InvalidSalaryException if salary < 0. 
 Throw InvalidTaxRateException if tax < 0 or tax > 100. 
 Compute net salary otherwise. 
*/

import java.util.Scanner;


class InvalidSalaryException extends Exception{
    InvalidSalaryException(String message){
        super(message);
    }
}
class InvalidTaxRateException extends Exception{
    InvalidTaxRateException(String message){
        super(message);
    }
}

public class P_14 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter salary: ");
        int salary = scan.nextInt();
        System.out.print("Enter tax: ");
        int tax = scan.nextInt();

        try{
            if(salary<0){
                throw new InvalidSalaryException("Salary can't be negative.");
            }
            if(tax<0 || tax>100){
                throw new InvalidTaxRateException("Invalid Tax rate.");
            }

            double net = salary - (salary * tax/100.0);
            System.out.println("Net salary is:  " + net);
        } catch(InvalidSalaryException | InvalidTaxRateException e){
            System.out.println("Error. " + e.getMessage());
        } finally{
            scan.close();
        }
    }
}
