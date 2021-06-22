package com.zcart;

import customer.Customer;
import product.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import filehandler.*;


public class Admin {

    Filehandler filehandler=new Filehandler();
    Scanner scan=new Scanner(System.in);
    public void displayCurrentStockDetails()
    {
        ArrayList<Product> list1=filehandler.readFileDataProduct();



        System.out.println("----------------WELCOME TO THE ADMIN SECTION---------------------");
        System.out.println();
        System.out.println("-----------LIST OF ITEMS  AND THE CURRENT STOCK-------------------");
        System.out.println();
        System.out.println("BRAND   "+"    MODEL   "+"    STOCK  " );
        System.out.println();


         int listSize=list1.size();
         boolean flag=true;
         for(int i=0;i<listSize;i++)
        {
            if(list1.get(i).stock<10)
            {
                flag=false;
                System.out.println(list1.get(i).brand+"     "+list1.get(i).model+"           "+list1.get(i).stock);
            }
        }

         if(flag)
         {
             System.out.println("----------------ALL PRODUCT IS AVAILABLE WITH MORE THAN 10 STOCK---------------------");
             System.out.println();
         }


    }


    public void adminUpdateStock()
    {
            String brandname=null;
            String modelname=null;
            System.out.println("Enter the Brand Name and Model");
            brandname=scan.next();
            modelname=scan.next();
            int stockvalue=0;
            System.out.println("How many stock you want to add in  "+brandname);
            stockvalue=scan.nextInt();
            ArrayList<Product> list1=filehandler.readFileDataProduct();

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
                return;
            }
            else
            {


                File AdminUpdate= new File("./File_db/product_db.txt");
                FileWriter fileWriter = null;

                try {
                    fileWriter = new FileWriter(AdminUpdate,true);
                    PrintWriter writer = new PrintWriter(AdminUpdate);
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

                } catch (IOException e) {
                    e.printStackTrace();
                }


                return;
            }




    }


   public boolean adminDefault_PasswordChanger()
   {

       String firstPassword;
       String secondPassword;
       System.out.println("--WELCOME TO THE ADMIN SECTION---");
       System.out.println("For Security Purpose Kindly change Your Password and relogin again");
       System.out.println("Enter new password");
       firstPassword=scan.next();
       System.out.println("Enter password again");
       secondPassword=scan.next();
       PassswordVerifier passswordVerifier =new PassswordVerifier();

       if(!passswordVerifier.passwordValidChecker(firstPassword,secondPassword))
       {
           return false;
       }
       else
       {
           System.out.println("Password match successfully!");

       }

       firstPassword = passswordVerifier.encryptPassword(firstPassword);



       ArrayList<Customer> list1=filehandler.readFileDataCustomer();
       for(int i=0;i<list1.size();i++)
       {
           if(list1.get(i).name.equals("Admin"))
           {
               list1.get(i).password=firstPassword;
           }
       }

       File AdminUpdate= new File("./File_db/zusers_db.txt");
       FileWriter fileWriter = null;

       try {
           fileWriter = new FileWriter(AdminUpdate,true);
           PrintWriter writer = new PrintWriter(AdminUpdate);
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

       return  true;

   }




}
