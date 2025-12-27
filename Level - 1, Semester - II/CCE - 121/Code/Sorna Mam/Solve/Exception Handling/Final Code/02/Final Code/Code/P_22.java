/*
22. Hospital Patient Management 
 A hospital has a limit on beds. 
 Throw NoBedAvailableException if a new patient is admitted when hospital is 
full. 
 Throw InvalidPatientDataException if patient age < 0 or name is empty.
*/

import java.util.Scanner;

class NoBedException extends Exception{
    NoBedException(String e){
        super(e);
    }
}
class InvalidPatientException extends Exception{
    InvalidPatientException(String e){
        super(e);
    }
}

class Bed{
    int totalBed;
    int currentPatient = 0;
    Bed(int totalBed){
        this.totalBed = totalBed;
    }

    void checkBed(String name, int age) throws NoBedException, InvalidPatientException{
        if(name.isEmpty()  ||  age<0){
            throw new InvalidPatientException("Invalid patient data! Name empty or age < 0");
        }
        if(totalBed<=currentPatient){
            throw new NoBedException("No beds available! Hospital is full.");
        }
        currentPatient++;
        System.out.println("Patient admitted successfully.\n Name: " + name + "\n Age:" + age);
        System.out.println("Available seat: " + (totalBed - currentPatient));
    }

}

public class P_22 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Hospital Total seat: ");
        int totalS = scan.nextInt();
        scan.nextLine();    // consume nextline
        Bed bed = new Bed(totalS);

        while(true){
            System.out.println("Enter patient name: ");
            String name = scan.nextLine();
            System.out.println("Enter patient age: ");
            int age = scan.nextInt();
            scan.nextLine();

            try{
                bed.checkBed(name, age);
            } catch(InvalidPatientException e){
                System.out.println("Error: " + e.getMessage());
            }catch(NoBedException e){
                System.out.println("Error: " + e.getMessage());
                break;
            }
        }
        scan.close();
    }
}
