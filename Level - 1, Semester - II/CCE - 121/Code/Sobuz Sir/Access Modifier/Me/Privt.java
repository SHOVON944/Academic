class Name{
    private String name;
    public String getName(){
        return name;
    }
    public void setName(String s){
        name = s;
    }
}

public class Privt {
    public static void main(String[] args) {
        Name student = new Name();
        student.setName("SHOVON");
        System.out.println("Name is: " + student.getName());
    }
}
