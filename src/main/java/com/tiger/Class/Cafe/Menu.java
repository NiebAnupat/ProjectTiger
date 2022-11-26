package com.tiger.Class.Cafe;

public class Menu {
    private String ID, name;
    private double price;

    public Menu(String ID, String name, double price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    public Menu(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getID () {
        return ID;
    }

    public void setID (String ID) {
        this.ID = ID;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public double getPrice () {
        return price;
    }

    public void setPrice (double price) {
        this.price = price;
    }
}
