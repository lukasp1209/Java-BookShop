package onlineshop.controllers;

import onlineshop.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController {
    private final static Logger log = LogManager.getLogger(OrderController.class);

    @Autowired
    Shop shop;

    @Autowired
    Cart cart;

    @Autowired
    Customer customer;

    @PostMapping(value = "/place")
    public String placeOrder(Model view, @ModelAttribute Billing newOrder) {
        // TODO:
        // 1. create a new order using 'newOrder'
        // 2. add this order to customer's order list
        // 3. redirect to '/order/{orderNumber}' and pass new orderId to path variable
        int orderNumber = 1;
        return "redirect:/order/" + orderNumber;
    }

    @GetMapping(value = "/{orderNumber}")
    public String showOrder(Model view, @PathVariable(name="orderNumber") Integer orderNumber) {
        // TODO: show details of order with {orderNumber}
        // 1. get order with 'orderNumber
        getCartItems(view);
        view.addAttribute("orderItems", cart.getItems());
        return "order";
    }

    @GetMapping(value = "/all")
    public String listOrders(Model view) {
        // TODO: list all orders of the current customer
        getCartItems(view);
        return "order-list";
    }
}