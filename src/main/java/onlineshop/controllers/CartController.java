package onlineshop.controllers;

import onlineshop.Cart;
import onlineshop.Shop;
import onlineshop.merchandise.Article;
import onlineshop.merchandise.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
    public static final String MESSAGE = "message";
    public static final String SHOW_MESSAGE = "showMessage";

    @Autowired
    Shop shop;

    @Autowired
    Cart cart;

    @GetMapping(value = {"/add/{id}"})
    public String addToCart(@PathVariable(name = "id") Integer id, RedirectAttributes atts) {
        String message = "Car with article no. \"" + id + "\" not found.";
        Car car = shop.getArticleByNumber(id);
        if (car != null) {
            cart.addArticle(car);
            message = "Article \"" + car.getModel() + "\" added to cart.";
        }
        atts.addFlashAttribute(MESSAGE, message);
        atts.addFlashAttribute(SHOW_MESSAGE, true);
        return "redirect:/index.html";
    }

    @GetMapping(value = {"/increase/{id}"})
    public String increaseQuantity(@PathVariable(name = "id") Integer id, RedirectAttributes atts) {
        String message = "Car with article no. \"" + id + "\" not found.";
        Car car = shop.getArticleByNumber(id);
        if (car != null) {
            cart.increaseQuantity(car.getId());
        } else {
            atts.addFlashAttribute(MESSAGE, message);
            atts.addFlashAttribute(SHOW_MESSAGE, true);
        }
        return "redirect:/cart.html";
    }

    @GetMapping(value = {"/decrease/{id}"})
    public String decreaseQuantity(@PathVariable(name = "id") Integer id, RedirectAttributes atts) {
        Car article = shop.getArticleByNumber(id);
        if (!cart.decreaseQuantity(id)) {
            atts.addFlashAttribute(MESSAGE, "Article \"" + article.getModel() + "\" removed from cart.");
            atts.addFlashAttribute(SHOW_MESSAGE, true);
        }
        return "redirect:/cart.html";
    }

    @GetMapping(value = {"/remove/{id}"})
    public String removeFromCart(@PathVariable(name = "id") Integer id, RedirectAttributes atts) {
        String message = "Article with article no. \"" + id + "\" not found in cart.";
        Car article = shop.getArticleByNumber(id);
        if (article != null && cart.removeArticle(id)) {
            message = "Article \"" + article.getModel() + "\" removed from cart.";
        }
        atts.addFlashAttribute(MESSAGE, message);
        atts.addFlashAttribute(SHOW_MESSAGE, true);
        return "redirect:/cart.html";
    }
}
