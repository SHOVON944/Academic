import java.util.Scanner;

// Pizza class
class Pizza {
    private String size;
    private int cheeseToppings;
    private int pepperoniToppings;
    private int hamToppings;

    public Pizza(String size, int cheeseToppings, int pepperoniToppings, int hamToppings) {
        this.size = size.toLowerCase();
        this.cheeseToppings = cheeseToppings;
        this.pepperoniToppings = pepperoniToppings;
        this.hamToppings = hamToppings;
    }

    public double calcCost() {
        double basePrice = 0;
        if (size.equals("small")) basePrice = 10;
        else if (size.equals("medium")) basePrice = 12;
        else if (size.equals("large")) basePrice = 14;

        int totalToppings = cheeseToppings + pepperoniToppings + hamToppings;
        return basePrice + (totalToppings * 2);
    }

    public String getDescription() {
        return "\n------------------------" +
               "\n Pizza Details" +
               "\n------------------------" +
               "\nSize       : " + size +
               "\nCheese     : " + cheeseToppings +
               "\nPepperoni  : " + pepperoniToppings +
               "\nHam        : " + hamToppings +
               "\nCost       : $" + calcCost() +
               "\n------------------------";
    }
}

class PizzaOrder {
    private Pizza pizza1;
    private Pizza pizza2;
    private Pizza pizza3;

    public void setPizza1(Pizza pizza) { this.pizza1 = pizza; }
    public void setPizza2(Pizza pizza) { this.pizza2 = pizza; }
    public void setPizza3(Pizza pizza) { this.pizza3 = pizza; }

    public double calcTotal() {
        double total = 0;
        if (pizza1 != null) total += pizza1.calcCost();
        if (pizza2 != null) total += pizza2.calcCost();
        if (pizza3 != null) total += pizza3.calcCost();
        return total;
    }
}

public class Three {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PizzaOrder order = new PizzaOrder();

        System.out.print("How many pizzas do you want to order? (1-3): ");
        int count = sc.nextInt();
        sc.nextLine(); // cancel newline

        for (int i = 1; i <= count; i++) {
            System.out.println("\nEnter details for Pizza " + i + ":");
            System.out.print("Size (small/medium/large): ");
            String size = sc.nextLine();

            System.out.print("Number of cheese toppings: ");
            int cheese = sc.nextInt();

            System.out.print("Number of pepperoni toppings: ");
            int pepperoni = sc.nextInt();

            System.out.print("Number of ham toppings: ");
            int ham = sc.nextInt();
            sc.nextLine(); // cancel newline

            Pizza pizza = new Pizza(size, cheese, pepperoni, ham);

            if (i == 1) order.setPizza1(pizza);
            else if (i == 2) order.setPizza2(pizza);
            else if (i == 3) order.setPizza3(pizza);

            System.out.println(pizza.getDescription());
        }

        System.out.println("\nTotal order cost: $" + order.calcTotal());
        sc.close();
    }
}
