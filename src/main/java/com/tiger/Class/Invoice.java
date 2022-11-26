package com.tiger.Class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public abstract class Invoice {
    private String invoiceID;
    private Date date;
    private double subTotal, total;
    private final double TAX = 0.07;


    private boolean isCreated = false;

    public Invoice (Date date) {
        this.invoiceID = this.getIdFromDatabase();
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

    abstract public InvoiceType getInvoiceType();

    public double getVat() {
        return (subTotal * TAX);
    }

    public String getDateWithFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public String getSqlDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    public String getTimeWithFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(date);
    }

    private String getIdFromDatabase() {
        int lastId = 0;
        String sql = "SELECT inv_id FROM invoice ORDER BY inv_id DESC LIMIT 1";
        try {
        ResultSet rs = new DB_Connector().getResultSet(sql);
             if(rs.next()){
                 lastId = rs.getInt("inv_id");
             }else {
                 lastId = 0;
             }
        } catch (SQLException e) {
            e.printStackTrace();
            lastId = -1;
        }
        if (lastId == -1) {
            return null;
        }
        lastId++;
        return String.format("%05d", lastId);
    }

    abstract public boolean submit();


}