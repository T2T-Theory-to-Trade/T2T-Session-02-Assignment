package StrategyPattern;

class  CreditCardPayment implements PaymentStrategy{
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit card");
    }
}

class  PayPalPayment implements PaymentStrategy{

    @Override
    public void pay(int amount) {
        System.out.println("Paid "+ amount + " using Paypal");
    }
}

class  UPIPayment implements PaymentStrategy{

    @Override
    public void pay(int amount) {
        System.out.println("Paid "+ amount + " using UPI");
    }
}

// Extension class
class  CryptoPayment implements PaymentStrategy{

    @Override
    public void pay(int amount) {
        System.out.println("Paid "+ amount + " using CryptoPayment");
    }
}


