package onlineshop;

import onlineshop.enums.Sorting;
import onlineshop.merchandise.Car;
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
import java.util.concurrent.ThreadLocalRandom;

/**
 * Manages the Shop
 */
@SpringBootApplication
public class Shop {
    public static final DecimalFormat df = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.GERMANY));
    private final static Logger log = LogManager.getLogger(Shop.class);
    private final static List<Car> cars = new ArrayList<>(220);

    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.readArticles("src/main/resources/cars.csv");
        SpringApplication.run(Shop.class, args);
        log.info("Server started on http://localhost:8080");
    }

    public List<Car> getArticles() {
        return cars;
    }

    /**
     * Returns a sublist of articles
     *
     * @param from {@link Integer}
     * @param to   {@link Integer}
     * @return articlesSublist {@link List<Car>}
     */
    public List<Car> sortAndPaginateArticles(Sorting sorting, int from, int to) {
        sortArticles(sorting);
        return cars.subList(from, to);
    }

    /**
     * Sorts the articles (i.e. the original List!) depending on the sorting
     *
     * @param sorting {@link Sorting}
     */
    private void sortArticles(Sorting sorting) {
        switch (sorting) {
            case DEFAULT:
            case ALPHA_UP:
                cars.sort((a, b) -> a.getModel().compareTo(b.getModel()));
                break;
            case ALPHA_DOWN:
                cars.sort((a, b) -> b.getModel().compareTo(a.getModel()));
                break;
            case PRICE_UP:
                cars.sort((a, b) -> (int) (a.getPrice() * 100 - b.getPrice() * 100));
                break;
            case PRICE_DOWN:
                cars.sort((a, b) -> (int) (b.getPrice() * 100 - a.getPrice() * 100));
                break;
/*
            case AUTHOR_UP:
                books.sort(Comparator.comparing(Car::getAuthor)); break;
            case AUTHOR_DOWN:
                books.sort(Comparator.comparing(Car::getAuthor).reversed()); break;
*/
        }
    }

    public int getNumOfArticles() {
        return cars.size();
    }

    /**
     * Read articles from a CSV file
     *
     * @param fileName {@link String}
     */

    void readArticles(String fileName) {
        try {
            Reader in = new FileReader(fileName);
            CSVFormat csvFormat = CSVFormat.EXCEL.withFirstRecordAsHeader().builder()
                    .setDelimiter(';')
                    .build();
            Iterable<CSVRecord> records = csvFormat.parse(in);
            ThreadLocalRandom random = ThreadLocalRandom.current();

            for (CSVRecord record : records) {
                Integer id = Integer.valueOf(record.get("id"));
                String brand = record.get("brand");
                String model = record.get("model");
                int year = Integer.parseInt(record.get("year"));
                String power = record.get("power");
                double price = Double.valueOf(record.get("price"));
                String image = record.get("image");
                if (image.isEmpty()) image = "/images/car-placeholder.png";

                Car car = new Car(id, brand, model, year, power, price, image);

                cars.add(car);
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("{} cars imported", cars.size());
    }

    /**
     * Gets an article by its article number
     *
     * @param id {@link Integer}
     * @return existingBook {@link Car}
     */
    public Car getArticleByNumber(int id) {
        for (Car car : cars) {
            if (car.getId() == id)
                return car;
        }
        return null;
    }
}

