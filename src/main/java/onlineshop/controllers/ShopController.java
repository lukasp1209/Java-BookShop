package onlineshop.controllers;

import jakarta.servlet.http.HttpSession;
import onlineshop.Cart;
import onlineshop.Shop;
import onlineshop.merchandise.Book;
import onlineshop.merchandise.CartItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShopController {
    private final static Logger log = LogManager.getLogger(ShopController.class);
    public static final int PAGE_SIZE = 15;

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
                           HttpSession session) {
        page = getSessionParam(session, "page", page, 1);
        handlePagination(model, page);
        getCartItems(model);
        return "index";
    }

    @GetMapping(value = {"/{name}.html"})
    public String htmlMapping(Model model, @PathVariable(name = "name") String name) {
        getCartItems(model);
        return name;
    }

    /**
     * Loads the cart items from the cart object and stores the corresponding attributes in the view model.
     * @param model {@link Model}
     */
    private void getCartItems(Model model) {
        List<CartItem> cartItems = cart.getItems();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("numOfCartItems", cart.getNumOfItems());
        model.addAttribute("grandTotal", cart.getGrandTotal());
    }

    /**
     * Looks up the requested parameter in  the session. If it doesn't exist, it uses the default value.
     * @param session {@link jakarta.servlet.http.HttpSession}
     * @param paramName {@link String}
     * @param paramValue {@link Integer}
     * @param defaultValue {@link Integer}
     * @return sessionValue {@link Integer}
     */
    private Integer getSessionParam(HttpSession session,
                                    String paramName,
                                    Integer paramValue,
                                    Integer defaultValue) {
        if (paramValue == null) {
            Integer sessionValue = (Integer) session.getAttribute(paramName);
            paramValue = sessionValue == null ? defaultValue : sessionValue;
        }
        session.setAttribute(paramName, paramValue);
        return paramValue;
    }

    /**
     * Delivers the articles sublist corresponding to the selected page
     * @param model {@link Model}
     * @param page {@link Integer}
     */
    private void handlePagination(Model model, Integer page) {
        int numOfArticles = shop.getNumOfArticles();
        int from = Math.max((page - 1) * PAGE_SIZE, 0);
        int to = Math.min(numOfArticles, from + PAGE_SIZE);
        List<Book> articles = shop.getArticles().subList(from, to);

        model.addAttribute("articles", articles);
        model.addAttribute("from", ++from);
        model.addAttribute("to", to);
        model.addAttribute("numOfArticles", numOfArticles);

        int pageCount = (numOfArticles / PAGE_SIZE) + 1;
        Map<Integer, String> pages = new HashMap<>();
        for(int pageNumber = 1; pageNumber <= pageCount; pageNumber++) {
            String active = (pageNumber == page) ? "active" : "";
            pages.put(pageNumber, active);
        }

        model.addAttribute("pageCount", pageCount);
        model.addAttribute("pages", pages.entrySet());
        model.addAttribute("prevPage", Math.max(page - 1, 1));
        model.addAttribute("nextPage", Math.min(page + 1, pageCount));
    }

}
