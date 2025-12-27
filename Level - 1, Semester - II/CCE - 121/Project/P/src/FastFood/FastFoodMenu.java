package FastFood;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FastFoodMenu {
    private List<FastFoodItem> menuItems;

    public FastFoodMenu() {
        menuItems = new ArrayList<>();
        populateMenu();
    }

    private void populateMenu() {
        Random random = new Random();

        // Burgers
        menuItems.add(new FastFoodItem("Classic Cheeseburger", 100 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Double Cheeseburger", 150 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Chicken Burger (Grilled)", 120 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Chicken Burger (Crispy)", 120 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Fish Burger", 130 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Veggie Burger", 90 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Bacon Burger", 150 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Spicy Burger", 140 + random.nextInt(50)));

        // Sandwiches / Wraps
        menuItems.add(new FastFoodItem("Chicken Sandwich (Grilled)", 120 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Chicken Sandwich (Fried)", 120 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Veggie Sandwich", 100 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Tuna Sandwich", 110 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Turkey Sandwich", 130 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Club Sandwich", 140 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Wraps (Chicken)", 150 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Wraps (Veggie)", 120 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Wraps (Beef)", 160 + random.nextInt(50)));

        // Fries & Sides
        menuItems.add(new FastFoodItem("French Fries (Regular)", 50 + random.nextInt(30)));
        menuItems.add(new FastFoodItem("French Fries (Large)", 80 + random.nextInt(30)));
        menuItems.add(new FastFoodItem("Sweet Potato Fries", 70 + random.nextInt(30)));
        menuItems.add(new FastFoodItem("Onion Rings", 60 + random.nextInt(30)));
        menuItems.add(new FastFoodItem("Cheese Fries", 90 + random.nextInt(30)));
        menuItems.add(new FastFoodItem("Potato Wedges", 60 + random.nextInt(30)));
        menuItems.add(new FastFoodItem("Mozzarella Sticks", 80 + random.nextInt(30)));
        menuItems.add(new FastFoodItem("Jalapeno Poppers", 70 + random.nextInt(30)));
        menuItems.add(new FastFoodItem("Chicken Nuggets (6 pcs)", 100 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Chicken Nuggets (12 pcs)", 180 + random.nextInt(50)));

        // Pizza
        menuItems.add(new FastFoodItem("Margherita Pizza", 250 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Pepperoni Pizza", 300 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Veggie Pizza", 280 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("BBQ Chicken Pizza", 320 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Hawaiian Pizza", 300 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Four Cheese Pizza", 350 + random.nextInt(50)));
        menuItems.add(new FastFoodItem("Meat Lovers Pizza", 400 + random.nextInt(50)));

        // Beverages
        menuItems.add(new FastFoodItem("Soft Drink (Coke)", 30 + random.nextInt(20)));
        menuItems.add(new FastFoodItem("Soft Drink (Pepsi)", 30 + random.nextInt(20)));
        menuItems.add(new FastFoodItem("Soft Drink (Sprite)", 30 + random.nextInt(20)));
        menuItems.add(new FastFoodItem("Soft Drink (Fanta)", 30 + random.nextInt(20)));
        menuItems.add(new FastFoodItem("Iced Tea / Lemonade", 40 + random.nextInt(20)));
        menuItems.add(new FastFoodItem("Coffee (Hot / Iced)", 50 + random.nextInt(20)));
        menuItems.add(new FastFoodItem("Milkshake (Chocolate)", 60 + random.nextInt(20)));
        menuItems.add(new FastFoodItem("Milkshake (Vanilla)", 60 + random.nextInt(20)));
        menuItems.add(new FastFoodItem("Milkshake (Strawberry)", 60 + random.nextInt(20)));
        menuItems.add(new FastFoodItem("Milkshake (Oreo)", 70 + random.nextInt(20)));
        menuItems.add(new FastFoodItem("Juice (Orange)", 40 + random.nextInt(20)));
        menuItems.add(new FastFoodItem("Juice (Apple)", 40 + random.nextInt(20)));
        menuItems.add(new FastFoodItem("Juice (Mango)", 50 + random.nextInt(20)));
    }

    public List<FastFoodItem> getMenuItems() {
        return menuItems;
    }
}
