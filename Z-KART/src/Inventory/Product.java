package Inventory;

public class Product {

    public
    String brand;
    String category;
    String model;
    double price;
    int stock;

    public Product(String brand, String category, String model, double price, int stock) {
        this.brand = brand;
        this.category = category;
        this.model = model;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
