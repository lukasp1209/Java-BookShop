package onlineshop.merchandise;

public class Article {
  private static int articleCounter = 1;
  /** Unique article number */
  protected int articleNo;
  /** Display-title of this Article */
  protected String title;
  /** Manufacturer of this Article */
  protected String manufacturer;
  /** Shop price */
  protected double price;
  /** URL to the image */
  protected String image;

  public Article() {
    this.articleNo = Article.articleCounter++;
  }

  public Article(String title, String manufacturer) {
    this.title = title;
    this.manufacturer = manufacturer;
  }

  public Article(String title, String manufacturer, double price, String image) {
    this();
    this.title = title;
    this.manufacturer = manufacturer;
    this.price = price;
    this.image = image;
  }

  public String getImage() {
    return image;
  }

  public String getManufacturer() {
    return manufacturer;
  }
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public double getPrice() {
    return price;
  }

  public String getTitle() {
    return title;
  }
}
