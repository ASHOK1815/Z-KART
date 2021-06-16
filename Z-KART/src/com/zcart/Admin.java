package com.zcart;

import Inventory.Product;

import java.util.ArrayList;

public class Admin {

     Admin()
     {

         System.out.println("----------------WELCOME TO THE ADMIN SECTION---------------------");
         System.out.println();
         System.out.println("-----------LIST OF ITEMS  AND THE CURRENT STOCK-------------------");
         System.out.println();
         System.out.println("BRAND   "+"    MODEL   "+"    STOCK  " );


     }


     void displayCurrentStockDetails(ArrayList<Product> list1)
     {


         for(int i=0;i<list1.size();i++)
         {
             if(list1.get(i).stock<10)
             {
                 System.out.println(list1.get(i).brand+"     "+list1.get(i).model+"           "+list1.get(i).stock);
             }
         }


     }


}
