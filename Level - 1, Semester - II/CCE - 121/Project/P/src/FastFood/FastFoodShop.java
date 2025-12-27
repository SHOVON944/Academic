package FastFood;

import cart.Cart;
import mainapp.Shop;
import java.util.List;

public class FastFoodShop extends Shop<FastFoodItem> {
    private FastFoodMenu menu;

    public FastFoodShop(Cart cart) {
        super(cart);
        this.menu = new FastFoodMenu();
    }

    @Override
    protected String getShopName() {
        return "Fast Food";
    }

    @Override
    protected List<FastFoodItem> getMenuItems() {
        return menu.getMenuItems();
    }

    @Override
    protected String getShopColor() {
        return "yellow";
    }
}
