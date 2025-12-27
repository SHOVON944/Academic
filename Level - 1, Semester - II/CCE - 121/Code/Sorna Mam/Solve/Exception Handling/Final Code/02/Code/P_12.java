// File: LibraryManagement.java
import java.util.*;

// Custom Exception
class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String message) {
        super(message);
    }
}

// Book class
class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true; // new book is available by default
    }

    public void borrowBook() throws BookNotAvailableException {
        if (!isAvailable) {
            throw new BookNotAvailableException("The book \"" + title + "\" is already borrowed.");
        }
        isAvailable = false;
        System.out.println("You have successfully borrowed: " + title);
    }

    public void returnBook() {
        if (isAvailable) {
            System.out.println("The book \"" + title + "\" was not borrowed.");
        } else {
            isAvailable = true;
            System.out.println("You have returned: " + title);
        }
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public String toString() {
        return title + " by " + author + (isAvailable ? " (Available)" : " (Borrowed)");
    }
}

// Main Class
public class P_12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Creating some books
        List<Book> library = new ArrayList<>();
        library.add(new Book("Java Basics", "James Gosling"));
        library.add(new Book("Effective Java", "Joshua Bloch"));
        library.add(new Book("Clean Code", "Robert C. Martin"));

        int choice;
        do {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Show all books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Books:");
                    for (int i = 0; i < library.size(); i++) {
                        System.out.println((i + 1) + ". " + library.get(i));
                    }
                    break;

                case 2:
                    System.out.print("Enter book number to borrow: ");
                    int borrowIndex = sc.nextInt() - 1;
                    sc.nextLine();
                    try {
                        library.get(borrowIndex).borrowBook();
                    } catch (BookNotAvailableException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid book number.");
                    }
                    break;

                case 3:
                    System.out.print("Enter book number to return: ");
                    int returnIndex = sc.nextInt() - 1;
                    sc.nextLine();
                    try {
                        library.get(returnIndex).returnBook();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid book number.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}
