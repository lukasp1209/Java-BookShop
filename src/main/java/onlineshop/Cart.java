package onlineshop;

import onlineshop.merchandise.Car;
import onlineshop.merchandise.CartItem;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class Cart {
    final static int MAX_QUANTITY = 15;
    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    /**
     * Counts cart items and their quantity
     * @return numberOfItems {@link Integer}
     */
    public int getNumOfItems() {
        int numOfItems = 0;
        for (CartItem item : items) {
            numOfItems += item.getQuantity();
        }
        return numOfItems;
    }

    /**
     * Sums all cart items, taking their quantity into account
     * @return formattedNumber {@link String}
     */
    public String getGrandTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return Shop.df.format(total);
    }

    public void clear() {
        items.clear();
    }

    /**
     * Adds an article/car to the cart
     * @param car {@link Book}
     */
    public void addArticle(Car car) {
        CartItem item = findItem(car.getId());
        if (item == null) {
            item = new CartItem(car);
            items.add(item);
        }
        // limit quantity
        if (item.getQuantity() <= MAX_QUANTITY) {
            item.increaseQuantity();
        }
    }

    public boolean increaseQuantity(int id) {
        CartItem existingItem = findItem(id);
        if (existingItem != null) {
            existingItem.increaseQuantity();
            return true;
        }
        return false;
    }

    /**
     * Decreases the quantity of an existing article.
     * If quantity sinks below 1, it removes the article and returns 'false'.
     * @param id {@link Integer}
     * @return isArticleNotRemoved {@link Boolean}
     */
    public boolean decreaseQuantity(int id) {
        CartItem existingItem = findItem(id);
        if (existingItem != null) {
            existingItem.decreaseQuantity();
            if (existingItem.getQuantity() < 1) {
                items.remove(existingItem);
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Removes an article from the cart
     * @param id {@link Integer}
     * @return wasSuccesful {@link Boolean}
     */
    public boolean removeArticle(int id) {
        CartItem existingItem = findItem(id);
        if (existingItem != null) {
            items.remove(existingItem);
            return true;
        }
        return false;
    }

    /**
     * Finds an article by its article number
     * @param id {@link Integer}
     * @return existingItem {@link CartItem}
     */
    private CartItem findItem(int id) {
        for (CartItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public double calculateTotal() {
        return 0;
    }

    public Object getTotalPrice() {
        return null;
    }
}
