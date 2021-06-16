package com.zcart;

import Inventory.Cart;
import Inventory.Invoice;
import Inventory.Product;
import user.Customer;
import user.UserRepository;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static String username="";
    private static boolean adminloginchecker=true;


    public static boolean Emailchecker(String email) throws IOException {

        File readfile = new File("./zusers_db.txt");
        BufferedReader br = new BufferedReader(new FileReader(readfile));
        String st;
        while ((st = br.readLine()) != null)
        {
            String[] data=st.split(" ");
            if(data[0].equals(email))
            {

                return true;
            }
        }
        br.close();

        return false;
    }

    public static boolean Adminchecker(String email,String Password) throws IOException {

        File readfile = new File("./zusers_db.txt");
        BufferedReader br = new BufferedReader(new FileReader(readfile));
        String st;

        while ((st = br.readLine()) != null)
        {
            String[] data=st.split(" ");
            if(data[2].equals("Admin") && data[1].equals(Password))
            {
                username=data[2];
                return true;
            }
        }
        br.close();

        return false;
    }


    public static boolean Passwordchecker(String email,String Password) throws IOException {

        File readfile = new File("./zusers_db.txt");
        BufferedReader br = new BufferedReader(new FileReader(readfile));
        String st;

        while ((st = br.readLine()) != null)
        {
            String[] data=st.split(" ");
            if(data[0].equals(email) && data[1].equals(Password))
            {
                username=data[2];
                return true;
            }
        }
        br.close();

        return false;
    }

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        UserRepository userRepository = new UserRepository();
        File file = new File("./zusers_db.txt");
        char choice;
       do{
            System.out.println("Welcome to Z-kart");
            System.out.println("1:Register");
            System.out.println("2:Login");
            System.out.println("3:Password Change");
            System.out.println("Q:Quit");
            choice =scan.next().charAt(0);
            switch(choice) {
                case '1':
                    String email;
                    String password;
                    String password1;
                    String name;
                    long mobileNumber;
                    System.out.println("Enter Your Mail id");
                    email = scan.next();

                    if (Emailchecker(email)) {
                        System.out.println("Email id alraedy exist!. Try to login");
                        break;
                    }

                    System.out.println("Enter Your first Name");
                    name = scan.next();
                    scan.nextLine();


                    System.out.println("Enter Your Mobile Number");
                    mobileNumber = scan.nextLong();
                    scan.nextLine();
                    System.out.println("Password complexity of mandating at least 2 lower case, 2 upper case and 2 numbers with a minimum length of 6");

                    System.out.println("Enter Your Password");
                    password = scan.next();

                    System.out.println("Enter Password again");
                    password1 = scan.next();


                    int upper = 0, lower = 0, number = 0, special = 0;

                    for(int i = 0; i < password.length(); i++)
                    {
                        char ch = password.charAt(i);
                        if (ch >= 'A' && ch <= 'Z')
                            upper++;
                        else if (ch >= 'a' && ch <= 'z')
                            lower++;
                        else if (ch >= '0' && ch <= '9')
                            number++;
                        else
                            special++;
                    }




                    if(upper<2 || lower<2 || number<2  || password.length()<6)
                    {
                        System.out.println("Password condition not match Try again!");
                        upper=0;
                        lower=0;
                        number=0;
                        break;

                    }
                    else if(upper==0 && lower==0 && number==0 && special==0)
                    {


                        for(int i = 0; i < password1.length(); i++)
                        {
                            char ch = password1.charAt(i);
                            if (ch >= 'A' && ch <= 'Z')
                                upper++;
                            else if (ch >= 'a' && ch <= 'z')
                                lower++;
                            else if (ch >= '0' && ch <= '9')
                                number++;
                            else
                                special++;
                        }
                        if(upper<2 || lower<2 || number<2  || password.length()<6)
                        {
                            System.out.println("Password condition not match Try again!");
                            break;
                        }
                    }
                    else if (password.length() != password1.length()) {
                        System.out.println("Password did not match Try again!");
                        break;

                    } else {
                        boolean flag = true;
                        for (int i = 0; i < password.length(); i++) {
                            if (password.charAt(i) != password1.charAt(i)) {
                                flag = false;
                                break;
                            }
                        }

                        if (!flag) {
                            System.out.println("Password did not match Try again!");
                            break;
                        }
                    }

                    password = userRepository.encryptPassword(password);




//                    System.out.println(password1 + " " + password);


                    Customer customer = new Customer(email, password, name, mobileNumber);

                    userRepository.addUser(customer,file);
                    System.out.println(customer.getName() + " added successfully");
                    break;
                case '2':

                    String Email;
                    String Password;

                    System.out.println("Enter Your mail id");
                    Email = scan.next();
                    System.out.println("Enter password");
                    Password = scan.next();
                    Password = userRepository.encryptPassword(Password);

                    if(Adminchecker(Email,Password))
                    {

                        ArrayList<Customer> list2 = new ArrayList<Customer>();
                        File AdminUpdate= new File("./zusers_db.txt");
                        BufferedReader Adu = new BufferedReader(new FileReader(AdminUpdate));
                        String temp;
                        while ((temp = Adu.readLine()) != null)
                        {
                            String[] data=temp.split(" ");
                            Customer cust=new Customer(data[0],data[1],data[2], Long.parseLong(data[3]));
                            list2.add(cust);
                        }

                        if(adminloginchecker && Password=="xyzzy")
                        {
                            String Password1;
                            String Password2;
                            System.out.println("--WELCOME TO THE ADMIN SECTION---");
                            System.out.println("For Security Purpose Kindly change Your Password and relogin again");
                            System.out.println("Enter new password");
                            Password1=scan.next();
                            System.out.println("Enter password again");
                            Password2=scan.next();
                            if (Password1.length() != Password2.length()) {
                                System.out.println("Password did not match Try again!");
                                break;
                            }
                            else {
                                boolean flag = true;
                                for (int i = 0; i < Password1.length(); i++) {
                                    if (Password1.charAt(i) != Password2.charAt(i)) {
                                        flag = false;
                                        break;
                                    }
                                }
                                if (!flag) {
                                    System.out.println("Password did not match Try again!");
                                    break;
                                }
                            }





                            password = userRepository.encryptPassword(Password1);

                            for(int i=0;i<list2.size();i++)
                            {
                                if(list2.get(i).name.equals("Admin"))
                                {
                                    list2.get(i).password=password;
                                }
                            }


                            FileWriter fileWriter = new FileWriter(AdminUpdate,true);
                            PrintWriter writer = new PrintWriter(AdminUpdate);
                            writer.print("");
                            writer.close();

                            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
                            for(int i=0;i<list2.size();i++)
                            {
                                bufferedWriter.write(list2.get(i).toString());
                            }

                            bufferedWriter.close();
                            fileWriter.close();

                            System.out.println("Password change Successfully !");




                            adminloginchecker=false;
                            break;
                        }
                        else
                        {
                            System.out.println("Ashok");

                            Admin admin=new Admin();

                            ArrayList<Product> list1 = new ArrayList<Product>();
                            File Pro = new File("./z-kart_db.txt");
                            BufferedReader br = new BufferedReader(new FileReader(Pro));
                            String st;
                            while ((st = br.readLine()) != null)
                            {
                                String[] data=st.split(" ");
                                Product prod=new Product(data[0],data[1],data[2],Double.parseDouble(data[3]),Integer.parseInt(data[4]));
                                list1.add(prod);
                            }

                            admin.displayCurrentStockDetails(list1);

                            int AdminPersmission;


                            do{
                                System.out.println("Want to Update stock and add product::--PRESS: 1   PRESS:Q-QUIT");
                                AdminPersmission=scan.nextInt();
                                if(AdminPersmission==1)
                                {

                                    String brandname=null;
                                    String modelname=null;
                                    System.out.println("Enter the Brand Name and Model");
                                    brandname=scan.next();
                                    modelname=scan.next();
                                    int stockvalue=0;
                                    System.out.println("How many stock you want to add in  "+brandname);
                                    stockvalue=scan.nextInt();

                                    boolean flag=true;

                                    for(int i=0;i<list1.size();i++)
                                    {
                                        if(list1.get(i).brand.equalsIgnoreCase(brandname) && list1.get(i).model.equalsIgnoreCase(modelname))
                                        {
                                            flag=false;
                                            list1.get(i).stock=list1.get(i).stock+stockvalue;
                                        }
                                    }

                                    if(flag)
                                    {
                                        System.out.println("Data not founded please write proper name");
                                    }
                                    else
                                    {

                                        FileWriter fileWriter = new FileWriter(Pro);
                                        PrintWriter writer = new PrintWriter(Pro);
                                        writer.print("");
                                        writer.close();

                                        BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
                                        for(int i=0;i<list1.size();i++)
                                        {
                                            bufferedWriter.write(list1.get(i).toString());
                                        }

                                        bufferedWriter.close();
                                        fileWriter.close();
                                        System.out.println("Stock Updated Successfully");

                                    }


                                }
                                else
                                {
                                    System.out.println("Thanks for checking out Stock ");
                                    System.out.println("-----------Z-KART-------------");

                                }



                            }while(AdminPersmission==1);




                        }


                    }
                    else if (Passwordchecker(Email, Password)) {

                        char Choice;
                        do {
                            System.out.println("Welcome to Z-kart");
                            System.out.println("1:Shopping");
                            System.out.println("2:ORDER-INVOICE");
                            System.out.println("3:ORDER-HISTORY");
                            System.out.println("4:CART");
                            System.out.println("5:Log-Out");
                            Choice = scan.next().charAt(0);
                            switch (Choice)
                            {
                                case '1':

                                        ArrayList<Product> list1 = new ArrayList<Product>();
                                        File Pro = new File("./z-kart_db.txt");
                                        BufferedReader br = new BufferedReader(new FileReader(Pro));
                                        String st;
                                        while ((st = br.readLine()) != null)
                                            {
                                                String[] data=st.split(" ");
                                                Product prod=new Product(data[0],data[1],data[2],Double.parseDouble(data[3]),Integer.parseInt(data[4]));
                                                list1.add(prod);
                                            }



                                        System.out.println("Enter the category you want to see");
                                        System.out.println("Type:1-MOBILE");
                                        System.out.println("Type:2-LAPTOP");
                                        System.out.println("Type:3-TABLET");
                                        int CATEGORY=scan.nextInt();
                                        String addCategory = null;
                                        double price = 0;

                                        if(CATEGORY==1)
                                        {
                                            System.out.println("CATEGORY:---MOBILE----------- ");
                                            addCategory="Mobile";
                                            for(int i=0;i<list1.size();i++)
                                            {

                                                if((list1.get(i).category).equalsIgnoreCase("Mobile"))
                                                {
                                                    price=list1.get(i).price;
                                                    System.out.println("BRAND :-" +list1.get(i).brand);
                                                    System.out.println("MODEL :-" +list1.get(i).model);
                                                    System.out.println("PRICE :-" +list1.get(i).price);
                                                    System.out.println("STOCK :-" +list1.get(i).stock);

                                                }
                                                System.out.println();
                                            }
                                        }
                                        else if(CATEGORY==2)
                                        {
                                            System.out.println("CATEGORY:---LAPTOP----------- ");
                                            addCategory="Laptop";
                                            for(int i=0;i<list1.size();i++)
                                            {
                                                if((list1.get(i).category).equalsIgnoreCase("Laptop"))
                                                {
                                                    price=list1.get(i).price;
                                                    System.out.println("BRAND :-" +list1.get(i).brand);
                                                    System.out.println("MODEL :-" +list1.get(i).model);
                                                    System.out.println("PRICE :-" +list1.get(i).price);
                                                    System.out.println("STOCK :-" +list1.get(i).stock);

                                                }
                                                System.out.println();
                                            }
                                        }
                                        else if(CATEGORY==3)
                                        {
                                            System.out.println("CATEGORY:---Tablet----------- ");
                                            addCategory="Tablet";
                                            for(int i=0;i<list1.size();i++)
                                            {
                                                if((list1.get(i).category).equalsIgnoreCase("Tablet"))
                                                {
                                                    price=list1.get(i).price;
                                                    System.out.println("BRAND :-" +list1.get(i).brand);
                                                    System.out.println("MODEL :-" +list1.get(i).model);
                                                    System.out.println("PRICE :-" +list1.get(i).price);
                                                    System.out.println("STOCK :-" +list1.get(i).stock);

                                                }
                                                System.out.println();
                                            }
                                        }


                                        System.out.println("Enter the brand and model you want to buy");
                                        String Brand = scan.next();
                                        String mode  = scan.next();

                                        boolean flag=true;
                                        for(int i=0;i<list1.size();i++)
                                        {
                                            if((list1.get(i).brand).equalsIgnoreCase(Brand) && (list1.get(i).model).equalsIgnoreCase(mode))
                                            {
                                                if(list1.get(i).stock==0)
                                                {
                                                    System.out.println("Product is out of stock");
                                                    flag=false;
                                                    break;
                                                }
                                                else
                                                {
                                                    list1.get(i).stock=list1.get(i).stock-1;
                                                }

                                            }
                                        }


                                        if(!flag)
                                        {
                                            break;
                                        }

                                    FileWriter fileWriter = new FileWriter(Pro,true);
                                    PrintWriter writer = new PrintWriter(Pro);
                                    writer.print("");
                                    writer.close();

                                    BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
                                    for(int i=0;i<list1.size();i++)
                                    {
                                        bufferedWriter.write(list1.get(i).toString());
                                    }

                                    bufferedWriter.close();
                                    fileWriter.close();


                                    LocalTime TimeObj = LocalTime.now();
                                    LocalDate DateObj = LocalDate.now();



                                    int randomInt;

                                    int min = 1000;
                                    int max = 9999;


                                    randomInt = (int)Math.floor(Math.random()*(max-min+1)+min);
                                    Cart cart = new Cart(Email,Brand,addCategory,mode,price,DateObj.toString(),TimeObj.toString(),randomInt);


                                    File currentprod=new File(("./z-current-product_db.txt"));

                                    UserRepository.addCart(cart,currentprod);


                                    File fILE = new File("./zcartuserHistory_db.txt");
                                    UserRepository.addCart(cart,fILE);



                                    break;

                                case '2':

                                    ArrayList<Cart> list2 = new ArrayList<Cart>();
                                    File Cartreader = new File("./z-current-product_db.txt");
                                    BufferedReader bri = new BufferedReader(new FileReader(Cartreader));

                                    String St;
                                    LocalTime TimeOBJ = LocalTime.now();
                                    LocalDate DateOBJ = LocalDate.now();


                                    while ((St = bri.readLine()) != null)
                                    {
                                        String[] data=St.split(" ");
                                        if(data[0].equals(Email))
                                        {

                                            Cart cART = new Cart(data[0],data[1],data[2],data[3],Double.parseDouble(data[4]),data[5],data[6],Integer.parseInt(data[7]));
                                            list2.add(cART);
                                        }

                                    }

                                    Invoice a1=new Invoice(username,list2,Email,TimeOBJ.toString(),DateOBJ.toString());


                                    break;



                                case '3':

                                        ArrayList<Cart> list3 = new ArrayList<Cart>();
                                        File CartHistoryReader = new File("./zcartuserHistory_db.txt");
                                        BufferedReader brin = new BufferedReader(new FileReader(CartHistoryReader));


                                        LocalTime TIMEOBJ = LocalTime.now();
                                        LocalDate DATEOBJ = LocalDate.now();

                                        String ST;
                                        while ((ST = brin.readLine()) != null)
                                        {
                                            String[] data=ST.split(" ");
                                            if(data[0].equals(Email))
                                            {

                                                Cart cART = new Cart(data[0],data[1],data[2],data[3],Double.parseDouble(data[4]),data[5],data[6],Integer.parseInt(data[7]));
                                                list3.add(cART);
                                            }

                                        }

                                        Invoice a2=new Invoice(username,list3,Email,TIMEOBJ.toString(),DATEOBJ.toString());
                                        break;





                                default:
                                    System.out.println("------Moving to the Main Page!--------");
                                    break;


                            }

                        } while (Choice !='5');
                       // CLEARING THE INVOICE CURRENT FILE
                        File Currentprod=new File(("./z-current-product_db.txt"));
                        PrintWriter CurrentProdFileEmpty = new PrintWriter(Currentprod);
                        PrintWriter Writer = new PrintWriter(CurrentProdFileEmpty);
                        Writer.print("");
                        Writer.close();

                    } else {
                        System.out.println("Email and password not valid!");
                    }


                    break;
                case '3':

                    String emaiL;
                    String passworD;
                    String passworD1;
                    String oldPassword;

                    System.out.println("Enter Your Mail id");
                    emaiL = scan.next();
                    if (!Emailchecker(emaiL)) {
                        System.out.println("Email not registered ! Register Your Mail id");
                        break;
                    }
                    System.out.println("Enter Your old Password");
                    oldPassword = scan.next();
                    oldPassword = userRepository.encryptPassword(oldPassword);



                    File Update= new File("./zusers_db.txt");
                    boolean flag=false;
                    BufferedReader Ad = new BufferedReader(new FileReader(Update));
                    String t;
                    String Password_dummy;
                    while ((t = Ad.readLine()) != null)
                    {
                        String[] data=t.split(" ");
                        if(data[0].equals(emaiL) && data[1].equals(oldPassword))
                        {
                            Password_dummy=oldPassword;
                            flag=true;
                        }

                    }

                    if(!flag)
                    {
                        System.out.println("Old PASSWORD INCORRECT");
                        break;
                    }
                    else
                    {
                        System.out.println("PASSWORD AUTHENTICATED");
                    }










                    System.out.println("Password complexity of mandating at least 2 lower case, 2 upper case and 2 numbers with a minimum length of 6");

                    System.out.println("Enter Your Password");
                    passworD = scan.next();


                    System.out.println("Enter Password again");
                    passworD1 = scan.next();


                    int Upper = 0, Lower = 0, Number = 0, Special = 0;

                    for(int i = 0; i < passworD.length(); i++)
                    {
                        char ch = passworD.charAt(i);

                        if (ch >= 'A' && ch <= 'Z')
                            Upper++;
                        else if (ch >= 'a' && ch <= 'z')
                            Lower++;
                        else if (ch >= '0' && ch <= '9')
                            Number++;
                        else
                            Special++;
                    }






                    if(Upper<2 || Lower<2 || Number<2  || passworD.length()<6)
                    {
                        System.out.println("Password condition not match Try again!");
                        upper=0;
                        lower=0;
                        number=0;
                        special=0;
                        break;

                    }
                    else if(Upper==0 && Lower==0  && Number==0 && Special==0)
                    {


                        for(int i = 0; i < passworD1.length(); i++)
                        {
                            char ch = passworD1.charAt(i);
                            if (ch >= 'A' && ch <= 'Z')
                                Upper++;
                            else if (ch >= 'a' && ch <= 'z')
                                Lower++;
                            else if (ch >= '0' && ch <= '9')
                                Number++;
                            else
                                Special++;
                        }

                        if(Upper<2 || Lower<2 || Number<2  || passworD1.length()<6)
                        {
                            System.out.println("Hello");
                            System.out.println("Password condition not match Try again!");
                            break;
                        }

                    }
                    else if (passworD.length() != passworD1.length()) {
                        System.out.println("Password did not match Try again!");
                        break;
                    } else {
                        boolean Flag = true;
                        for (int i = 0; i < passworD.length(); i++) {
                            if (passworD.charAt(i) != passworD1.charAt(i)) {
                                Flag = false;
                                break;
                            }
                        }

                        if (!Flag) {
                            System.out.println("Password did not match Try again!");
                            break;
                        }
                    }
                    passworD = userRepository.encryptPassword(passworD);


                    ArrayList<Customer> list2 = new ArrayList<Customer>();
                    File AdminUpdate= new File("./zusers_db.txt");
                    BufferedReader Adu = new BufferedReader(new FileReader(AdminUpdate));
                    String temp;
                    while ((temp = Adu.readLine()) != null)
                    {
                        String[] data=temp.split(" ");
                        Customer cust=new Customer(data[0],data[1],data[2], Long.parseLong(data[3]));
                        list2.add(cust);
                    }



                    for(int i=0;i<list2.size();i++)
                    {
                        if(list2.get(i).email.equals(emaiL))
                        {
                            list2.get(i).password=passworD;
                        }
                    }


                    FileWriter fileWriter = new FileWriter(AdminUpdate,true);
                    PrintWriter writer = new PrintWriter(AdminUpdate);
                    writer.print("");
                    writer.close();

                    BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
                    for(int i=0;i<list2.size();i++)
                    {
                        bufferedWriter.write(list2.get(i).toString());
                    }

                    bufferedWriter.close();
                    fileWriter.close();

                    System.out.println("Password change Successfully !");

                    break;


                default:
                    System.out.println("Wrong Input Please Try again!");
                    break;


            }

        }while(choice!='Q');

    }
}
