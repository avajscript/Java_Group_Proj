package org.example.grouppro.classes;

public class Food {
    private String name;
    private int quantity;
    private double price;
    private boolean isDiscounted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isIsDiscounted() {
        return isDiscounted;
    }

    public void setIsDiscounted(boolean isDiscounted) {
        this.isDiscounted = isDiscounted;
    }


}
