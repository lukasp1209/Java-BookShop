package onlineshop.controllers;

import onlineshop.Cart;
import onlineshop.Customer;
import onlineshop.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    Shop shop;

    @Autowired
    Cart cart;

    @Autowired
    Customer customer;

    @PostMapping(value = "/place")
    public String placeOrder() {
        // TODO: place a new order
        // add it to the current customer's orders
        return "order";
    }

    @PostMapping(value = "/{orderNumber}")
    public String placeOrder(@PathVariable(name="orderNumber") Integer orderNumber) {
        // TODO: show details of order with {orderNumber}
        return "order";
    }

    @GetMapping(value = "/all")
    public String listOrders() {
        // TODO: list all orders of the current customer
        return "order-list";
    }
}
