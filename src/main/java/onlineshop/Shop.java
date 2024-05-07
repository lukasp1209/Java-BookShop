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
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the Shop
 */
@SpringBootApplication
public class Shop {
    private final static String RESOURCES = "src/main/resources/";
    private final static String CSV_FILE = "books.csv";
    private static Logger log = LogManager.getLogger(Shop.class);
    private final static List<Book> books = new ArrayList<>(220);

    public static void main(String[] args) {
        readArticles(CSV_FILE, books);
        SpringApplication.run(Shop.class, args);
    }

    /**
     * Read articles from a CSV file
     * @param fileName {@link String}
     * @param books    {@link List}
     */
    private static void readArticles(String fileName, List<Book> books) {
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
                if(image.isEmpty()) image = "/images/product-item2.png";
                Book book = new Book(title, author, publisher, genre, pages, price, image);
                books.add(book);
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.debug(books.size() + " books imported");
    }

    public static List<Book> getArticles() {
        return books;
    }
}
