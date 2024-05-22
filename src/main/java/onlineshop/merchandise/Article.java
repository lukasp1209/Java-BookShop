package onlineshop.merchandise;

public class Article {
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

  public Article() { }

  public Article(String title, String manufacturer) {
    this.title = title;
    this.manufacturer = manufacturer;
  }

  public Article(String title, String manufacturer, double price, String image) {
    this.title = title;
    this.manufacturer = manufacturer;
    this.price = price;
    this.image = image;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public int getArticleNo() {
    return articleNo;
  }

  public void setArticleNo(int articleNo) {
    this.articleNo = articleNo;
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

  public void setPrice(double price) {
    this.price = price;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
