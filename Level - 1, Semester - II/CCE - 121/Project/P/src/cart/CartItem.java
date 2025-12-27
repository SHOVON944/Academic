package cart;

public class CartItem {
    private String name;
    private double price;
    private String shopName;

    public CartItem(String name, double price, String shopName) {
        this.name = name;
        this.price = price;
        this.shopName = shopName;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getShopName() {
        return shopName;
    }
}
