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

    public Book(String title, String author, String publisher, String genre, double price, String image) {
        super("Description will follow...", publisher, price, image);
        this.title = title;
        this.author = author;
        this.format = Format.PAPERBACK;
        this.genre = Genre.fromString(genre);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
