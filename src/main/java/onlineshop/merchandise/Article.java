package onlineshop.merchandise;

public class Article {
  /** Unique article number */
  protected int id;
  /** Display-title of this Article */
  protected String title;
  /** Shop price */
  protected double price;
  /** URL to the image */
  protected String image;

  public Article() { }

  public Article(String title) {
    this.title = title;

  }

  public Article(String title, double price, String image) {
    this.title = title;
    this.price = price;
    this.image = image;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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
