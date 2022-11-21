package com.tiger.Class.Cafe;

public class LineItem {
    private Menu menu;
    private int quantity;
    private double price;
    private double subtotal;

    public LineItem(Menu menu, int quantity, double price) {
        this.menu = menu;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = price * quantity;
    }

    public Menu getMenu () {
        return menu;
    }

    public void setMenu (Menu menu) {
        this.menu = menu;
    }

    public int getQuantity () {
        return quantity;
    }

    public void setQuantity (int quantity) {
        this.quantity = quantity;
    }

    public double getPrice () {
        return price;
    }

    public void setPrice (double price) {
        this.price = price;
    }

    public double getSubtotal () {
        return subtotal;
    }

    public void setSubtotal (double total) {
        this.subtotal = total;
    }
}
