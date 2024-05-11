package onlineshop.merchandise;

import onlineshop.enums.Format;
import onlineshop.enums.Genre;

public class Book extends Article {
    protected int pages;
    protected String author;
    protected Format format;
    protected Genre genre;

    public Book() {
        super();
    }

    public Book(String title, String author) {
        super();
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author, String publisher, String genre, int pages, double price, String image) {
        super("Description will follow...", publisher, price, image);
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.format = Format.PAPERBACK;
        this.genre = Genre.fromString(genre);
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }
}
