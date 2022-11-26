package com.tiger.Class.Room;

import com.tiger.Class.DB_Connector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Room {
    private Maxseat maxseat;
    private RoomType roomType;
    private double Price;

    public Room(Maxseat maxseat, RoomType roomType, double price) {
        this.maxseat = maxseat;
        this.roomType = roomType;
        Price = price;
    }

    public Maxseat getMaxseat () {
        return maxseat;
    }

    public void setMaxseat (Maxseat maxseat) {
        this.maxseat = maxseat;
    }

    public RoomType getRoomType () {
        return roomType;
    }

    public void setRoomType (RoomType roomType) {
        this.roomType = roomType;
    }

    public double getPrice () {
        return Price;
    }

    public void setPrice (double price) {
        Price = price;
    }


    public int getTypeID(){
        switch (roomType){
            case SMALL : return 1;
            case LARGE : return 2;
            case INDIVIDUAL : return 3;
            default: return 0;
        }
    }
    public boolean setReserved (boolean b) {
        String roomType = this.roomType.toString();

        switch (roomType){
            case "SMALL" -> roomType = "1";
            case "LARGE" -> roomType = "2";
            case "INDIVIDUAL" -> roomType = "3";
        }

        String sql = "UPDATE room SET reserved = ? WHERE roomType = ? AND reserved = ? LIMIT 1";
        try {
            PreparedStatement preparedStatement = new DB_Connector().getConnection().prepareStatement(sql);
            preparedStatement.setBoolean(1, b);
            preparedStatement.setString(2, roomType);
            preparedStatement.setBoolean(3, !b);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean checkRoomIsAvailable(){
        String roomType = this.roomType.toString();

        switch (roomType){
            case "SMALL" -> roomType = "1";
            case "LARGE" -> roomType = "2";
            case "INDIVIDUAL" -> roomType = "3";
        }

//        String sql = "SELECT * FROM room WHERE roomType = ? AND reserved = 1 LIMIT 1";
        String sql = String.format("SELECT * FROM room WHERE roomType = %s AND reserved = 1 LIMIT 1", roomType);
        try {
            return new DB_Connector().getResultSet( sql ).next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}