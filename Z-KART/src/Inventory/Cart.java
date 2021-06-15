package Inventory;

public class Cart {

    public String email;
    public String brand;
    public String category;
    public String model;
    public double price;
    public int Quantity;

    public Cart( String email, String brand, String category,String model, double price)
    {
        this.email = email;
        this.brand = brand;
        this.category = category;
        this.model = model;
        this.price=price;
    }



    @Override
    public  String toString() {
        return email + " "+ brand+ " "+category+" "+model+" "+price+""+"\n";
    }



}
