package onlineshop;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import onlineshop.merchandise.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShopController {
    private static Logger log = LogManager.getLogger(Shop.class);
    public final static String ARTICLES = "articles";


    @GetMapping(value = {"/"})
    public String root(Model model) {
        return "redirect:/index.html";
    }

    @GetMapping(value = {"/index.html"})
    public String homePage(Model model) {
        model.addAttribute(ARTICLES, Shop.getArticles());
        return "index";
    }

    @GetMapping(value = {"/{name}.html"})
    public String htmlMapping(@PathVariable String name, HttpSession session) {
        return name;
    }
}
