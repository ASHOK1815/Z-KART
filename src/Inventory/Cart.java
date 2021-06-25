package Inventory;

public class Cart {

    public String email;
    public int productId;
    public double price;
    public String Time;
    public String Date;



    public Cart( String email, int productId, double price, String Time, String Date)
    {
        this.email = email;
        this.productId = productId;
        this.price=price;
        this.Time=Time;
        this.Date=Date;

    }


    @Override
    public  String toString() {
        return email+" "+productId+" "+price+" "+Date+" "+Time+" "+"\n";
    }



}
