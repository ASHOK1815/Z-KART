package Inventory;

public class Cart {

    public String email;
    public String brand;
    public String category;
    public String model;
    public double price;
    public String Time;
    public String Date;
    public int randomInt;



    public Cart( String email, String brand, String category,String model, double price,String Time,String Date,int radomnumber)
    {
        this.email = email;
        this.brand = brand;
        this.category = category;
        this.model = model;
        this.price=price;
        this.Time=Time;
        this.Date=Date;
        randomInt = radomnumber;

    }



    @Override
    public  String toString() {
        return email + " "+ brand+ " "+category+" "+model+" "+price+" "+Date+" "+Time+" "+randomInt+"\n";
    }



}
