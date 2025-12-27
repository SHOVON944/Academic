public class StaticBlank {
    static final int speed;
    static {
        speed = 50;
    }
    void car(){
        System.out.println(speed);
    }

    public static void main(String[] args) {
        new StaticBlank().car();
    }
}
