package com.tiger.Class.Room;

import com.tiger.Class.Invoice;
import com.tiger.Class.InvoiceType;

import java.util.Date;

public class Invoice_Member extends Invoice {
    private Customer customer;

    public Invoice_Member(String invoiceID, Date date, Customer customer) {
        super( date);
        this.customer = customer;

        double subTotal = 1500;
        setSubTotal(subTotal);
        setTotal();
    }

    public Customer getCustomer () {
        return customer;
    }

    public void setCustomer (Customer customer) {
        this.customer = customer;
    }

    @Override
    public InvoiceType getInvoiceType() {
        return InvoiceType.MEMBER;
    }

    @Override
    public boolean submit() {
        return false;
    }
}