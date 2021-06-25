package customer;


public class Customer {

    public String email;
    public  String password;
    public String name;
    public long mobileNumber;
    public int isInitialCouponGenerated;

    public Customer( String email, String password, String name, long mobileNumber, int isInitialCouponGenerated) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.isInitialCouponGenerated = isInitialCouponGenerated;
    }


    @Override
    public String toString() {
        return email+" "+password+" "+name+" "+mobileNumber+" "+isInitialCouponGenerated+"\n";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }
}
