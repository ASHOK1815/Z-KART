package product;

import Inventory.*;
import customer.Customer;
import customer.CustomerCoupen;
import filehandler.Filehandler;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import filehandler.Filehandler;

import filehandler.Filehandler;
import filehandler.IdTracker;

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
        int productId=0;
        boolean flag=false;

        if(highestStockNumber!=0)
        {
            int size=list1.size();
            for(int i=0;i<size;i++)
            {
                if(brandName.equalsIgnoreCase(list1.get(i).brand)  &&  modelName.equalsIgnoreCase(list1.get(i).model))
                {
                    price=list1.get(i).price;
                    productId=list1.get(i).id;
                    if(list1.get(i).stock==highestStockNumber)
                    {
                        flag=true;
                        break;
                    }
                }
            }

        }

        if(price==0 && productId==0)
        {
            System.out.println("Invalid Input..");
            return;
        }




        File productFIle=new File(("./File_db/z-current-product_db.txt"));

        if(flag)
        {
            price=price-(price/10.0);
            Cart cart = new Cart(email,productId,price,dateObj.toString(),timeObj.toString());
            filehandler.addCart(cart,productFIle);
        }
        else
        {
            Cart cart = new Cart(email,productId,price,dateObj.toString(),timeObj.toString());
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
            System.out.println("5:Empty Cart");
            System.out.println("6:Log-Out");
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
                    }
                    else
                    {
                        System.out.println("------Thanks for visiting Z-KART--------");
                    }
                    break;


                case '2':
                    ArrayList<Order> listHistoryCart =filehandler.readHistoryProductUser();

                    ArrayList<Order> showCart=new ArrayList<Order>();

                    for(int i=0;i<listHistoryCart.size();i++)
                    {
                        if(listHistoryCart.get(i).email.equals(email))
                        {
                            showCart.add(listHistoryCart.get(i));
                        }
                    }

                    Invoice invoiceObject=new Invoice(showCart,email,timeObj.toString(),dateObj.toString(),"0");
                    break;

                case '3':

                        File productsFile= new File("./File_db/product_db.txt");
                        File ordersFile = new File("./File_db/order_db.txt");
                        File orderProductsFile = new File("./File_db/order_products_db.txt");
                        File idTrackerFile = new File("./File_db/id_tracker_db.txt");
                        File cartProductsFile= new File("./File_db/z-current-product_db.txt");
                        File couponFile= new File("./File_db/coupon_db.txt");
                        File usersFile= new File("./File_db/zusers_db.txt");


                    ArrayList<Cart> cartProducts =filehandler.readCurrentProductUser();
                       ArrayList<IdTracker> idTracker =filehandler.readLastId();
                       int newOrderId = 0;
                       for(int i=0;i<idTracker.size();i++){
                           if(idTracker.get(i).name.equals("order")){
                               newOrderId=idTracker.get(i).lastId+1;
                               idTracker.get(i).lastId = newOrderId;
                               break;
                           }
                       }

                       int increment=0;
                       for(int i=0;i<cartProducts.size();i++)
                       {
                            if(cartProducts.get(i).email.equals(email))
                            {
                                increment++;
                            }
                       }

                       if(increment==0)
                       {
                            System.out.println("------PLEASE ADD SOME PRODUCT IN CART--------");
                            break;
                       }

//                       Update Product Stocks
                        ArrayList<Product> productsList=filehandler.readFileDataProduct();
                        Set<Integer> cartIteamsOutOfStock= new HashSet<Integer>();
                        int is_stock_avialable = 1;
                        String outOfStockMsg = "";
                        for(int i=0;i<productsList.size();i++)
                        {
                            for(int j=0;j<cartProducts.size();j++)
                            {
                                if( cartProducts.get(j).email.equals(email) && productsList.get(i).id==cartProducts.get(j).productId )
                                {
                                    if(productsList.get(i).stock>=1){
                                        productsList.get(i).stock -= 1;
                                    }else{
                                        cartIteamsOutOfStock.add(j);
                                        outOfStockMsg += String.format("------%s %s NOT AVAILABLE IN STOCK--------\n",productsList.get(i).brand,productsList.get(i).model);
                                        is_stock_avialable=0;
                                    }
                                }
                            }
                        }

                        if(is_stock_avialable==0){
                            System.out.println(String.format("------!!CHECKOUT ABORT!!--------"));
                            System.out.println(outOfStockMsg);
                            System.out.println(String.format("------THESE OUT OF STOCK PRODUCTS WILL BE REMOVED FROM YOUR CART--------"));
                            filehandler.fileDataVanisher(cartProductsFile);
                            for(int i=0;i<cartProducts.size();i++){
                                if(!cartIteamsOutOfStock.contains(i)){
                                    filehandler.addCart(cartProducts.get(i), cartProductsFile);
                                }
                            }
                            break;
                        }

                        filehandler.fileDataVanisher(productsFile);
                        for(int j=0;j<productsList.size();j++)
                        {
                            filehandler.addProduct(productsList.get(j), productsFile);
                        }
//                        End


//                    Add Order Products
                        Double totalAmount= Double.valueOf(0);
                        for(int i=0;i<cartProducts.size();i++)
                        {
                            if(cartProducts.get(i).email.equals(email))
                            {
                                OrderProduct orderProductObj = new OrderProduct(newOrderId, cartProducts.get(i).productId, cartProducts.get(i).price);
                                totalAmount += cartProducts.get(i).price;
                                filehandler.addOrderProduct(orderProductObj, orderProductsFile);
                            }
                        }
//                    End

//                    Check for Discount Coupon
                        ArrayList<Coupon> allCoupons= filehandler.readCoupons();
                        ArrayList<Customer> allCustomers= filehandler.readFileDataCustomer();
                        ArrayList<Order> allOrders= filehandler.readHistoryProductUser();
                        Customer userObj=null;
                        ArrayList<Coupon> userCoupons=new ArrayList<Coupon>();
                        int discountPercentage=0;
                        double amountSaved=0;
                        for(int i=0;i<allCoupons.size();i++){
                            if(allCoupons.get(i).email.equals(email)){
                                userCoupons.add(allCoupons.get(i));
                            }
                        }
                        for(int i=0;i<allCustomers.size();i++){
                            if(allCustomers.get(i).email.equals(email)){
                                userObj=allCustomers.get(i);
                                break;
                            }
                        }
                        int isCouponGenerated=0;
                        int newCouponId=0;
                        for(int i=0;i<idTracker.size();i++){
                            if(idTracker.get(i).name.equals("coupon")){
                                newCouponId=idTracker.get(i).lastId+1;
                            }
                        }
                        int totalUserOrders=0;
                        for(int i=0;i<allOrders.size();i++){
                            if( allOrders.get(i).email.equals(email) ){
                                totalUserOrders += 1;
                            }
                        }
                        if(totalUserOrders>=2 && userObj.isInitialCouponGenerated==0){
                            int couponCode = (int)Math.floor(Math.random()*(999999-100000+1)+100000);
                            Coupon newUserCoupon = new Coupon(newCouponId, email, newOrderId, couponCode, LocalTime.now().toString(), LocalDate.now().toString());
                            filehandler.addCoupon(newUserCoupon, couponFile);
                            isCouponGenerated=1;
                            filehandler.fileDataVanisher(usersFile);
                            for(int i=0;i<allCustomers.size();i++){
                                if(allCustomers.get(i).email.equals(email)){
                                    allCustomers.get(i).isInitialCouponGenerated=1;
                                }
                                filehandler.addUser(allCustomers.get(i), usersFile);
                            }
                        }else if(totalAmount>=20000){
                            int couponCode = (int)Math.floor(Math.random()*(999999-100000+1)+100000);
                            Coupon newUserCoupon = new Coupon(newCouponId, email, newOrderId, couponCode, LocalTime.now().toString(), LocalDate.now().toString());
                            filehandler.addCoupon(newUserCoupon, couponFile);
                            isCouponGenerated=1;
                        }
                        if(isCouponGenerated==1){
                            for(int i=0;i<idTracker.size();i++){
                                if(idTracker.get(i).name.equals("coupon")){
                                    idTracker.get(i).lastId=newCouponId;
                                }
                            }
                        }
                        if(userCoupons.size()!=0){
                            Set<Integer> deleteCoupons= new HashSet<Integer>();
                            for(int j=0;j<userCoupons.size();j++){
                                int noOrdersAfterCouponGeneration= 0;
                                for(int i=0;i<allOrders.size();i++){
                                    if(allOrders.get(i).email.equals(email) && allOrders.get(i).id>userCoupons.get(j).getOnOrderId){
                                        noOrdersAfterCouponGeneration += 1;
                                    }
                                }
                                if(noOrdersAfterCouponGeneration<3){
                                    System.out.println("DO YOU WANT TO USE COUPON? PRESS 1 TO USE, ANY KEY TO SKIP");
                                    int isCouponApplied=0;
                                    Scanner scan = new Scanner(System.in);
                                    isCouponApplied = scan.nextInt();
                                    if(isCouponApplied==1){
                                        Random r = new Random();
                                        discountPercentage = r.nextInt(30-20) + 20;
                                        amountSaved = (totalAmount*discountPercentage)/100;
                                        totalAmount -= (totalAmount*discountPercentage)/100;
                                    }
                                    break;
                                }else{
                                    deleteCoupons.add(userCoupons.get(j).id);
                                }
                            }
                            allCoupons= filehandler.readCoupons();
                            filehandler.fileDataVanisher(couponFile);
                            for(int i=0;i<allCoupons.size();i++){
                                if(!(allCoupons.get(i).email.equals(email) && deleteCoupons.contains(allCoupons.get(i).id) ) ){
                                    filehandler.addCoupon(allCoupons.get(i), couponFile);
                                }
                            }
                        }
//                    End

//                    Add Order
                        Order order=new Order(newOrderId, email, discountPercentage, totalAmount, LocalTime.now().toString(), LocalDate.now().toString());
                       filehandler.addOrder(order, ordersFile);
                       filehandler.fileDataVanisher(idTrackerFile);
                        for(int i=0;i<idTracker.size();i++){
                            filehandler.addLastId(idTracker.get(i), idTrackerFile);
                        }
//                   End

                     Invoice invoice=new Invoice(cartProducts,email,timeObj.toString(),dateObj.toString());
                    if(discountPercentage>0){
                        System.out.println(String.format("----------------------You have Saved %s------------------------------------", String.valueOf(amountSaved)));
                    }
                    System.out.println("----------------------Thanks for shopping------------------------------------");


//                        Remove Cart Objects for That User
                        filehandler.fileDataVanisher(cartProductsFile);
                        for(int i=0;i<cartProducts.size();i++)
                        {
                            if(!cartProducts.get(i).email.equals(email))
                            {
                                filehandler.addCart(cartProducts.get(i), cartProductsFile);
                            }
                        }
//                        End

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

                case '5':
                    ArrayList<Cart> productsInCart =filehandler.readCurrentProductUser();
                    File cartFile= new File("./File_db/z-current-product_db.txt");
                    filehandler.fileDataVanisher(cartFile);
                    int flag=0;
                    for(int i=0;i<productsInCart.size();i++){
                        if(!productsInCart.get(i).email.equals(email)){
                            filehandler.addCart(productsInCart.get(i), cartFile);
                        }else{
                            flag=1;
                        }
                    }
                    if(flag==1){
                        System.out.println("------YOUR CART HAS BEEN EMPTIED SUCCESSFULLY--------\n");
                    }else{
                        System.out.println("------YOUR CART IS ALREADY EMPTY--------\n");
                    }

                default:
                    break;


            }

        } while (Choice !='6');





    }




}
