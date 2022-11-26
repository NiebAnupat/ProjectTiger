package com.tiger.Class.Cafe;

import com.tiger.Class.DB_Connector;
import com.tiger.Class.Invoice;
import com.tiger.Class.InvoiceType;

import java.sql.ResultSet;
import java.util.Date;

public class Invoice_Cafe extends Invoice {
    private LineItem item[];

    public Invoice_Cafe( Date date, LineItem item[]) {
        super(date);
        this.item = item;

        double subTotal = 0;
        for (int i = 0; i < item.length; i++) {
            subTotal += item[i].getSubtotal();
        }

        setSubTotal(subTotal);
        setTotal();


    }

    public LineItem[] getItem() {
        return item;
    }

    public void setItem(LineItem[] item) {
        this.item = item;
    }

    @Override
    public InvoiceType getInvoiceType() {
        return InvoiceType.CAFE;
    }

    @Override
    public boolean submit() {
        // save to database
        int[] itemID = new int[item.length];
        String sql = "select item_id from items where item_name = '%s'";
        for (int i = 0; i < item.length; i++) {
            sql = String.format(sql, item[i].getMenu().getName());
            try {
                ResultSet rs = new DB_Connector().getResultSet(sql);
                if (rs.next()) {
                    itemID[i] = rs.getInt("item_id");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        try {
            DB_Connector db = new DB_Connector();
            int invoiceType = 0;
            sql = "select type_id from invoice_type where type_name = '%s'";
            sql = String.format(sql, getInvoiceType().toString());
            ResultSet rs = db.getResultSet(sql);
            if (rs.next()) {
                invoiceType = rs.getInt("type_id");
            } else {
                return false;
            }

            sql = String.format("insert into invoice(inv_type,inv_vat,inv_total,inv_date) values('%d','%.2f','%.2f','%s')",invoiceType,getVat(),getTotal(),getSqlDate());
            if (!db.execute(sql))return false;

            sql = "select inv_id from invoice order by inv_id desc limit 1";
            rs = db.getResultSet(sql);
            int lastId = 0;
            if (rs.next()) {
                lastId = rs.getInt("inv_id");
            } else {
                return false;
            }

            // loop for insert invoice items
            for (int i = 0; i < item.length; i++) {
                sql = String.format("insert into invoice_items(inv_id,item_id,qty,subtotal) values(%d,%d,%d,%d)", lastId, itemID[i], item[i].getQuantity(), item[i].getSubtotal());
                if (!db.execute(sql)) return false;
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}