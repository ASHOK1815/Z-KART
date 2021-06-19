package product;

import Inventory.Cart;
import Inventory.Invoice;
import customer.Customer;
import filehandler.Filehandler;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import filehandler.Filehandler;
import java.util.Scanner;
import filehandler.Filehandler;

public class Shopping {

    Filehandler filehandler=new Filehandler();

    Scanner scan=new Scanner(System.in);

    LocalTime timeObj = LocalTime.now();
    LocalDate dateObj = LocalDate.now();



    public void displayUserPrefrenceProduct(ArrayList<Product>list1,String category)
    {

        for(int i=0;i<list1.size();i++)
        {
            if((list1.get(i).category).equalsIgnoreCase(category))
            {
                System.out.println("BRAND :-" +list1.get(i).brand);
                System.out.println("MODEL :-" +list1.get(i).model);
                System.out.println("PRICE :-" +list1.get(i).price);

            }
            System.out.println();
        }


    }

    public int highestStock(ArrayList<Product>productsList)
    {
        int size=productsList.size();
        int value=0;
        for(int i=0;i<size;i++)
        {
            if(productsList.get(i).stock>value)
            {
                value=productsList.get(i).stock;
            }

        }

        return value;

    }





    public void productPurchase_Customer(ArrayList<Product>list1,String category,String email)
    {
        double price=0;
        System.out.println("Enter the brand and model you want to buy");
        String brandName = scan.next();
        String modelName  = scan.next();


        int highestStockNumber=highestStock(list1);
        boolean flag=false;

        if(highestStockNumber!=0)
        {
            int size=list1.size();
            for(int i=0;i<size;i++)
            {
                if(brandName.equalsIgnoreCase(list1.get(i).brand)  &&  modelName.equalsIgnoreCase(list1.get(i).model))
                {
                    price=list1.get(i).price;
                    if(list1.get(i).stock==highestStockNumber)
                    {
                        flag=true;
                        break;
                    }
                }
            }

        }

        if(price==0)
        {
            System.out.println("Invalid Input..");
            return;
        }


        for(int i=0;i<list1.size();i++)
        {
            if((list1.get(i).brand).equalsIgnoreCase(brandName) && (list1.get(i).model).equalsIgnoreCase(modelName))
            {
                if(list1.get(i).stock==0)
                {
                    System.out.println("Product is out of stock");
                    return;
                }
                else
                {
                    list1.get(i).stock=list1.get(i).stock-1;
                }

            }
        }

        File cartUpdate= new File("./File_db/z-kart_db.txt");
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(cartUpdate,true);
            PrintWriter writer = new PrintWriter(cartUpdate);
            writer.print("");
            writer.close();
            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
            for(int i=0;i<list1.size();i++)
            {
                bufferedWriter.write(list1.get(i).toString());
            }

            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        int randomNumber;

        int min = 1000;
        int max = 9999;


        randomNumber = (int)Math.floor(Math.random()*(max-min+1)+min);

        File productFIle=new File(("./File_db/z-current-product_db.txt"));
        File userHistoryFIle = new File("./File_db/zcartuserHistory_db.txt");
        if(flag)
        {
            Cart cart = new Cart(email,brandName,category,modelName,price/10.0,dateObj.toString(),timeObj.toString(),randomNumber);
            filehandler.addCart(cart,productFIle);
            filehandler.addCart(cart,userHistoryFIle);
        }
        else
        {
            Cart cart = new Cart(email,brandName,category,modelName,price,dateObj.toString(),timeObj.toString(),randomNumber);
            filehandler.addCart(cart,productFIle);
            filehandler.addCart(cart,userHistoryFIle);
        }

        System.out.println("Product added successfully to Cart");

        return;
    }

    boolean getTotal(ArrayList<Cart>totalProductCart)
    {
        double totalAmount=0;
        for(int i=0;i<totalProductCart.size();i++)
        {
            totalAmount+=totalProductCart.get(i).price;

        }

        if((int)totalAmount>=20000)
        {
            return  true;
        }

        return false;

    }


    public void shoppingDetials(String email)
    {
        char Choice;
        do
        {
            System.out.println("------Welcome to Z-kart---------");
            System.out.println("1:Shopping");
            System.out.println("2:ORDER-INVOICE");
            System.out.println("3:ORDER-HISTORY");
            System.out.println("4:Log-Out");
            Choice = scan.next().charAt(0);
            int counter=0;
            switch (Choice)
            {
                case '1':
                    ArrayList<Product> list1=filehandler.readFileDataProduct();
                    System.out.println("Enter the category you want to see");
                    System.out.println("Type:1-MOBILE");
                    System.out.println("Type:2-LAPTOP");
                    System.out.println("Type:3-TABLET");
                    int category=scan.nextInt();
                    String addCategory = null;


                    if(category==1)
                    {
                        System.out.println("CATEGORY:---MOBILE----------- ");
                        addCategory="Mobile";
                        displayUserPrefrenceProduct(list1,addCategory);

                    }
                    else if(category==2)
                    {
                        System.out.println("CATEGORY:---LAPTOP----------- ");
                        addCategory="Laptop";
                        displayUserPrefrenceProduct(list1,addCategory);
                    }
                    else if(category==3)
                    {
                        System.out.println("CATEGORY:---Tablet----------- ");
                        addCategory="Tablet";
                        displayUserPrefrenceProduct(list1,addCategory);
                    }

                    System.out.println("Do you want to buy Something:-PRESS:-1      PRESS:-2 QUIT");
                    category=scan.nextInt();

                    if(category==1)
                    {
                        productPurchase_Customer(list1,addCategory,email);
//                        counter++;
//                        ArrayList<Cart>totalProductCart=filehandler.readCurrentProductUser();
//                        if(counter==3 || getTotal(totalProductCart))
//                        {
//                            int randomNumber;
//                            int min = 100000;
//                            int max = 999999;
//                            randomNumber = (int)Math.floor(Math.random()*(max-min+1)+min);
//                            ArrayList<Customer>allCustomer =filehandler.readFileDataCustomer();
//                            ArrayList<Customer>temp=new ArrayList<Customer>();
//
//
//                            String emailId;
//                            String password;
//                            String name;
//                            long mobileNumber;
//
//
//                            for(int i=0;i<allCustomer.size();i++)
//                            {
//                                if(allCustomer.get(i).email.equals(email))
//                                {
//                                      emailId=email;
//                                      password=allCustomer.get(i).password;
//                                      name=allCustomer.get(i).name;
//                                      mobileNumber=allCustomer.get(i).mobileNumber;
//                                      Customer modifyCustomer=new Customer(emailId,password,name,mobileNumber,randomNumber);
//                                      temp.add(modifyCustomer);
//
//
//                                 }
//                                else
//                                {
//                                    emailId=email;
//                                    password=allCustomer.get(i).password;
//                                    name=allCustomer.get(i).name;
//                                    mobileNumber=allCustomer.get(i).mobileNumber;
//                                    Customer modifyCustomer=new Customer(emailId,password,name,mobileNumber,0);
//                                    temp.add(modifyCustomer);
//
//                                }
//
//                            }
//
//
//                            File file=new File("./File_db/zusers_db.txt");
//                            filehandler.fileDataVanisher(file);
//
//                            for(int i=0;i<allCustomer.size();i++)
//                            {
//                                filehandler.addUser(allCustomer.get(i),file);
//                            }
//
//                            System.out.println("Tokens has been generated");







  //                      }
                    }
                    else
                    {

                        System.out.println("------Thanks for visiting Z-KART--------");

                    }
                    break;

                case '2':
                    ArrayList<Cart> listCart =filehandler.readCurrentProductUser();
                    Invoice invoice=new Invoice(listCart,email,timeObj.toString(),dateObj.toString());
                    break;



                case '3':
                    ArrayList<Cart> listHistoryCart =filehandler.readHistoryProductUser();
                    Invoice invoiceObject=new Invoice(listHistoryCart,email,timeObj.toString(),dateObj.toString());
                    break;

                default:

                      File file = new File("./File_db/z-current-product_db.txt");
                      filehandler.fileDataVanisher(file);





            }

        } while (Choice !='4');





    }




}
