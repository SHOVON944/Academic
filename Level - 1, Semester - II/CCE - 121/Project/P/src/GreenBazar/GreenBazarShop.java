package GreenBazar;

import cart.Cart;
import mainapp.Shop;
import java.util.List;

public class GreenBazarShop extends Shop<GreenBazarItem> {
    private GreenBazarMenu menu;

    public GreenBazarShop(Cart cart) {
        super(cart);
        this.menu = new GreenBazarMenu();
    }

    @Override
    protected String getShopName() {
        return "Green Bazar";
    }

    @Override
    protected List<GreenBazarItem> getMenuItems() {
        return menu.getMenuItems();
    }

    @Override
    protected String getShopColor() {
        return "green";
    }
}
