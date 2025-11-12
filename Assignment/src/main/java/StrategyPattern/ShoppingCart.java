package StrategyPattern;

import java.util.ArrayList;
import java.util.List;

//Product class
class Product{
    private int price;

    public Product(int price){
        this.price=price;
    }

    public int getPrice() {
        return price;
    }
}

//context class
class ShoppingCart {
    private List<Product> products;
    private PaymentStrategy paymentStrategy;

    public ShoppingCart(){
        products = new ArrayList<>();
    }

    public void addProduct(Product product){
        products.add(product);
        System.out.println("Added item worth " +product.getPrice());
    }
    public int getTotatl(){
        int total =0;
        for(Product product: products){
            total += product.getPrice();
        }
        return  total;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(){
        int total = getTotatl();
        paymentStrategy.pay(total);
    }
}
