package com.tiger.Class.Room;

import com.tiger.Class.DB_Connector;
import com.tiger.Class.Invoice;
import com.tiger.Class.myAlert;
import javafx.scene.control.Alert;

import java.util.Date;

public class Customer {
    private String phoneNum;
    private Boolean isMember;

    public Customer (String phoneNum) {
        this.phoneNum = phoneNum;

        // Check if the customer is a member
        String sql = String.format( "SELECT * FROM customer WHERE phoneNum = '%s'", phoneNum );
        try {
            if ( new DB_Connector().getResultSet( sql ).next() ) {
                isMember = true;
            } else {
                isMember = false;
            }
        } catch (Exception e) {
           new myAlert().showErrorAlert( "Error: " + e.getMessage() );
        }
    }

    public String getPhoneNum () {
        return phoneNum;
    }

    public void setPhoneNum (String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Boolean getMember () {
        return isMember;
    }

    public void setMember (Boolean member) {
        isMember = member;
    }

    public void reserveRoom (Room room, Date startTime, Date endTime) {

        if ( room.setReserved( true ) ) {
            // calculate hour
            double hour = ( endTime.getTime() - startTime.getTime() ) / 3600000;
            // create new invoice room
            Invoice invoice = new Invoice_Room( new Date(), this, room, hour );
            if ( invoice.isCreated() ) {
                System.out.println( "Room reserved" );
                // show alert dialog
               new myAlert().showInformationAlert( "การจองสำเร็จ" );

            } else {
                System.out.println( "Room reservation failed" );
                // show alert dialog
                new myAlert().showErrorAlert( "การจองล้มเหลว" );
            }
        }


    }
}