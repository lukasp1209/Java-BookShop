package onlineshop.merchandise;

public class OrderItem extends CartItem {
    private double discount;
    private double shippingCost;
    private double taxAmount;

    public OrderItem(CartItem cartItem) {
        super(cartItem.getProduct(), cartItem.getQuantity(), cartItem.getPrice());
    }

//    public OrderItem(CartItem cartItem, double discount, double shippingCost, double taxAmount) {
//        this(cartItem);
//        this.discount = discount;
//        this.shippingCost = shippingCost;
//        this.taxAmount = taxAmount;
//    }

    // Getters and Setters
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }
}
