package customer;



public class CustomerCoupen {

    int coupen;
    public String email;
    public String password;
    public String name;
    public long mobileNumber;
    public CustomerCoupen(String email, String password, String name, long mobileNumber, int coupen)
    {

       this.email=email;
       this.password=password;
       this.name=name;
       this.mobileNumber=mobileNumber;
       this.coupen=coupen;

    }


    @Override
    public String toString() {
        return email+" "+password+" "+name+" "+mobileNumber+" "+coupen+" "+"\n";
    }


}
