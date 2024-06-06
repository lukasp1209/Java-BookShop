package onlineshop.controllers;

import onlineshop.*;
import onlineshop.merchandise.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

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
        Order order = new Order(newOrder, cart.getItems(), cart.getTotalPrice());
        customer.addOrder(order);
        int orderNumber = order.getOrderNo();
        return "redirect:/order/" + orderNumber;
    }

    @GetMapping(value = "/{orderNumber}")
    public String showOrder(Model view, @PathVariable(name = "orderNumber") Integer orderNumber) {
        Order order = customer.getOrder(orderNumber);

        if (order == null) {
            return "redirect:/order/all";
        }

        view.addAttribute("order", order);
        view.addAttribute("orderItems", order.getItems());
        view.addAttribute("total", order.getTotal());
        view.addAttribute("message", "Bestellung mit der Nummer \"" + orderNumber + "\" ist eingegangen.");

        return "order";
    }

    @GetMapping(value = "/all")
    public String listOrders(Model view) {
        List<Order> orders = customer.getOrders();

        view.addAttribute("orders", orders);

        double grandTotal = orders.stream().mapToDouble(Order::getTotal).sum();
        view.addAttribute("grandTotal", grandTotal);

        return "order-list";
    }
}
