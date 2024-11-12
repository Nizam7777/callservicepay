package org.example.gudilin.callservicepay.Model;

public class Payment {
    private String Id;
    private double amount;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
