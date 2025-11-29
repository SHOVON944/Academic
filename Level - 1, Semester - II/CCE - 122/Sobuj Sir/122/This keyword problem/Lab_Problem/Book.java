import java.util.Scanner;

public class Book {
    String title;

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public void display() {
        System.out.println("Book name: " + title);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Book Name: ");
        String userTitle = scan.nextLine();

        Book b1 = new Book();
        b1.setTitle(userTitle).display();

        scan.close();
    }
}
