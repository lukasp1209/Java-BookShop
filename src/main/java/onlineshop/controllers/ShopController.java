package onlineshop.controllers;

import jakarta.servlet.http.HttpSession;
import onlineshop.Shop;
import onlineshop.enums.Sorting;
import onlineshop.merchandise.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

@Controller
public class ShopController extends BaseController {
    private final static Logger log = LogManager.getLogger(ShopController.class);
    public static final int PAGE_SIZE = 15;

    @Autowired
    Shop shop;

    @GetMapping(value = {"/"})
    public String root() {
        return "redirect:/index.html";
    }

    @GetMapping(value = {"/index.html"})
    public String homePage(Model view,
                           @RequestParam(name = "page", required = false) Integer page,
                           @RequestParam(name = "sort", required = false) Sorting sort,
                           HttpSession session) {
        page = (Integer) getSessionParam(session, "page", page, 1);
        sort = (Sorting) getSessionParam(session, "sort", sort, Sorting.ALPHA_UP);
        handleSortingPagination(view, sort, page);
        getCartItems(view);
        return "index";
    }

    @GetMapping(value = {"/checkout.html"})
    public String checkout(Model view) {
        getCartItems(view);

        double subTotal = 0.0;
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
            subTotal = format.parse(cart.getGrandTotal()).doubleValue();
        } catch (ParseException e) {
            log.error("Fehler beim Parsen der Zahl: " + cart.getGrandTotal());
            view.addAttribute("errorMessage", "Ungültiges Zahlenformat im Warenkorb.");
            return "error";
        }
        double shippingCosts = 3.99;
        double taxRate = 0.07;
        double taxes = subTotal * taxRate;
        double grandTotal = subTotal + shippingCosts + taxes;

        view.addAttribute("subTotal", cart.getGrandTotal());
        view.addAttribute("shippingCosts", shippingCosts);
        view.addAttribute("taxes", Shop.df.format(taxes));
        view.addAttribute("grandTotal", Shop.df.format(grandTotal));
        return "checkout";
    }

    @GetMapping(value = {"/details.html"})
    public String detailsPage(Model view,
                              @RequestParam(name = "id") Integer id,
                              RedirectAttributes atts) throws IOException {
        // 1. Den Artikel mit der {id} Artikelnummer aus dem Shop abrufen
        Car car = shop.getArticleByNumber(id);

        // 2. Wenn der Artikel existiert, füge ihn den View-Attributen als 'car' hinzu
        if (car != null) {
            view.addAttribute("car", car);
        } else {
            // 3. Wenn er nicht existiert, zeige eine Fehlermeldung mit 'atts.addFlashAttribute()' an
            atts.addFlashAttribute("errorMessage", "Artikel mit der ID " + id + " wurde nicht gefunden.");
            return "redirect:/index.html";
        }

        // Warenkorb-Artikel zum View hinzufügen
        getCartItems(view);
        return "details";
    }


    @GetMapping(value = {"/{name}.html"})
    public String htmlMapping(Model view, @PathVariable(name = "name") String name) {
        getCartItems(view);
        return name;
    }

    /**
     * Delivers the articles sublist corresponding to the selected page
     *
     * @param view    {@link Model}
     * @param sorting {@link Sorting}
     * @param page    {@link Integer}
     */
    private void handleSortingPagination(Model view, Sorting sorting, Integer page) {
        int numOfArticles = shop.getNumOfArticles();
        int from = Math.max((page - 1) * PAGE_SIZE, 0);
        int to = Math.min(numOfArticles, from + PAGE_SIZE);
        List<Car> articles = shop.sortAndPaginateArticles(sorting, from, to);

        view.addAttribute("articles", articles);
        view.addAttribute("from", ++from);
        view.addAttribute("to", to);
        view.addAttribute("numOfArticles", numOfArticles);

        int pageCount = (numOfArticles / PAGE_SIZE) + 1;
        Map<Integer, String> pages = new HashMap<>();
        for (int pageNumber = 1; pageNumber <= pageCount; pageNumber++) {
            String active = (pageNumber == page) ? "active" : "";
            pages.put(pageNumber, active);
        }

        view.addAttribute("pageCount", pageCount);
        view.addAttribute("pages", pages.entrySet());
        view.addAttribute("prevPage", Math.max(page - 1, 1));
        view.addAttribute("nextPage", Math.min(page + 1, pageCount));

        createSortingLinks(view, sorting);
    }

    /**
     * Creates the model for the sorting drop down
     * @param view {@link Model} - the Spring ViewModel
     * @param currentSort {@link Sorting} - the current sorting
     */
    private void createSortingLinks(Model view, Sorting currentSort) {
        List<Sorting> sortings = new ArrayList<>();
        for (Sorting entry : Sorting.values()) {
            String isCurrentSort = (entry == currentSort) ? "selected" : "";
            entry.setSelected(isCurrentSort);
            sortings.add(entry);
        }
        view.addAttribute("sortings", sortings);
        view.addAttribute("sort", currentSort.getValue());
    }
}
