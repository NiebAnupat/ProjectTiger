package com.tiger.Class.Room;

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
}
