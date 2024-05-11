package onlineshop;

import onlineshop.merchandise.Book;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.io.Reader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Manages the Shop
 */
@SpringBootApplication
public class Shop {
    public static final DecimalFormat df = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.US));

    private final static String RESOURCES = "src/main/resources/";
    private final static String CSV_FILE = "books.csv";
    private static Logger log = LogManager.getLogger(Shop.class);
    private final static List<Book> books = new ArrayList<>(220);


    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.readArticles(CSV_FILE, books);
        SpringApplication.run(Shop.class, args);
        log.info("Server started on http://localhost:8080");
    }

    public List<Book> getArticles() {
        return books;
    }

    public int getNumOfArticles() {
        return books.size();
    }

    /**
     * Read articles from a CSV file
     * @param fileName {@link String}
     * @param books    {@link List}
     */
    private void readArticles(String fileName, List<Book> books) {
        try {
            Reader in = new FileReader(RESOURCES + fileName);
            CSVFormat csvFormat = CSVFormat.EXCEL.withFirstRecordAsHeader().builder()
                    .setDelimiter(';')
                    .build();
            Iterable<CSVRecord> records = csvFormat.parse(in);

            for (CSVRecord record : records) {
                String title = record.get("Title");
                String author = record.get("Author");
                String publisher = record.get("Publisher");
                String genre = record.get("Genre");
                int pages = Integer.parseInt(record.get("Pages"));

                double price = 0.0;
                String priceString = record.get("Price");
                if (!priceString.isEmpty()) {
                    priceString = priceString.replace(',', '.');
                    price = Double.parseDouble(priceString);
                }

                String image = record.get("Image");
                if (image.isEmpty()) image = "/images/book-placeholder.png";
                Book book = new Book(title, author, publisher, genre, pages, price, image);
                books.add(book);
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.debug(books.size() + " books imported");
    }

    /**
     * Gets a /book by its article number
     * @param articleNo {@link Integer}
     * @return existingBook {@link Book}
     */
    public Book getArticleByNumber(int articleNo) {
        for (Book book : books) {
            if(book.getArticleNo() == articleNo)
                return book;
        }
        return null;
    }
}
