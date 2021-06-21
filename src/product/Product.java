package product;

import  filehandler.Filehandler;
import filehandler.IdTracker;

import java.util.ArrayList;

public class Product {

    public int id;
    public String brand;
    public String category;
    public String model;
    public double price;
    public int stock;


    public Product(int id, String category,String brand,  String model, double price, int stock) {

        this.id = id;
        this.brand = brand;
        this.category = category;
        this.model = model;
        this.price = price;
        this.stock = stock;
    }



    @Override
    public String toString() {
        return id +" "+ category +" "+ brand +" "+ model +" "+ price +" "+ stock +"\n";
    }
}
