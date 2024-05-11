package onlineshop.controllers;

import jakarta.servlet.http.HttpSession;
import onlineshop.Cart;
import onlineshop.Shop;
import onlineshop.merchandise.Book;
import onlineshop.merchandise.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ShopController {
    public static final String PAGE_NO = "page";
    public static final String PAGE_SIZE = "size";
    public final static String ARTICLES = "articles";

    @Autowired
    Shop shop;

    @Autowired
    Cart cart;

    @GetMapping(value = {"/"})
    public String root() {
        return "redirect:/index.html";
    }

    @GetMapping(value = {"/index.html"})
    public String homePage(Model model,
                           @RequestParam(name = "page", required = false) Integer page,
                           @RequestParam(name = "size", required = false) Integer size,
                           HttpSession session) {
        page = getSessionParam(session, PAGE_NO, page, 0);
        size = getSessionParam(session, PAGE_SIZE, size, 10);

        int from = page * size;
        int to = from + size;
        List<Book> articles = shop.getArticles().subList(from, to);

        model.addAttribute(ARTICLES, articles);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("noOfArticles", shop.getNumOfArticles());
        getCartItems(model);
        return "index";
    }

    @GetMapping(value = {"/{name}.html"})
    public String htmlMapping(Model model,
                              @PathVariable(name = "name") String name) {
        getCartItems(model);
        return name;
    }

    private void getCartItems(Model model) {
        List<CartItem> cartItems = cart.getItems();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("numOfCartItems", cart.getNumOfItems());
        model.addAttribute("grandTotal", cart.getGrandTotal());
    }

    private Integer getSessionParam(HttpSession session, String paramName, Integer paramValue, Integer defaultValue) {
        if (paramValue == null) {
            Integer sessionValue = (Integer) session.getAttribute(paramName);
            paramValue = sessionValue == null ? defaultValue : sessionValue;
        }
        session.setAttribute(paramName, paramValue);
        return paramValue;
    }
}
