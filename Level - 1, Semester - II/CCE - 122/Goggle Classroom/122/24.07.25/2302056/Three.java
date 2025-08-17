class Book {
    String title;
    String author;
    double price;

    Book(String t, String a, double p) {
        title = t;
        author = a;
        price = p;
    }

    void display() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: $" + price);
    }
}

class Three {
    public static void main(String[] args) {
        Book b = new Book("Java Basics", "SI.SHOVON", 300.00);
        b.display();
    }
}