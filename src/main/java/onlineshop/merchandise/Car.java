package onlineshop.merchandise;

public class Car {
    protected int id;
    protected String brand;
    protected String model;
    protected int year;
    protected String power;
    protected double price;
    protected String image;

    public Car(int id, String brand, String model, int year, String power, double price, String image) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.power = power;
        this.price = price;
        this.image = image;
    }

    public Car() {
        
    }


    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getPower() {
        return power;
    }

    public double getPrice() {
        return price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
