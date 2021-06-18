package filehandler;

import Inventory.Cart;
import customer.Customer;
import product.Product;

import java.io.*;
import java.util.ArrayList;

public class Filehandler {

    

    public void addCart(Cart cart, File file) {

        try {
            FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
            bufferedWriter.write(cart.toString());
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    public  ArrayList<Product> readFileDataProduct()
    {

        ArrayList<Product> list1 = new ArrayList<Product>();
        File fileReader = new File("./File_db/z-kart_db.txt");
        BufferedReader br = null;

        try{
                br = new BufferedReader(new FileReader(fileReader));
                String st;
                while ((st = br.readLine()) != null)
                {
                    String[] data=st.split(" ");
                    Product prod=new Product(data[0],data[1],data[2],Double.parseDouble(data[3]),Integer.parseInt(data[4]));
                    list1.add(prod);
                }
                br.close();
        } catch (IOException e)
        {
            e.printStackTrace();
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
                Cart cart = new Cart(data[0],data[1],data[2],data[3],Double.parseDouble(data[4]),data[5],data[6],Integer.parseInt(data[7]));
                list1.add(cart);
            }
            adu.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }


        return list1;
    }


    public  ArrayList<Cart> readHistoryProductUser()
    {

        ArrayList<Inventory.Cart> list1 = new ArrayList<Inventory.Cart>();
        File AdminUpdate= new File("./File_db/zcartuserHistory_db.txt");

        try{
            BufferedReader adu = new BufferedReader(new FileReader(AdminUpdate));
            String temp;
            while ((temp = adu.readLine()) != null)
            {
                String[] data=temp.split(" ");
                Inventory.Cart cart = new Inventory.Cart(data[0],data[1],data[2],data[3],Double.parseDouble(data[4]),data[5],data[6],Integer.parseInt(data[7]));
                list1.add(cart);
            }
            adu.close();
        } catch (IOException e)
        {
            e.printStackTrace();
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
                Customer cust=new Customer(data[0],data[1],data[2], Long.parseLong(data[3]));
                list1.add(cust);
            }
            adu.close();
        } catch (IOException e)
        {
            e.printStackTrace();
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
            e.printStackTrace();
        }

        return false;
    }




    public boolean emailAndPasswordVerifier(String email,String password) {

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
            e.printStackTrace();
        }

        return false;
    }




    public boolean adminChecker(String email,String Password){

        try {
            File readfile = new File("./File_db/zusers_db.txt");
            BufferedReader br = new BufferedReader(new FileReader(readfile));
            String st;
            while ((st = br.readLine()) != null)
            {
                String[] data=st.split(" ");
                if(data[2].equals("Admin") && data[1].equals(Password))
                {
                    return true;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;


    }


    public void fileDataVanisher(File file)
    {
        FileWriter fwOb = null;
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
