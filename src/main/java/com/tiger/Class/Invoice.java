package com.tiger.Class;

import java.util.Date;
import java.util.UUID;

public class Invoice {
    private String invoiceID;
    private Date date;
    private double subTotal, total;
    private final double TAX = 0.7;



    private boolean isCreated = false;

    public Invoice (Date date) {
        this.invoiceID = UUID.randomUUID().toString();
        this.date = date;
    }

    public String getInvoiceID () {
        return invoiceID;
    }

    public void setInvoiceID (String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Date getDate () {
        return date;
    }

    public void setDate (Date date) {
        this.date = date;
    }

    public double getSubTotal () {
        return subTotal;
    }

    public void setSubTotal (double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotal () {
        return total;
    }

    public void setTotal () {
        this.total = subTotal + ( subTotal * TAX );
    }

    public double getTAX () {
        return TAX;
    }

    public boolean isCreated () {
        return isCreated;
    }

    public void setCreated (boolean created) {
        isCreated = created;
    }
}