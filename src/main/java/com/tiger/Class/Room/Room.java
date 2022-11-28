package com.tiger.Class.Room;

import com.tiger.Class.DB_Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Room {

    private int id;
    private RoomType roomType;
    private double Price;

    public Room(RoomType roomType, double price) {
        this.roomType = roomType;
        Price = price;

        if (this.checkRoomIsAvailable()) {
            // set id
            this.id = this.getAvailableRoomId();
        }
    }

    public static boolean isRoomFull(RoomType type) {

        DB_Connector db = new DB_Connector();
        String sql = "SELECT COUNT(*) FROM room WHERE roomType = '%s' AND reserved = '0'";
        sql = String.format(sql, type.getId());
        try {
            ResultSet rs = db.getResultSet(sql);
            rs.next();
            int count = rs.getInt(1);
            if (count >= 1) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    private int getAvailableRoomId() {
        DB_Connector db = new DB_Connector();
        String sql = String.format("SELECT r_id FROM room WHERE roomType = '%d' AND reserved = '0' LIMIT 1", this.getTypeID());
        try {
            ResultSet rs = db.getResultSet(sql);
            if (rs.next()) {
                return rs.getInt("r_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }


    public int getTypeID() {
        switch (roomType) {
            case SMALL:
                return 1;
            case LARGE:
                return 2;
            case INDIVIDUAL:
                return 3;
            default:
                return 0;
        }
    }

    public boolean setReserved(boolean b) {
        String roomType = this.roomType.toString();

        switch (roomType) {
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

    public boolean checkRoomIsAvailable() {

        String type = "";

        switch (this.roomType) {
            case SMALL -> type = "1";
            case LARGE -> type = "2";
            case INDIVIDUAL -> type = "3";
        }

        String sql = String.format("SELECT * FROM room WHERE roomType = '%s' AND reserved = '0' LIMIT 1", type);
        try {
            if (new DB_Connector().getResultSet(sql).next()) return true;
            else return false;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public String getName() {
        switch (roomType) {
            case SMALL:
                return "ห้องประชุมเล็ก";
            case LARGE:
                return "ห้องประชุมใหญ่";
            case INDIVIDUAL:
                return "รายบุคคล";
            default:
                return "ไม่มีชื่อห้อง";
        }
    }


    public int getRoomID() {
        return this.id;
    }
}