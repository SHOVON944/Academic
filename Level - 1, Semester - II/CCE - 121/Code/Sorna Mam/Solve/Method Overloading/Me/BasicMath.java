class Adding{
    static int add(int a, int b){
        return a+b;
    }
    static double add(double a, double b, double c){
        return a+b+c;
    }
}

    public class BasicMath{
    public static void main(String[] args) {
        System.out.println(Adding.add(11, 11));
        System.out.println(Adding.add(4.4, 3.4, -3.3));
    }
}
