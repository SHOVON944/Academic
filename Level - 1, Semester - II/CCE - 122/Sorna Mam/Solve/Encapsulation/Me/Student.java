package Me;

public class Student {
    // private variable: বাইরের ক্লাস থেকে সরাসরি access করা যাবে না
    private String name;

    // public setter method: নাম সেট করার জন্য
    public void setName(String newName) {
        name = newName;
    }

    // public getter method: নাম রিটার্ন করার জন্য
    public String getName() {
        return name;
    }
}

// A Java class to test the encapsulated class.
class Main {
    public static void main(String[] args) {
        // encapsulated class এর object তৈরি
        Student s = new Student();

        // নাম সেট করা হচ্ছে
        s.setName("vijay");

        // নাম প্রিন্ট করা হচ্ছে
        System.out.println(s.getName());
    }
}
