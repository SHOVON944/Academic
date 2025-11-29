public class Example3 {
    int a;
    int b;
    Example3(){
        this(10, 20+10);
    }
    Example3(int a, int b){
        this.a = a;
        this.b = b;
    }
    void display(){
        System.out.println("a = " + this.a + ", b =  " + this.b);
    }
    public static void main(String[] args) {
        Example3 obj3 = new  Example3();
        obj3.display();
    }
}
