class Shape{
    void show(){
        System.out.println("This is Shape class.");
    }
    void getInfo(){
        System.out.println("Class name: Shape.");
    }
}
class Circle extends Shape{
    void show(){
        System.out.println("This is a Circle.");
    }
}
class Rectangle extends Shape{
    void show(){
        System.out.println("This is a Rectangle.");
    }
}


public class One {
    public static void main(String[] args) {
        System.out.println();

        Shape obj = new Shape();
        Shape obj1 = new Circle();
        Shape obj2 = new Circle();

        obj.show();
        obj.getInfo();

        System.out.println();

        obj1.show();
        obj1.getInfo();

        System.out.println();

        obj2.show();
        obj2.getInfo();
    }
}
