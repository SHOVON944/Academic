import java.util.Scanner;

// Pizza class
class Pizza {
    private String size;
    private int cheeseToppings;
    private int pepperoniToppings;
    private int hamToppings;

    // Constructor
    public Pizza(String size, int cheeseToppings, int pepperoniToppings, int hamToppings) {
        this.size = size.toLowerCase();
        this.cheeseToppings = cheeseToppings;
        this.pepperoniToppings = pepperoniToppings;
        this.hamToppings = hamToppings;
    }

    // Getters and Setters
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size.toLowerCase();
    }

    public int getCheeseToppings() {
        return cheeseToppings;
    }

    public void setCheeseToppings(int cheeseToppings) {
        this.cheeseToppings = cheeseToppings;
    }

    public int getPepperoniToppings() {
        return pepperoniToppings;
    }

    public void setPepperoniToppings(int pepperoniToppings) {
        this.pepperoniToppings = pepperoniToppings;
    }

    public int getHamToppings() {
        return hamToppings;
    }

    public void setHamToppings(int hamToppings) {
        this.hamToppings = hamToppings;
    }

    // Calculate cost
    public double calcCost() {
        double basePrice = 0;
        if (size.equals("small"))
            basePrice = 10;
        else if (size.equals("medium"))
            basePrice = 12;
        else if (size.equals("large"))
            basePrice = 14;

        int totalToppings = cheeseToppings + pepperoniToppings + hamToppings;
        return basePrice + (totalToppings * 2);
    }

    // Description
    public String getDescription() {
        return "\n------------------------" +
                "\nPizza Details" +
                "\n------------------------" +
                "\nSize : " + size +
                "\nCheese : " + cheeseToppings +
                "\nPepperoni : " + pepperoniToppings +
                "\nHam : " + hamToppings +
                String.format("\nCost : $%.2f", calcCost()) +
                "\n------------------------";
    }
}

// PizzaOrder class
class PizzaOrder {
    private Pizza pizza1;
    private Pizza pizza2;
    private Pizza pizza3;

    public void setPizza1(Pizza pizza) {
        this.pizza1 = pizza;
    }

    public void setPizza2(Pizza pizza) {
        this.pizza2 = pizza;
    }

    public void setPizza3(Pizza pizza) {
        this.pizza3 = pizza;
    }

    public double calcTotal() {
        double total = 0;
        if (pizza1 != null)
            total += pizza1.calcCost();
        if (pizza2 != null)
            total += pizza2.calcCost();
        if (pizza3 != null)
            total += pizza3.calcCost();
        return total;
    }
}

// Main class
public class Pizzaa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PizzaOrder order = new PizzaOrder();

        System.out.print("How many pizzas do you want to order? (1-3): ");
        int count = sc.nextInt();
        sc.nextLine(); // clear newline

        for (int i = 1; i <= count; i++) {
            System.out.println("\nEnter details for Pizza " + i + ":");

            System.out.print("Size (small/medium/large): ");
            String size = sc.nextLine().trim().toLowerCase(); // assume correct input

            System.out.print("Number of cheese toppings: ");
            int cheese = sc.nextInt();

            System.out.print("Number of pepperoni toppings: ");
            int pepperoni = sc.nextInt();

            System.out.print("Number of ham toppings: ");
            int ham = sc.nextInt();
            sc.nextLine(); // clear newline

            Pizza p = new Pizza(size, cheese, pepperoni, ham);

            if (i == 1)
                order.setPizza1(p);
            else if (i == 2)
                order.setPizza2(p);
            else
                order.setPizza3(p);

            System.out.println(p.getDescription());
        }

        System.out.printf("\nTotal cost of order: $%.2f\n", order.calcTotal());
        sc.close();
    }
}
