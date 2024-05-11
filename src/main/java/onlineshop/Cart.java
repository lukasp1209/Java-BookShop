package onlineshop;

import onlineshop.merchandise.Book;
import onlineshop.merchandise.CartItem;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class Cart {
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

    /**
     * Adds an article/book to the cart
     * @param book {@link Book}
     */
    public void addArticle(Book book) {
        CartItem item = findItem(book.getArticleNo());
        if (item == null) {
            item = new CartItem(book);
            items.add(item);
        }
        item.increaseQuantity();
    }

    /**
     * Decreases the quantity of an existing article.
     * If quantity sinks below 1, it removes the article and returns 'false'.
     * @param articleNo {@link Integer}
     * @return isArticleRemoved {@link Boolean}
     */
    public boolean decreaseQuantity(int articleNo) {
        CartItem existingItem = findItem(articleNo);
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
     * @param articleNo {@link Integer}
     * @return wasSuccesful {@link Boolean}
     */
    public boolean removeArticle(int articleNo) {
        CartItem existingItem = findItem(articleNo);
        if (existingItem != null) {
            items.remove(existingItem);
            return true;
        }
        return false;
    }

    /**
     * Finds an article by its article number
     * @param articleNo {@link Integer}
     * @return existingItem {@link CartItem}
     */
    private CartItem findItem(int articleNo) {
        for (CartItem item : items) {
            if (item.getArticleNo() == articleNo) {
                return item;
            }
        }
        return null;
    }
}
