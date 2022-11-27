package com.tiger.Class.Cafe;

public class LineItem {
    private Menu menu;
    private int quantity;
    private int price;
    private int subtotal;

    public LineItem(Menu menu, int quantity, int price) {
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

    public void setPrice (int price) {
        this.price = price;
    }

    public int getSubtotal () {
        return subtotal;
    }

    public void setSubtotal (int total) {
        this.subtotal = total;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "menu=" + menu.getName() +
                ", quantity=" + quantity +
                ", price=" + price +
                ", subtotal=" + subtotal +
                '}';
    }
}
