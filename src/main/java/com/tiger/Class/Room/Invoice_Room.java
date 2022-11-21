package com.tiger.Class.Room;

import com.tiger.Class.Invoice;

import java.util.Date;

public class Invoice_Room extends Invoice {
    private Customer customer;
    private Room room;
    private double hour;

    public Invoice_Room (String invoiceID, Date date, Customer customer, Room room, double hour) {
        super( invoiceID, date );
        this.customer = customer;
        this.room = room;
        this.hour = hour;

        double subTotal = room.getPrice() * hour;
        setSubTotal( subTotal );

        double total = (subTotal * getTAX()) + subTotal;
        setTotal( total );

    }

    public Customer getCustomer () {
        return customer;
    }

    public void setCustomer (Customer customer) {
        this.customer = customer;
    }

    public Room getRoom () {
        return room;
    }

    public void setRoom (Room room) {
        this.room = room;
    }

    public double getHour () {
        return hour;
    }

    public void setHour (double hour) {
        this.hour = hour;
    }
}
