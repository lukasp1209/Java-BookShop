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
public class CartController extends BaseController {
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
        showMessage(atts, message);
        return "redirect:/index.html";
    }

    @GetMapping(value = {"/increase/{articleNo}"})
    public String increaseQuantity(@PathVariable(name = "articleNo") Integer articleNo, RedirectAttributes atts) {
        String message = "Book with article no. \"" + articleNo + "\" not found.";
        Book book = shop.getArticleByNumber(articleNo);
        if (book != null) {
            cart.increaseQuantity(book.getArticleNo());
        } else {
            showMessage(atts, message);
        }
        return "redirect:/cart.html";
    }

    @GetMapping(value = {"/decrease/{articleNo}"})
    public String decreaseQuantity(@PathVariable(name = "articleNo") Integer articleNo, RedirectAttributes atts) {
        Article article = shop.getArticleByNumber(articleNo);
        String message = "Article '" + article.getTitle() + "' removed from cart.";
        if (!cart.decreaseQuantity(articleNo)) {
            showMessage(atts, message);
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
        showMessage(atts, message);
        return "redirect:/cart.html";
    }
}
