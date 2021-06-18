package product;

public class Product {


    public String brand;
    public String category;
    public String model;
    public double price;
    public int stock;

    public Product(String category,String brand,  String model, double price, int stock) {
        this.brand = brand;
        this.category = category;
        this.model = model;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return category + " "+ brand+ " "+model+" "+price+""+" "+stock+"\n";
    }
}
