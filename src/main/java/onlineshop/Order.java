package onlineshop;

import onlineshop.merchandise.OrderItem;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Order {
    private List<OrderItem> items = new ArrayList<>();
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String notes;
    private Billing billing;
    // calculated in backend
    private double total;


    public void addItem(OrderItem item) {
        // TODO: add item to items
    }
    public boolean removeItem(OrderItem item) {
        // TODO: remove item from items
        // return 'true' if successful
        return false;
    }

    public boolean placeOrder() {
        // TODO: place a new order
        // return 'true' if successful
        return false;
    }

    // TODO: generate Getters/Setters
}
