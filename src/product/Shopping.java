package product;

import Inventory.Cart;
import Inventory.Invoice;
import filehandler.Filehandler;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import filehandler.Filehandler;
import java.util.Scanner;

public class Shopping {

    Filehandler filehandler=new Filehandler();
    Scanner scan=new Scanner(System.in);

    LocalTime timeObj = LocalTime.now();
    LocalDate dateObj = LocalDate.now();



    public double displayUserPrefrenceProduct(ArrayList<Product>list1,String category,double price)
    {

        for(int i=0;i<list1.size();i++)
        {
            if((list1.get(i).category).equalsIgnoreCase(category))
            {
                price=list1.get(i).price;
                System.out.println("BRAND :-" +list1.get(i).brand);
                System.out.println("MODEL :-" +list1.get(i).model);
                System.out.println("PRICE :-" +list1.get(i).price);

            }
            System.out.println();
        }

        return price;

    }

    public void productPurchase_Customer(ArrayList<Product>list1,double price,String category,String email)
    {
        System.out.println("Enter the brand and model you want to buy");
        String brandName = scan.next();
        String modelName  = scan.next();

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
        Cart cart = new Cart(email,brandName,category,modelName,price,dateObj.toString(),timeObj.toString(),randomNumber);


        File productFIle=new File(("./File_db/z-current-product_db.txt"));

        filehandler.addCart(cart,productFIle);


        File userHistoryFIle = new File("./File_db/zcartuserHistory_db.txt");
        filehandler.addCart(cart,userHistoryFIle);

        System.out.println("Product added successfully to Cart");

        return;
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
                    double price = 0;

                    if(category==1)
                    {
                        System.out.println("CATEGORY:---MOBILE----------- ");
                        addCategory="Mobile";
                        price=displayUserPrefrenceProduct(list1,addCategory,price);

                    }
                    else if(category==2)
                    {
                        System.out.println("CATEGORY:---LAPTOP----------- ");
                        addCategory="Laptop";
                        price=displayUserPrefrenceProduct(list1,addCategory,price);
                    }
                    else if(category==3)
                    {
                        System.out.println("CATEGORY:---Tablet----------- ");
                        addCategory="Tablet";
                        price=displayUserPrefrenceProduct(list1,addCategory,price);
                    }

                    System.out.println("Do you want to buy Something:-PRESS:-1      PRESS:-2 QUIT");
                    category=scan.nextInt();

                    if(category==1)
                    {
                        productPurchase_Customer(list1,price,addCategory,email);
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
