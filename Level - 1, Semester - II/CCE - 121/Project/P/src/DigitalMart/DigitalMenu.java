package DigitalMart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DigitalMenu {
    private List<DigitalItem> menuItems;

    public DigitalMenu() {
        menuItems = new ArrayList<>();
        populateMenu();
    }

    private void populateMenu() {
        Random random = new Random();

        // Electronics & Gadgets
        menuItems.add(new DigitalItem("Smartphone", 20000 + random.nextInt(10000)));
        menuItems.add(new DigitalItem("Laptop", 50000 + random.nextInt(20000)));
        menuItems.add(new DigitalItem("Tablet", 15000 + random.nextInt(8000)));
        menuItems.add(new DigitalItem("Smartwatch", 5000 + random.nextInt(3000)));

        // Home Appliances
        menuItems.add(new DigitalItem("Mixer", 2500 + random.nextInt(1500)));
        menuItems.add(new DigitalItem("Juicer", 3000 + random.nextInt(2000)));
        menuItems.add(new DigitalItem("Blender", 2000 + random.nextInt(1000)));
        menuItems.add(new DigitalItem("Vacuum Cleaner", 4000 + random.nextInt(2000)));

        // Fashion & Clothing
        menuItems.add(new DigitalItem("Men's T-Shirt", 500 + random.nextInt(300)));
        menuItems.add(new DigitalItem("Women's Dress", 800 + random.nextInt(500)));
        menuItems.add(new DigitalItem("Kids T-Shirt", 400 + random.nextInt(200)));

        // Health, Beauty & Personal Care
        menuItems.add(new DigitalItem("Face Wash", 250 + random.nextInt(100)));
        menuItems.add(new DigitalItem("Shampoo", 300 + random.nextInt(100)));

        // Food, Groceries & Beverages
        menuItems.add(new DigitalItem("Rice", 70 + random.nextInt(30)));
        menuItems.add(new DigitalItem("Sugar", 40 + random.nextInt(20)));
        menuItems.add(new DigitalItem("Tea", 60 + random.nextInt(20)));

        // Baby Products
        menuItems.add(new DigitalItem("Baby Diapers", 300 + random.nextInt(150)));
        menuItems.add(new DigitalItem("Baby Milk Powder", 500 + random.nextInt(200)));

        // Sports & Outdoor
        menuItems.add(new DigitalItem("Dumbbells", 1500 + random.nextInt(500)));
        menuItems.add(new DigitalItem("Yoga Mat", 1000 + random.nextInt(500)));

        // Toys, Kids & Hobbies
        menuItems.add(new DigitalItem("Lego Set", 2000 + random.nextInt(1000)));
        menuItems.add(new DigitalItem("Puzzle", 500 + random.nextInt(200)));

        // Home & Living
        menuItems.add(new DigitalItem("Bed", 8000 + random.nextInt(4000)));
        menuItems.add(new DigitalItem("Chair", 2000 + random.nextInt(1000)));

        // Stationery & Office Supplies
        menuItems.add(new DigitalItem("Notebook", 100 + random.nextInt(50)));
        menuItems.add(new DigitalItem("Pen", 50 + random.nextInt(30)));

        // Books & Media
        menuItems.add(new DigitalItem("Academic Book", 300 + random.nextInt(100)));
        menuItems.add(new DigitalItem("Novel", 200 + random.nextInt(100)));

        // Automotive & Tools
        menuItems.add(new DigitalItem("Car Charger", 500 + random.nextInt(200)));
        menuItems.add(new DigitalItem("Helmet", 1000 + random.nextInt(500)));

        // Gadgets & Smart Home
        menuItems.add(new DigitalItem("Smart Light", 1500 + random.nextInt(500)));
        menuItems.add(new DigitalItem("Wi-Fi Router", 2000 + random.nextInt(1000)));
    }

    public List<DigitalItem> getMenuItems() {
        return menuItems;
    }
}
