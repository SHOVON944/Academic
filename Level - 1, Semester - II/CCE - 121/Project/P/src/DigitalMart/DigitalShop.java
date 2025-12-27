package DigitalMart;

import cart.Cart;
import mainapp.Shop;
import java.util.List;

public class DigitalShop extends Shop<DigitalItem> {
    private DigitalMenu menu;

    public DigitalShop(Cart cart) {
        super(cart);
        this.menu = new DigitalMenu();
    }

    @Override
    protected String getShopName() {
        return "Digital Mart";
    }

    @Override
    protected List<DigitalItem> getMenuItems() {
        return menu.getMenuItems();
    }

    @Override
    protected String getShopColor() {
        return "blue";
    }
}
