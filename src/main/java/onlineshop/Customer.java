package onlineshop;

import onlineshop.enums.Gender;
import onlineshop.order.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Customer {
    /** Generates a new customer number for each customer */
    private static Integer customerCounter = 1;
    /** Converts the date string into a {@link Date} */
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yyyy");

    protected int customerNo;
    protected String firstname;
    protected String surname;
    protected Gender gender;
    protected LocalDate birthDate;
    protected Cart cart;
    protected List<Order> orders = new ArrayList<>();

    public Customer() {
        this.customerNo = customerCounter++;
    }

    public Customer(String firstname, String surname, Gender gender, String birthDate, Cart cart) {
        this();
        this.firstname = firstname;
        this.surname = surname;
        this.gender = gender;
        this.birthDate = LocalDate.parse(birthDate, formatter);
        this.cart = cart;
    }

    public void addOrder(Order order) {
        orders.add(order);

        // TODO: create a CSV for the order
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Order getOrderByOrderNumber(Integer orderNo) {
        for(Order order : orders) {
            if(order.getOrderNo() == orderNo) {
                return order;
            }
        }
        return null;
    }
}
