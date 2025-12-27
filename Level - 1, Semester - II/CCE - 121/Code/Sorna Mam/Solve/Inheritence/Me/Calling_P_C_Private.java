private class parentClass{ 
    public void parentMethod(){ 
        System.out.println("This is parent Class"); 
    } 
} 
class childClass extends parentClass{ 
    public void childMethod() { 
        System.out.println("This is child Class"); 
    } 
}     
class callingPrivateClass{ 
public static void main(String[] args) { 
parentClass objParent = new parentClass(); 
childClass objChild = new childClass(); 
objParent.parentMethod(); 
objChild.childMethod(); 
objChild.parentMethod(); 
} 
}