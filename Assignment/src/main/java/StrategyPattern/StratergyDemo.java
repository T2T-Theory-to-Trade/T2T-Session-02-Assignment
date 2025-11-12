package StrategyPattern;

public class StratergyDemo {
    public static void main(String[] args){
        ShoppingCart shoppingCart = new ShoppingCart();

        //Add Products
        shoppingCart.addProduct(new Product(40));
        shoppingCart.addProduct(new Product(60));

        //Total
        int total = shoppingCart.getTotatl();
        System.out.println("Total: " + total);
//---------------- Strategies --------------------------------
        //Pay using CreditCard
        shoppingCart.setPaymentStrategy(new CreditCardPayment());
        shoppingCart.checkout();

        //pay using PayPal
        shoppingCart.setPaymentStrategy(new PayPalPayment());
        shoppingCart.checkout();

        //pay using UPI
        shoppingCart.setPaymentStrategy(new UPIPayment());
        shoppingCart.checkout();

        //Extension
        shoppingCart.setPaymentStrategy(new CryptoPayment());
        shoppingCart.checkout();
    }
}
