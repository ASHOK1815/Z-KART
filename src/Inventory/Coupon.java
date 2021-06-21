package Inventory;

public class Coupon {

    public int id;
    public String email;
    public int getOnOrderId;
    public int couponCode;
    public String Time;
    public String Date;



    public Coupon(int id, String email, int getOnOrderId, int couponCode, String Time, String Date)
    {
        this.id=id;
        this.email = email;
        this.getOnOrderId = getOnOrderId;
        this.couponCode = couponCode;
        this.Date=Date;
        this.Time=Time;
    }


    @Override
    public  String toString() {
        return id+" "+email+" "+getOnOrderId+" "+couponCode+" "+Date+" "+Time+" "+"\n";
    }



}
