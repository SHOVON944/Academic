public class BasicConcatination {
    public String concat(String str1, String str2){
        return str1+str2;
    }
    public String concat(String str1, String str2, String str3){
        return str1+str2+str3;
    }

    public static void main(String[] args) {
        BasicConcatination obj = new BasicConcatination();
        String s1 = obj.concat("Clash ", "Royal");
        String s2 = obj.concat("Clash ", "of ", "Clans");

        System.out.println("The Sum of 2 String is: " + s1);
        System.out.println("The Sum of 3 String is: " + s2);
    }
}
