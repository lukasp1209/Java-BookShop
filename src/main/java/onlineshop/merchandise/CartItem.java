package onlineshop.merchandise;

import onlineshop.Shop;

/**
 * Represents an article/book in the shopping cart
 */
public class CartItem extends Book {
    private int quantity = 0;

    public CartItem() {
    }

    public CartItem(Book book) {
        this.articleNo = book.articleNo;
        this.title = book.title;
        this.manufacturer = book.manufacturer;
        this.price = book.price;
        this.image = book.image;

        this.author = book.author;
        this.pages = book.pages;
        this.format = book.format;
        this.genre = book.genre;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getShowQuantity() {
        return quantity > 1;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public void decreaseQuantity() {
        quantity--;
    }

    public double getTotalPrice() {
        return quantity * price;
    }

    public String getTotalPriceFormatted() {
        return Shop.df.format(getTotalPrice());
    }
}
