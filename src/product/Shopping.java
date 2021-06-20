package product;

import Inventory.Cart;
import Inventory.Invoice;
import customer.Customer;
import customer.CustomerCoupen;
import filehandler.Filehandler;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import filehandler.Filehandler;

import java.util.InputMismatchException;
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



        int randomNumber;

        int min = 1000;
        int max = 9999;


        randomNumber = (int)Math.floor(Math.random()*(max-min+1)+min);

        File productFIle=new File(("./File_db/z-current-product_db.txt"));

        if(flag)
        {
            price=price-(price/10.0);

            Cart cart = new Cart(email,brandName,category,modelName,price,dateObj.toString(),timeObj.toString(),randomNumber);
            filehandler.addCart(cart,productFIle);

        }
        else
        {
            Cart cart = new Cart(email,brandName,category,modelName,price,dateObj.toString(),timeObj.toString(),randomNumber);
            filehandler.addCart(cart,productFIle);

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
            System.out.println("yes product available");
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
            System.out.println("2:ORDER-HISTORY");
            System.out.println("3:CHECK-OUT");
            System.out.println("4:SHOW-CART");
            System.out.println("5:Log-Out");
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


                    try {

                        category=scan.nextInt();

                    } catch (InputMismatchException e) {
                        System.out.print("Caution:-- Please enter number between 1-2\n");
                        scan.next();

                        break;
                    }

                    if(category==1)
                    {
                        productPurchase_Customer(list1,addCategory,email);
                        counter++;
                        ArrayList<Cart>totalProductCart=filehandler.readCurrentProductUser();
                       if(counter==3 || getTotal(totalProductCart))
                      {
                            int randomNumber;
                            int min = 100000;
                            int max = 999999;
                            randomNumber = (int)Math.floor(Math.random()*(max-min+1)+min);
                            ArrayList<Customer>allCustomer = filehandler.readFileDataCustomer();
                            ArrayList<Customer>arrayList=new ArrayList<Customer>();

                          File file = new File("./File_db/zusers_db.txt");
                          try{

                              FileWriter fileWriter = new FileWriter(file, false);
                              BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                              for(int i=0;i<allCustomer.size();i++)
                              {
                                  if(email.equalsIgnoreCase(allCustomer.get(i).getEmail()))
                                  {
                                      CustomerCoupen customerCoupen=new CustomerCoupen(email,allCustomer.get(i).getPassword(),allCustomer.get(i).getName(),allCustomer.get(i).mobileNumber,randomNumber);
                                      bufferedWriter.write(customerCoupen.toString());
                                  }
                                  else
                                  {
                                      bufferedWriter.write(allCustomer.get(i).toString());
                                  }

                              }
                              bufferedWriter.close();
                              fileWriter.close();
                          } catch (IOException e)
                          {
                              e.printStackTrace();
                          }

                          System.out.println("Tokens has been generated");



                        }
                    }
                    else
                    {

                        System.out.println("------Thanks for visiting Z-KART--------");

                    }
                    break;


                case '2':
                    ArrayList<Cart> listHistoryCart =filehandler.readHistoryProductUser();

                    ArrayList<Cart> showCart=new ArrayList<Cart>();

                    for(int i=0;i<listHistoryCart.size();i++)
                    {
                        if(listHistoryCart.get(i).email.equals(email))
                        {
                            showCart.add(listHistoryCart.get(i));
                        }


                    }


                    Invoice invoiceObject=new Invoice(showCart,email,timeObj.toString(),dateObj.toString(),0);
                    break;


                case '3':

                       ArrayList<Cart> cartProduct =filehandler.readCurrentProductUser();


                        File userHistoryFIle = new File("./File_db/zcartuserHistory_db.txt");


                        int increment=0;
                        for(int i=0;i<cartProduct.size();i++)
                        {
                            if(cartProduct.get(i).email.equals(email))
                            {

                                filehandler.addCart(cartProduct.get(i),userHistoryFIle);
                                increment++;
                            }


                        }

                        if(increment==0)
                        {
                            System.out.println("------PLEASE ADD SOME PRODUCT IN CART--------");
                            break;
                        }
                        Invoice invoice=new Invoice(cartProduct,email,timeObj.toString(),dateObj.toString());

                        ArrayList<Product> cartDataList=filehandler.readFileDataProduct();

                        for(int i=0;i<cartDataList.size();i++)
                        {
                            for(int j=0;j<cartProduct.size();j++)
                            {

                                if( (cartDataList.get(i).brand.equalsIgnoreCase(cartProduct.get(j).brand))  &&  (cartDataList.get(i).model.equalsIgnoreCase(cartProduct.get(j).model)) )
                                {

                                      cartDataList.get(i).stock=cartDataList.get(i).stock-1;
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
                        for(int i=0;i<cartDataList.size();i++)
                        {
                            bufferedWriter.write(cartDataList.get(i).toString());
                        }

                        bufferedWriter.close();
                        fileWriter.close();

                      } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                        File file = new File("./File_db/z-current-product_db.txt");
                        filehandler.fileDataVanisher(file);

                        for(int i=0;i<cartProduct.size();i++)
                        {
                            if(!cartProduct.get(i).email.equals(email))
                            {
                                filehandler.addCart(cartProduct.get(i),file);
                            }

                        }



                    break;



                case '4':
                    ArrayList<Cart> listCart =filehandler.readCurrentProductUser();
                    ArrayList<Cart> showCartProduct=new ArrayList<Cart>();

                    for(int i=0;i<listCart.size();i++)
                    {
                        if(listCart.get(i).email.equals(email))
                        {
                            showCartProduct.add(listCart.get(i));
                        }


                    }


                    Invoice invoiceObj=new Invoice(showCartProduct,email,timeObj.toString(),dateObj.toString(),0.0);
                    break;


                default:
                    break;







            }

        } while (Choice !='5');





    }




}
