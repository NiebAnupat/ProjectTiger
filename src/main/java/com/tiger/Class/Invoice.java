package com.tiger.Class;

import java.util.Date;

public class Invoice {
    private String invoiceID;
    private Date date;
    private double subTotal, total;
    private final double TAX = 0.7;

    public Invoice(String invoiceID, Date date  ) {
        this.invoiceID = invoiceID;
        this.date = date;

        this.total = total;
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

    public void setTotal (double total) {
        this.total = subTotal * TAX;
    }

    public double getTAX () {
        return TAX;
    }
}
