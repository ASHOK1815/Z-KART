package Inventory;

import java.util.ArrayList;

public class Invoice {

    Double TotalPrice;


    public Invoice(ArrayList<Cart> listProduct, String email, String date, String time)
    {

        System.out.println("------------------------------Z-KART-----------------------------------------");
        System.out.println("----------------------Everything at one place--------------------------------");
        System.out.println();
        System.out.println("               TAX Invoice/Bill of Supply/Cash Memo                            ");
        System.out.println();
        System.out.println("USER-EMAIL "+email);
        System.out.println("DATE:- "+date);
        System.out.println("Time:- "+time);
        System.out.println();
        TotalPrice= Double.valueOf(0);

        int listSize=listProduct.size();
        for(int i=0;i<listSize;i++)
        {
            if(listProduct.get(i).email.equals(email))
            {
                TotalPrice+=listProduct.get(i).price;
                System.out.println("Invoice Number "+listProduct.get(i).randomInt);
                System.out.println("Date:-"+listProduct.get(i).Date);
                System.out.println("Brand:--"+listProduct.get(i).brand+"  "+"Model:--"+listProduct.get(i).model+" "+"Price:--"+listProduct.get(i).price);
            }

            System.out.println();


        }


        System.out.println("TOTAL PAY AMOUNT:--"+TotalPrice+"  Rs only");

        System.out.println("----------------------Thanks for shopping------------------------------------");

    }


}