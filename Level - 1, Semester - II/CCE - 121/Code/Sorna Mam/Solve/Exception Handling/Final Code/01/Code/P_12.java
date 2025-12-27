/*
            --- Must check Programe end---                

(Catching Exceptions Using Class Exception) Write a program that demonstrates how 
various exceptions are caught with catch (Exception exception ) This time, define classes 
ExceptionA (which inherits from class Exception) and ExceptionB (which inherits from 
class ExceptionA). In your program, create try blocks that throw exceptions of types 
ExceptionA, ExceptionB, NullPointerException and IOException. All exceptions should 
be caught with catch blocks specifying type Exception.
*/

class ExceptionA_12 extends Exception{
    ExceptionA_12(String messageA_12){
        super(messageA_12);
    }
}
class ExceptionB_12 extends ExceptionA_12{
    ExceptionB_12(String messageB_12){
        super(messageB_12);
    }
}


public class P_12 {
    public static void main(String[] args) {

        try{
            throw new ExceptionB_12("This is ExceptionB.");
        } catch(ExceptionB_12 e){
            System.out.println("Caught ExceptionB: " + e.getMessage());
        } catch(ExceptionA_12 e){
            System.out.println("Caught ExceptionA: " + e.getMessage());
        } catch(Exception e){
            System.out.println("Caught Exception: " + e.getMessage());
        }
    }
}
/*
keno nicer catch block e yellow error?
এখানে ExceptionB_12 হলো ExceptionA_12-এর subclass।
কিন্তু যেহেতু ExceptionB_12 আগেই ধরা হচ্ছে, compiler বুঝে নিচ্ছে:
যদি কোনো ExceptionB_12 থ্রো হয়, সেটা প্রথম ক্যাচ ব্লকেই ধরা পড়বে।
তাই এর পরে লিখা catch(ExceptionA_12 e) ব্লক আংশিকভাবে redundant মনে হচ্ছে compiler-এর কাছে।
*/

/*
❌ ভুল উদাহরণ (superclass আগে রাখলে → compile-time error)
try {
            throw new ExceptionB("This is ExceptionB");
        } 
        // ❌ ভুল: ExceptionA আগে
        catch (ExceptionA e) {
            System.out.println("Caught ExceptionA: " + e.getMessage());
        } 
        // ❌ এই ব্লক কখনো execute হবে না → compiler error: "unreachable catch block"
        catch (ExceptionB e) {
            System.out.println("Caught ExceptionB: " + e.getMessage());
        }
*/