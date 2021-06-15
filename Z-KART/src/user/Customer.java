package user;

public class Customer {

          public String email;
          public String password;
          public String name;
          public long mobileNumber;

    public Customer( String email, String password, String name, long mobileNumber)
    {
        this.email = email;
        this.password = password;
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return email + " "+ password+ " "+name+" "+mobileNumber+"\n";
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


