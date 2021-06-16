package com.zcart;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import user.Customer;
import java.io.File;
import Inventory.*;

import user.UserRepository;

public class Main {

    static String username="";
    public static boolean Emailchecker(String email) throws IOException {

        File readfile = new File("./zusers_db.txt");
        BufferedReader br = new BufferedReader(new FileReader(readfile));
        String st;
        while ((st = br.readLine()) != null)
        {
            String[] data=st.split(" ");
            if(data[0].equals(email))
            {
                System.out.println("Email id alraedy exist!. Try to login");
                return true;
            }
        }

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
                        break;
                    }

                    System.out.println("Enter Your Name");
                    name = scan.next();
                    System.out.println("Enter Your Mobile Number");
                    mobileNumber = scan.nextLong();
                    scan.nextLine();
                    System.out.println("Enter Your Password");
                    password = scan.nextLine();

                    System.out.println("Enter Password again");
                    password1 = scan.nextLine();
                    if (password.length() != password1.length()) {
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
                    if (Passwordchecker(Email, Password)) {

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

                                                if((list1.get(i).category).equals("Mobile"))
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
                                                if((list1.get(i).category).equals("Laptop"))
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
                                                if((list1.get(i).category).equals("Tablet"))
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
                                            if((list1.get(i).brand).equals(Brand) && (list1.get(i).model).equals(mode))
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


                                    Cart cart = new Cart(Email,Brand,addCategory,mode,price,TimeObj.toString(),DateObj.toString());


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

                                            Cart cART = new Cart(data[0],data[1],data[2],data[3],Double.parseDouble(data[4]),data[5],data[6]);
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

                                                Cart cART = new Cart(data[0],data[1],data[2],data[3],Double.parseDouble(data[4]),data[5],data[6]);
                                                list3.add(cART);
                                            }

                                        }

                                        Invoice a2=new Invoice(username,list3,Email,TIMEOBJ.toString(),DATEOBJ.toString());
                                        break;

























                            }

                        } while (choice != '5');
                       // CLEARING THE INVOICE CURRENT FILE
                        File Currentprod=new File(("./z-current-product_db.txt"));
                        PrintWriter CurrentProdFileEmpty = new PrintWriter(Currentprod);
                        PrintWriter Writer = new PrintWriter(CurrentProdFileEmpty);
                        Writer.print("");
                        Writer.close();


                    } else {
                        System.out.println("Email and password not valid!");
                    }

            }

        }while(choice!='Q');

    }
}
