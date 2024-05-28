package onlineshop.merchandise;

import onlineshop.Shop;
import org.springframework.beans.BeanUtils;

/**
 * Represents an article/car in the shopping cart
 */
public class CartItem extends Car {
    private int quantity = 0;

    public CartItem() {
    }

    public CartItem(Car car) {
        super();
        BeanUtils.copyProperties(car, this);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getShowQuantity() {
        return quantity > 1;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public void decreaseQuantity() {
        quantity--;
    }

    public double getTotalPrice() {
        return quantity * price;
    }

    public String getTotalPriceFormatted() {
        return Shop.df.format(getTotalPrice());
    }
}
