class Car{
    void speed(final int limit){
        System.out.println(limit);
        //limit = 300;
        //System.out.println(limit);
    }
}

public class Parameter {
    public static void main(String[] args) {
       Car call = new Car();
       call.speed(500);
    }
}
