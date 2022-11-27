package com.tiger.Class;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Report {
    private SimpleStringProperty date;
    private SimpleStringProperty itemName;
    private SimpleStringProperty  quantity;
    private SimpleStringProperty  price;

    public Report(String  date, String itemName, String  quantity, String  price) {

        this.date = new SimpleStringProperty(date);
        this.itemName = new SimpleStringProperty(itemName);
        this.quantity = new SimpleStringProperty(quantity);
        this.price = new SimpleStringProperty(price);
    }


    public SimpleStringProperty dateProperty() {
        return date;
    }

    public SimpleStringProperty itemNameProperty() {
        return itemName;
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }
}
