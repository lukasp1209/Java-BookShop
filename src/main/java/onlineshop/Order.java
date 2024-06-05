package onlineshop;

import onlineshop.merchandise.Car;
import onlineshop.merchandise.CartItem;
import onlineshop.merchandise.OrderItem;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Order {
    private static int nextOrderNo = 1;
    private Integer orderNo;
    private List<OrderItem> items = new ArrayList<>();
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String notes;
    private Billing billing;
    private double total;

    public Order() {
        this.orderNo = nextOrderNo++;
    }

    public Order(Billing billing, double total) {
        this.orderNo = nextOrderNo++;
        this.billing = billing;
        this.total = total;
    }

    public Order(Billing newOrder, List<CartItem> items, Object totalPrice) {
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public boolean removeItem(OrderItem item) {
        return items.remove(item);
    }

    public boolean placeOrder() {
        // logic for placing order, e.g., save to database
        return true;
    }

    // Getters and Setters
    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
