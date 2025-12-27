/*
(Constructor Failure) Write a program that shows a constructor passing information 
about constructor failure to an exception handler. Define class SomeClass, which throws 
an Exception in the constructor. Your program should try to create an object of type 
SomeClass and catch the exception that’s thrown from the constructor. 
*/


class SomeClass {
    public SomeClass() throws Exception {
        //The constructor is deliberately throwing an exception
        throw new Exception("Constructor failed: Could not create SomeClass object.");
    }
}

public class P_13 {
    public static void main(String[] args) {
        try {
            SomeClass obj = new SomeClass();  
            System.out.println("Object created successfully: " + obj);
        } 
        catch (Exception e) {
            // // Catching exception coming from Constructor
            System.out.println("Caught Exception from constructor: " + e.getMessage());
        }
    }
}