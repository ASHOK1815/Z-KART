package Inventory;

public class OrderProduct {

    public int orderId;
    public int productId;
    public double price;



    public OrderProduct(int orderId, int productId, double price)
    {
        this.orderId=orderId;
        this.productId=productId;
        this.price=price;
    }


    @Override
    public  String toString() {
        return orderId+" "+productId+" "+price+"\n";
    }
}
