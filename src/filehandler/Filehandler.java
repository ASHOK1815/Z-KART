package filehandler;

import Inventory.Cart;
import Inventory.Coupon;
import Inventory.Order;
import Inventory.OrderProduct;
import customer.Customer;
import product.Product;

import java.io.*;
import java.util.ArrayList;

public class Filehandler {

    public void addLastId(IdTracker idTracker, File file) {// adding the tracking of the last id in the file

        try{
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(idTracker.toString());
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e)
        {
            System.out.println("Problem in adding last id data in the  file !");
        }
    }

    public void addCoupon(Coupon coupon, File file) { // adding coupen with the valid person details

        try{
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(coupon.toString());
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e)
        {
            System.out.println("Problem in adding coupen to  file !");
        }
    }


    public void addCart(Cart cart, File file) {   // adding date to the cart(product) file

        try {
            FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
            bufferedWriter.write(cart.toString());
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Problem in adding data from file !");
        }
    }

    public void addOrder(Order order, File file) {  // adding data to the order text file

        try {
            FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
            bufferedWriter.write(order.toString());  // calling of order class to string method
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Problem in adding data from file !");
        }
    }

    public void addOrderProduct(OrderProduct orderProduct, File file) {

        try {
            FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
            bufferedWriter.write(orderProduct.toString());
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Problem in adding data from file !");
        }
    }

    public void addProduct(Product product, File file) {

        try {
            FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
            bufferedWriter.write(product.toString());
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Problem in adding data from file !");
        }
    }

    public void addUser(Customer customer, File file) {

        try{
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(customer.toString());
                bufferedWriter.close();
                fileWriter.close();
        } catch (IOException e)
        {
            System.out.println("Problem in adding data from file !");
        }
    }


    public  ArrayList<IdTracker> readLastId()
    {

        ArrayList<IdTracker> list1 = new ArrayList<IdTracker>();
        File fileReader = new File("./File_db/id_tracker_db.txt");
        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader(fileReader));
            String st;
            while ((st = br.readLine()) != null)
            {
                String[] data=st.split(" ");
                IdTracker prod=new IdTracker(data[0],Integer.parseInt(data[1]));
                list1.add(prod);
            }
            br.close();
        } catch (IOException e)
        {
            System.out.println("Problem in reading data from file !");
        }

        return list1;
    }

    public  ArrayList<Coupon> readCoupons()
    {

        ArrayList<Coupon> list1 = new ArrayList<Coupon>();
        File fileReader = new File("./File_db/coupon_db.txt");
        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader(fileReader));
            String st;
            while ((st = br.readLine()) != null)
            {
                String[] data=st.split(" ");
                Coupon coupon=new Coupon(Integer.parseInt(data[0]),data[1],Integer.parseInt(data[2]),Integer.parseInt(data[3]),data[4],data[5]);
                list1.add(coupon);
            }
            br.close();
        } catch (IOException e)
        {
            System.out.println("Problem in reading data from file !");
        }

        return list1;
    }

    public  ArrayList<Product> readFileDataProduct()
    {

        ArrayList<Product> list1 = new ArrayList<Product>();
        File fileReader = new File("./File_db/product_db.txt");
        BufferedReader br = null;

        try{
                br = new BufferedReader(new FileReader(fileReader));
                String st;
                while ((st = br.readLine()) != null)
                {
                    String[] data=st.split(" ");
                    Product prod=new Product(Integer.parseInt(data[0]),data[1],data[2],data[3],Double.parseDouble(data[4]),Integer.parseInt(data[5]));
                    list1.add(prod);
                }
                br.close();
        } catch (IOException e)
        {
            System.out.println("Problem in reading data from file !");
        }


        return list1;
    }




    public  ArrayList<Cart> readCurrentProductUser()
    {

        ArrayList<Cart> list1 = new ArrayList<Cart>();
        File AdminUpdate= new File("./File_db/z-current-product_db.txt");

        try{
            BufferedReader adu = new BufferedReader(new FileReader(AdminUpdate));
            String temp;
            while ((temp = adu.readLine()) != null)
            {
                String[] data=temp.split(" ");
                Cart cart = new Cart(data[0],Integer.parseInt(data[1]),Double.parseDouble(data[2]),data[3],data[4]);
                list1.add(cart);
            }
            adu.close();
        } catch (IOException e)
        {
            System.out.println("Problem in reading data from file !");
        }


        return list1;
    }


    public  ArrayList<Order> readHistoryProductUser()
    {

        ArrayList<Order> list1 = new ArrayList<Order>();
        File AdminUpdate= new File("./File_db/order_db.txt");

        try{
            BufferedReader adu = new BufferedReader(new FileReader(AdminUpdate));
            String temp;
            while ((temp = adu.readLine()) != null)
            {
                String[] data=temp.split(" ");
                Order order = new Order(Integer.parseInt(data[0]),data[1],Integer.parseInt(data[2]),Double.parseDouble(data[3]),data[4],data[5]);
                list1.add(order);
            }
            adu.close();
        } catch (IOException e)
        {
            System.out.println("Problem in reading data from file !");
        }


        return list1;
    }

    public  ArrayList<OrderProduct> readOrderProducts()
    {

        ArrayList<OrderProduct> list1 = new ArrayList<OrderProduct>();
        File AdminUpdate= new File("./File_db/order_products_db.txt");

        try{
            BufferedReader adu = new BufferedReader(new FileReader(AdminUpdate));
            String temp;
            while ((temp = adu.readLine()) != null)
            {
                String[] data=temp.split(" ");
                OrderProduct orderProduct = new OrderProduct(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Double.parseDouble(data[2]));
                list1.add(orderProduct);
            }
            adu.close();
        } catch (IOException e)
        {
            System.out.println("Problem in reading data from file !");
        }


        return list1;
    }


    public  ArrayList<Customer> readFileDataCustomer()
    {

        ArrayList<Customer> list1 = new ArrayList<Customer>();
        File AdminUpdate= new File("./File_db/zusers_db.txt");

        try{
            BufferedReader adu = new BufferedReader(new FileReader(AdminUpdate));
            String temp;
            while ((temp = adu.readLine()) != null)
            {
                String[] data=temp.split(" ");
                Customer cust=new Customer(data[0],data[1],data[2], Long.parseLong(data[3]),Integer.parseInt(data[4]));
                list1.add(cust);
            }
            adu.close();
        } catch (IOException e)
        {
            System.out.println("Problem in reading data from file !");
        }


        return list1;
    }


    public  ArrayList<Customer> readFilePassword()  // reading the password data
    {

        ArrayList<Customer> list1 = new ArrayList<Customer>();
        File AdminUpdate= new File("./File_db/z-Password_db.txt");

        try{
            BufferedReader adu = new BufferedReader(new FileReader(AdminUpdate));
            String temp;
            while ((temp = adu.readLine()) != null)
            {
                String[] data=temp.split(" ");
                Customer cust=new Customer(data[0],data[1],data[2], Long.parseLong(data[3]), Integer.parseInt(data[4]));
                list1.add(cust);
            }
            adu.close();
        } catch (IOException e)
        {
            System.out.println("Problem in reading data from file !");
        }


        return list1;
    }



    public boolean emailVerifier(String email) {

        try {
            File readfile = new File("./File_db/zusers_db.txt");
            BufferedReader br = new BufferedReader(new FileReader(readfile));
            String st;
            while ((st = br.readLine()) != null) {
                String[] data = st.split(" ");
                if (data[0].equals(email)) {

                    return true;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Problem in reading data from file !");
        }

        return false;
    }




    public boolean emailAndPasswordVerifier(String email,String password) { // Email and password verifier

        try {
            File readfile = new File("./File_db/zusers_db.txt");
            BufferedReader br = new BufferedReader(new FileReader(readfile));
            String st;
            while ((st = br.readLine()) != null) {
                String[] data = st.split(" ");
                if (data[0].equals(email) && data[1].equals(password)) {

                    return true;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Problem in reading data from file !");
        }

        return false;
    }




    public boolean adminChecker(String email,String Password){   // CHECKING THE VALID ADMIN

        try {
            File readfile = new File("./File_db/zusers_db.txt");
            BufferedReader br = new BufferedReader(new FileReader(readfile));
            String st;
            while ((st = br.readLine()) != null)
            {
                String[] data=st.split(" ");

                if( email.equals("admin@zoho.com") && (data[1].equals(Password)))
                {
                    return true;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Problem in reading data from file !");
        }

        return false;


    }


    public void fileDataVanisher(File file)  // REMOVING DATA FROM THE FILE
    {
        FileWriter fwOb = null;
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();

        } catch (IOException e) {
            System.out.println("Problem in removing data from file !");
        }

    }






}
