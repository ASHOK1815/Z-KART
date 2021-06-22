package Inventory;

import product.Product;

import filehandler.Filehandler;

import java.util.ArrayList;

public class Invoice {

    Double TotalPrice;
    Filehandler fileHandler=new Filehandler();

    public Invoice(ArrayList<Cart> listProduct, String email, String date, String time)  // Displaying  the Invoice
    {

        System.out.println("------------------------------Z-KART-----------------------------------------");
        System.out.println("----------------------Everything at one place--------------------------------");
        System.out.println();
        System.out.println("               TAX Invoice/Bill of Supply/Cash Memo                            ");
        System.out.println();
        System.out.println("USER-EMAIL "+email);
        System.out.println("DATE:- "+date);
        System.out.println("Time:- "+time);
        int invoiceNumber = (int)Math.floor(Math.random()*(9999-1000+1)+1000);
        System.out.println("Invoice Number "+invoiceNumber);
        System.out.println();


        int listSize=listProduct.size();
        ArrayList<Product> products=fileHandler.readFileDataProduct();
        for(int i=0;i<listSize;i++)
        {
            if(listProduct.get(i).email.equals(email))
            {

                System.out.println("Date:-"+listProduct.get(i).Date);
                int productIndex=0;
                for(int j=0;j<products.size();j++){
                    if(products.get(j).id==listProduct.get(i).productId){
                        productIndex=j;
                        break;
                    }
                }
                System.out.println("Brand:--"+products.get(productIndex).brand+"  "+"Model:--"+products.get(productIndex).model+" "+"Price:--"+listProduct.get(i).price);
            }

            System.out.println();
        }




    }

    public Invoice(ArrayList<Order> listOrders, String email, String date, String time, String counter) // Displaying  the History Data
    {

        if(listOrders.size()==0)
        {
            System.out.println("----------------------NO-PRODUCT HISTORY YET---------------------------------");
            System.out.println();
        }
        else
        {
            System.out.println("------------------------------Z-KART-----------------------------------------");
            System.out.println("----------------------Everything at one place--------------------------------");
            System.out.println();
            System.out.println("USER-EMAIL "+email);
            System.out.println();

            int listSize=listOrders.size();
            for(int i=0;i<listSize;i++)
            {
                if(listOrders.get(i).email.equals(email))
                {
                    TotalPrice= Double.valueOf(0);
                    System.out.println("------------------------------OrderId-"+listOrders.get(i).id+"-----------------------------------------");
                    System.out.println("Date:-"+listOrders.get(i).Date);
                    System.out.println("\nProducts:");

                    ArrayList<OrderProduct> orderProducts= fileHandler.readOrderProducts();
                    ArrayList<Product> products=fileHandler.readFileDataProduct();
                    for(int j=0;j<orderProducts.size();j++){
                        if(orderProducts.get(j).orderId==listOrders.get(i).id){
                            TotalPrice+=orderProducts.get(j).price;
                            int productIndex=0;
                            for(int k=0;k<products.size();k++){
                                if(products.get(k).id==orderProducts.get(j).productId){
                                    productIndex=k;
                                    break;
                                }
                            }
                            System.out.println("-> Brand:--"+products.get(productIndex).brand+"  "+"Model:--"+products.get(productIndex).model+" "+"Price:--"+orderProducts.get(j).price);
                        }
                    }
                    System.out.println("\nTOTAL PAY AMOUNT FOR THIS ORDER:--"+TotalPrice+"  Rs only");
                    System.out.println("--------------------------------------------------------------------");
                }
                System.out.println();
            }


            System.out.println("----------------------Z-KART-----------------------------------");
            System.out.println();
        }





    }

    public Invoice(ArrayList<Cart> listProduct, String email, String date, String time, double number)     // Displaying  the cart details
    {

        if(listProduct.size()==0)
        {
            System.out.println("------------------YOUR CART IS EMPTY------------------------------------");
            System.out.println();
        }
        else
        {

            System.out.println("------------------------------Z-KART-----------------------------------------");
            System.out.println("----------------------Everything at one place--------------------------------");
            System.out.println();
            System.out.println("               PRODUCT AVAILABLE IN YOUR ORDER CART                           ");
            System.out.println();
            System.out.println("USER-EMAIL "+email);
            System.out.println("DATE:- "+date);
            System.out.println("Time:- "+time);
            System.out.println();


            int listSize=listProduct.size();
            for(int i=0;i<listSize;i++)
            {
                if(listProduct.get(i).email.equals(email))
                {
                    System.out.println("Date:-"+listProduct.get(i).Date);
                    ArrayList<Product> products=fileHandler.readFileDataProduct();
                    int productIndex=0;
                    for(int j=0;j<products.size();j++){
                        if(products.get(j).id==listProduct.get(i).productId){
                            productIndex=j;
                            break;
                        }
                    }
                    System.out.println("Brand:--"+products.get(productIndex).brand+"  "+"Model:--"+products.get(productIndex).model+" "+"Price:--"+listProduct.get(i).price);
                }

                System.out.println();
            }

            System.out.println("----------------------Z-KART-----------------------------------");
            System.out.println();

        }

        }



}