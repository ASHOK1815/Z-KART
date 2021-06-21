package Inventory;

public class Order {

    public int id;
    public String email;
    public int discount;
    public double totalAmount;
    public String Time;
    public String Date;



    public Order(int id, String email, int discount, double totalAmount, String Time, String Date)
    {
        this.id=id;
        this.email = email;
        this.discount=discount;
        this.totalAmount=totalAmount;
        this.Date=Date;
        this.Time=Time;

    }


    @Override
    public  String toString() {
        return id+" "+email+" "+discount+" "+totalAmount+" "+Date+" "+Time+"\n";
    }
}
