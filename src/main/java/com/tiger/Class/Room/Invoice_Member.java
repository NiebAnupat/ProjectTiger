package com.tiger.Class.Room;

import com.tiger.Class.DB_Connector;
import com.tiger.Class.Invoice;
import com.tiger.Class.InvoiceType;

import java.sql.ResultSet;
import java.util.Date;

public class Invoice_Member extends Invoice {
    String itemName = "สมัครสมาชิกรายเดือน";
    int itemPrice = 1500;
    private Customer customer;

    public Invoice_Member(Date date, Customer customer) {
        super(date);
        this.customer = customer;
        double subTotal = 1500;
        setSubTotal(subTotal);
        setTotal();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public InvoiceType getInvoiceType() {
        return InvoiceType.MEMBER;
    }


    @Override
    public boolean submit() {
        // save to database
        DB_Connector db = new DB_Connector();

        String sql;

        try {

            // insert customer
            sql = "INSERT INTO customers (phoneNum) VALUES ('%s')";
            sql = String.format(sql, customer.getPhoneNum());
            if (!db.execute(sql)) return false;

            // get itemID
            sql = "select item_id from items where item_name = '%s'";
            sql = String.format(sql, itemName);
            ResultSet rs = db.getResultSet(sql);
            if (!rs.next()) return false;
            int itemID = rs.getInt("item_id");

            // insert invoice
            sql = "INSERT INTO invoice (inv_type,inv_vat,inv_total,inv_date) VALUES ((select type_id from invoice_type where type_name = '%s'),'%.2f','%.2f','%s')";
            sql = String.format(sql, getInvoiceType().toString(), getVat(), getTotal(), getSqlDate());
            if (!db.execute(sql)) return false;

            // get invoiceID
            sql = "select inv_id from invoice order by inv_id desc limit 1";
            rs = db.getResultSet(sql);
            if (!rs.next()) return false;
            int lastId = rs.getInt("inv_id");

            // insert invoice_item
            sql = "INSERT INTO invoice_items (inv_id,item_id,qty,subtotal) VALUES ('%d','%d','%d','%.2f')";
            sql = String.format(sql, lastId, itemID, 1, getSubTotal());
            if (!db.execute(sql)) return false;

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}