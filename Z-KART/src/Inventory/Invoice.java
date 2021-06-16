package Inventory;

import java.util.ArrayList;
public class Invoice {

    String email;
    Double TotalPrice= Double.valueOf(0);
    public Invoice(String username,ArrayList<Cart> list2,String Email)
    {

        System.out.println("------------------------------Z-KART-----------------------------------------");
        System.out.println("----------------------Everything at one place--------------------------------");
        System.out.println();
        System.out.println("               TAX Invoice/Bill of Supply/Cash Memo                            ");
        System.out.println();
        System.out.println("USER-NAME "+username);
        System.out.println("USER-EMAIL "+Email);



        for(int i=0;i<list2.size();i++)
        {
            if(list2.get(i).email.equals(Email))
            {
                TotalPrice+=list2.get(i).price;
              System.out.println("Brand "+list2.get(i).brand+"  "+"Model "+list2.get(i).model+" "+"Price "+list2.get(i).price);
            }


        }
        System.out.println("TOTAL PAY AMOUNT:--"+TotalPrice+"  Rs only");

        System.out.println("----------------------Thanks for shopping------------------------------------");

    }


}
