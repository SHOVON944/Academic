package GreenBazar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GreenBazarMenu {
    private List<GreenBazarItem> menuItems;

    public GreenBazarMenu() {
        menuItems = new ArrayList<>();
        populateMenu();
    }

    private void populateMenu() {
        Random random = new Random();

        // Rice, Lentils & Cooking Items
        menuItems.add(new GreenBazarItem("Aus Rice", 80 + random.nextInt(20)));
        menuItems.add(new GreenBazarItem("Super Basmati Rice", 120 + random.nextInt(30)));
        menuItems.add(new GreenBazarItem("Local Rice", 70 + random.nextInt(20)));
        menuItems.add(new GreenBazarItem("Masoor Dal", 100 + random.nextInt(30)));
        menuItems.add(new GreenBazarItem("Chhola Dal", 110 + random.nextInt(30)));
        menuItems.add(new GreenBazarItem("Mung Dal", 90 + random.nextInt(30)));
        menuItems.add(new GreenBazarItem("Red Lentil", 100 + random.nextInt(30)));
        menuItems.add(new GreenBazarItem("Butter Dal", 120 + random.nextInt(30)));

        // Flour & Others
        menuItems.add(new GreenBazarItem("Wheat Flour", 60 + random.nextInt(20)));
        menuItems.add(new GreenBazarItem("Maida", 50 + random.nextInt(20)));
        menuItems.add(new GreenBazarItem("Flattened Rice (Chire)", 40 + random.nextInt(20)));
        menuItems.add(new GreenBazarItem("Semai", 35 + random.nextInt(15)));
        menuItems.add(new GreenBazarItem("Bread", 30 + random.nextInt(10)));

        // Cooking Oils
        menuItems.add(new GreenBazarItem("Mustard Oil", 150 + random.nextInt(50)));
        menuItems.add(new GreenBazarItem("Soybean Oil", 140 + random.nextInt(50)));
        menuItems.add(new GreenBazarItem("Edible Oil", 130 + random.nextInt(40)));

        // Spices
        menuItems.add(new GreenBazarItem("Salt", 10 + random.nextInt(5)));
        menuItems.add(new GreenBazarItem("Sugar", 20 + random.nextInt(10)));
        menuItems.add(new GreenBazarItem("Vinegar", 30 + random.nextInt(10)));
        menuItems.add(new GreenBazarItem("Turmeric", 25 + random.nextInt(10)));
        menuItems.add(new GreenBazarItem("Red Chili Powder", 30 + random.nextInt(10)));
        menuItems.add(new GreenBazarItem("Coriander Powder", 40 + random.nextInt(15)));
        menuItems.add(new GreenBazarItem("Cumin", 35 + random.nextInt(15)));
        menuItems.add(new GreenBazarItem("Cinnamon", 50 + random.nextInt(20)));
        menuItems.add(new GreenBazarItem("Cardamom", 60 + random.nextInt(25)));
        menuItems.add(new GreenBazarItem("Cloves", 55 + random.nextInt(20)));

        // Vegetables & Fruits
        menuItems.add(new GreenBazarItem("Potato", 25 + random.nextInt(10)));
        menuItems.add(new GreenBazarItem("Onion", 20 + random.nextInt(10)));
        menuItems.add(new GreenBazarItem("Tomato", 30 + random.nextInt(10)));
        menuItems.add(new GreenBazarItem("Banana", 15 + random.nextInt(5)));
        menuItems.add(new GreenBazarItem("Apple", 50 + random.nextInt(15)));
        menuItems.add(new GreenBazarItem("Mango", 70 + random.nextInt(20)));
        menuItems.add(new GreenBazarItem("Orange", 40 + random.nextInt(15)));
        menuItems.add(new GreenBazarItem("Garlic", 20 + random.nextInt(10)));
        menuItems.add(new GreenBazarItem("Chili", 15 + random.nextInt(10)));
        menuItems.add(new GreenBazarItem("Eggplant", 30 + random.nextInt(10)));
        menuItems.add(new GreenBazarItem("Cauliflower", 40 + random.nextInt(15)));
        menuItems.add(new GreenBazarItem("Cabbage", 35 + random.nextInt(15)));
        menuItems.add(new GreenBazarItem("Carrot", 40 + random.nextInt(15)));
        menuItems.add(new GreenBazarItem("Spinach", 20 + random.nextInt(10)));
        menuItems.add(new GreenBazarItem("Watermelon", 100 + random.nextInt(50)));
        menuItems.add(new GreenBazarItem("Papaya", 80 + random.nextInt(30)));
        menuItems.add(new GreenBazarItem("Grapes", 120 + random.nextInt(40)));
    }

    public List<GreenBazarItem> getMenuItems() {
        return menuItems;
    }
}
