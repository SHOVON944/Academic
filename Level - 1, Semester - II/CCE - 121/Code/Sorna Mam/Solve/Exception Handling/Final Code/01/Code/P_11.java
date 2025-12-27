/*
(Catching Exceptions Using Class Exception) Write a program that demonstrates how 
various exceptions are caught with catch (Exception exception ) This time, define classes 
ExceptionA (which inherits from class Exception) and ExceptionB (which inherits from 
class ExceptionA). In your program, create try blocks that throw exceptions of types 
ExceptionA, ExceptionB, NullPointerException and IOException. All exceptions should 
be caught with catch blocks specifying type Exception.
*/

import java.io.IOException;

class ExceptionA_11 extends Exception{
    ExceptionA_11(String messageA_11){
        super(messageA_11);
    }
}
class ExceptionB_11 extends ExceptionA_11{
    ExceptionB_11(String messageB_11){
        super(messageB_11);
    }
}


public class P_11 {
    public static void main(String[] args) {
        // throw ExcetionA
        try{
            throw new ExceptionA_11("This is ExceptionA.");
        } catch(Exception e){
            System.out.println("Caught by ExceptionA catch block: " + e.getMessage());
        }

        // throw ExcetionB
        try{
            throw new ExceptionB_11("This is ExceptionB.");
        } catch(Exception e){
            System.out.println("Caught by ExceptionA catch block: " + e.getMessage());
        }

        // throw NullPointerException
        try{
            throw new NullPointerException("This is NullPointerException.");
        } catch(Exception e){
            System.out.println("Caught by ExceptionA catch block: " + e.getMessage());
        }

        // throw IOException
        try{
            throw new IOException("This is IOException.");
        } catch(Exception e){
            System.out.println("Caught by ExceptionA catch block: " + e.getMessage());
        }
    }
}
