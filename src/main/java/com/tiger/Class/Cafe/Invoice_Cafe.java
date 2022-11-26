package com.tiger.Class.Cafe;

import com.tiger.Class.Invoice;

import java.util.Date;

public class Invoice_Cafe extends Invoice {
    private LineItem item[];

    public Invoice_Cafe(String invoiceID, Date date, LineItem item[]) {
        super( date);
        this.item = item;

        double subTotal = 0;
        for (int i = 0; i < item.length; i++) {
            subTotal += item[i].getSubtotal();
        }

        setSubTotal(subTotal);
        setTotal();


    }

    public LineItem[] getItem () {
        return item;
    }

    public void setItem (LineItem[] item) {
        this.item = item;
    }
}