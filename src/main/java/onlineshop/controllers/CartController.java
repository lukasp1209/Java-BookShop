package onlineshop.controllers;

import onlineshop.Cart;
import onlineshop.Shop;
import onlineshop.merchandise.Article;
import onlineshop.merchandise.Book;
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

    @GetMapping(value = {"/add/{articleNo}"})
    public String addToCart(@PathVariable(name = "articleNo") Integer articleNo, RedirectAttributes atts) {
        String message = "Book with article no. \"" + articleNo + "\" not found.";
        Book book = shop.getArticleByNumber(articleNo);
        if (book != null) {
            cart.addArticle(book);
            message = "Article \"" + book.getTitle() + "\" added to cart.";
        }
        atts.addFlashAttribute(MESSAGE, message);
        atts.addFlashAttribute(SHOW_MESSAGE, true);
        return "redirect:/index.html";
    }

    @GetMapping(value = {"/increase/{articleNo}"})
    public String increaseQuantity(@PathVariable(name = "articleNo") Integer articleNo, RedirectAttributes atts) {
        String message = "Book with article no. \"" + articleNo + "\" not found.";
        Book book = shop.getArticleByNumber(articleNo);
        if (book != null) {
            cart.increaseQuantity(book.getArticleNo());
        } else {
            atts.addFlashAttribute(MESSAGE, message);
            atts.addFlashAttribute(SHOW_MESSAGE, true);
        }
        return "redirect:/cart.html";
    }

    @GetMapping(value = {"/decrease/{articleNo}"})
    public String decreaseQuantity(@PathVariable(name = "articleNo") Integer articleNo, RedirectAttributes atts) {
        Article article = shop.getArticleByNumber(articleNo);
        if (!cart.decreaseQuantity(articleNo)) {
            atts.addFlashAttribute(MESSAGE, "Article \"" + article.getTitle() + "\" removed from cart.");
            atts.addFlashAttribute(SHOW_MESSAGE, true);
        }
        return "redirect:/cart.html";
    }

    @GetMapping(value = {"/remove/{articleNo}"})
    public String removeFromCart(@PathVariable(name = "articleNo") Integer articleNo, RedirectAttributes atts) {
        String message = "Article with article no. \"" + articleNo + "\" not found in cart.";
        Article article = shop.getArticleByNumber(articleNo);
        if (article != null && cart.removeArticle(articleNo)) {
            message = "Article \"" + article.getTitle() + "\" removed from cart.";
        }
        atts.addFlashAttribute(MESSAGE, message);
        atts.addFlashAttribute(SHOW_MESSAGE, true);
        return "redirect:/cart.html";
    }
}
