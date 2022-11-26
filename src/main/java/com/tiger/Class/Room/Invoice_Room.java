package com.tiger.Class.Room;

import com.tiger.Class.DB_Connector;
import com.tiger.Class.Invoice;

import java.sql.SQLException;
import java.util.Date;

public class Invoice_Room extends Invoice {
    private Customer customer;
    private Room room;
    private double hour;

    public Invoice_Room (Date date, Customer customer, Room room, double hour) {
        super( date );
        this.customer = customer;
        this.room = room;
        this.hour = hour;

        double subTotal = room.getPrice() * hour;
        setSubTotal( subTotal );
        setTotal();

        // save to database
        String sql = "INSERT INTO invoice_room (invoiceID, date,customerID, roomID, hour, totalPrice,) VALUES ('%s','%s','%s','%s','%s','%s')";
        sql = String.format( sql, getInvoiceID(), ( new java.sql.Date( getDate().getTime() ) ), customer.getPhoneNum(), room.getTypeID(), hour, getTotal() );
        try {
            if ( new DB_Connector().execute( sql ) ) {
                System.out.println( "Invoice Room created" );
                this.setCreated( true );
            }
            else System.out.println( "Invoice Room failed" );
        } catch (SQLException e) {
            e.printStackTrace();
        }


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