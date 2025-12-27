class Variables {
    final int SPEED_LIMIT=90;    //final variable
    void run() {
        //SPEED_LIMIT=400;   //we cannot change the final variable
        System.out.println(SPEED_LIMIT);
    }
    public static void main(String args[]) {
        Variables obj=new Variables();
        obj.run();
    }
}