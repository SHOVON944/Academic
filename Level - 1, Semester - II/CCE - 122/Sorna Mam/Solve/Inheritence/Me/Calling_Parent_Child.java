class parentClass{
    public void parentMethod(){
        System.out.println("This is parent class.");
    }
}

class childClass extends parentClass{
    public void childMethod(){
        System.out.println("This is child class.");
    }
}


public class Calling_Parent_Child {
    public static void main(String[] args) {
        parentClass pObj = new parentClass();
        childClass cObj = new childClass();
        pObj.parentMethod();
        cObj.childMethod();
        cObj.parentMethod();
    }
}
