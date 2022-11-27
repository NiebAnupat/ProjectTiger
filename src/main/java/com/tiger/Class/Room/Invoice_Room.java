package com.tiger.Class.Room;

import com.tiger.Class.DB_Connector;
import com.tiger.Class.Invoice;
import com.tiger.Class.InvoiceType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class Invoice_Room extends Invoice {
    private Customer customer;
    private Room room;
    private double hour;

    private Timestamp lastReservedDate;

    private String itemName;

    private int roomPriceMember = 0;

    private double originalPrice = 0;



    public Invoice_Room(Date date, Customer customer, Room room, double hour, Timestamp lastReservedDate) {
        super(date);
        this.customer = customer;
        this.room = room;
        this.hour = hour;
        this.itemName = room.getName();
        this.lastReservedDate = lastReservedDate;

        double subTotal = room.getPrice() * hour;
        setSubTotal(subTotal);
        this.originalPrice = subTotal;

        this.calculateMemberDiscount();
        setTotal();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double getHour() {
        return hour;
    }

    public void setHour(double hour) {
        this.hour = hour;
    }

    public boolean submit() {

        // check room is available
        if (!room.checkRoomIsAvailable()) return false;
        String sql;
        int invoiceType = 0;
        try {
            DB_Connector db = new DB_Connector();
            sql = "select type_id from invoice_type where type_name = '%s'";
            sql = String.format(sql, getInvoiceType().toString());
            ResultSet rs = db.getResultSet(sql);
            if (rs.next()) {
                invoiceType = rs.getInt("type_id");
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // save to database
        if (this.customer.getMember()) sql = String.format("insert into invoice(inv_type,inv_vat,inv_total,inv_date,cus_phone,inv_discount) values('%d','%.2f','%.2f','%s','%s','%.2f')", invoiceType, getVat(), getTotal(), getSqlDate(),getCustomer().getPhoneNum(),getDiscount());
        else sql = String.format("insert into invoice(inv_type,inv_vat,inv_total,inv_date) values('%d','%.2f','%.2f','%s')", invoiceType, getVat(), getTotal(), getSqlDate());
        try {
            if (new DB_Connector().execute(sql)) {

            // update room status
                sql = String.format("update room set reserved = 1, last_reserved_end='%s' where r_id = '%d'",this.lastReservedDate,room.getRoomID());
                if (!new DB_Connector().execute(sql)) return false;

                return true;
            } else {
                System.out.println("Invoice Room failed");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public InvoiceType getInvoiceType() {
        return InvoiceType.ROOM;
    }


    public int getQtyHour() {
        return (int) Math.ceil(hour);
    }

    private void calculateMemberDiscount() {
        if (this.customer.getMember()) {
            roomPriceMember = this.room.getRoomType().getMemberPrice();
            this.setDiscount(Math.abs(roomPriceMember - this.getSubTotal()));
            this.setSubTotal(roomPriceMember);
        } else {
            this.setDiscount(0);
        }
    }

    public String getItemName() {
        return itemName;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }
}