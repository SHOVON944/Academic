/*
University Course Registration 
 Each course has a limited number of seats. 
 Implement registerStudent() that throws: 
o SeatFullException when capacity is exceeded. 
o PrerequisiteNotMetException if student hasn’t completed required 
courses.
*/

import java.util.Scanner;

class PreQNotMetException extends Exception{
    PreQNotMetException(String s){
        super(s);
    }
}

class Resistra{
    String courseName;
    int totalseat;
    int registration = 0;

    Resistra(String courseName, int totalseat){
        this.courseName = courseName;
        this.totalseat = totalseat;
    }

    void CheckResis(String StdName, boolean preRes) throws SeatFullException, PreQNotMetException{
        if(!preRes){
            throw new PreQNotMetException("Student can't Pre-resistration for " + courseName);
        }
        if(totalseat<=registration){
            throw new SeatFullException("Seat are not availabe for" + courseName);
        }
        registration++;
        System.out.println("✅ Student '" + StdName + "' successfully registered for " + courseName);
        System.out.println("Available seats left: " + (totalseat - registration));

    }
}

public class P_23 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter course number: ");
    }
}
