class InvokedFirst{
    int speed;  // // Instance variable (each object gets its own copy)

    // Constructor -> First instance block invoked than constructor invoked
    // Called automatically after instance initializer block
    InvokedFirst(){
        System.out.println("constructor is invoked");
    }

    // instance block -> Invoked first than Constructor Invoked
    // Runs before constructor for each new object
    {
        System.out.println("instance initializer block invoked");
    }

    public static void main(String args[]){
        InvokedFirst b1=new InvokedFirst();
        InvokedFirst b2=new InvokedFirst();
    }
}
