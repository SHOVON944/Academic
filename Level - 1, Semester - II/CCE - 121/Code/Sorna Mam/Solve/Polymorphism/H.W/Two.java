import java.util.Scanner;

class SuperClass {
    protected int data1;

    public void setData1(int data1) {
        this.data1 = data1;
    }

    public int getData1() {
        return data1;
    }
}

class SubClass extends SuperClass {
    private int data2;

    public SubClass(int data1, int data2) {
        this.data1 = data1; // super(data1);
        this.data2 = data2;
    }

    public void setData2(int data2) {
        this.data2 = data2;
    }

    public int getData2() {
        return data2;
    }

    public String checkCondition() {
        if (data1 == 10 && data2 == 15) {
            return "Condition True!";
        } else {
            return "Condition False!";
        }
    }
}

public class Two {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Data1: ");
        int d1 = scan.nextInt();
        System.out.print("Enter Data2: ");
        int d2 = scan.nextInt();
        SubClass obj = new SubClass(d1, d2);

        System.out.println("Data1: " + obj.getData1());
        System.out.println("Data2: " + obj.getData2());
        System.out.println(obj.checkCondition());

        System.out.print("Enter Data2(For update)");
        int d1Up = scan.nextInt();
        obj.setData2(d1Up);
        System.out.println("Updated Data2: " + obj.getData2());
        System.out.println(obj.checkCondition());

        scan.close();
    }
}
