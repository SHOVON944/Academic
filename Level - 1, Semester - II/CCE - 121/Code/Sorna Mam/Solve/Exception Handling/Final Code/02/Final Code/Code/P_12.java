/*
12. Library Management System 
• Create a class Book with fields like title, author, and isAvailable. 
• Implement a method borrowBook() which throws a custom exception 
  BookNotAvailableException if the book is already borrowed. 
• Test it by creating multiple books and simulating borrowing/returning. 
*/

import java.util.*;

class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String message) {
        super(message);
    }
}

class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public void borrowBook() throws BookNotAvailableException {
        if (!isAvailable) {
            throw new BookNotAvailableException("The book \"" + title + "\" is already borrowed.");
        }
        isAvailable = false;
        System.out.println("You borrowed \"" + title + "\" by " + author + ".");
    }

    public void returnBook() {
        if (isAvailable) {
            System.out.println("ℹ The book \"" + title + "\" was not borrowed.");
        } else {
            isAvailable = true;
            System.out.println("You returned \"" + title + "\".");
        }
    }

    public void showInfo(int index) {
        System.out.println(index + ". " + title + " by " + author + " | Available: " + isAvailable);
    }
}

public class P_12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Java Programming", "James Gosling"));
        books.add(new Book("Data Structures", "Mark Allen Weiss"));
        books.add(new Book("Clean Code", "Robert C. Martin"));
        books.add(new Book("Artificial Intelligence", "Stuart Russell"));
        books.add(new Book("Algorithms", "Thomas H. Cormen"));

        while (true) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Show all books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 1) {
                System.out.println("\nAvailable Books:");
                for (int i = 0; i < books.size(); i++) {
                    books.get(i).showInfo(i + 1);
                }

            } else if (choice == 2) {
                System.out.print("Enter book number to borrow: ");
                int borrowIndex = sc.nextInt() - 1;
                if (borrowIndex >= 0 && borrowIndex < books.size()) {
                    try {
                        books.get(borrowIndex).borrowBook();
                    } catch (BookNotAvailableException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Invalid book number!");
                }

            } else if (choice == 3) {
                System.out.print("Enter book number to return: ");
                int returnIndex = sc.nextInt() - 1;
                if (returnIndex >= 0 && returnIndex < books.size()) {
                    books.get(returnIndex).returnBook();
                } else {
                    System.out.println("Invalid book number!");
                }

            } else if (choice == 4) {
                System.out.println("Exiting Library System. Goodbye!");
                break;

            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }

        sc.close();
    }
}
