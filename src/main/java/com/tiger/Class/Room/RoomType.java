package com.tiger.Class.Room;

public enum RoomType {
    SMALL, LARGE, INDIVIDUAL;

    // get member price
    public int getMemberPrice() {
        switch (this) {
            case SMALL,LARGE: return 50;
            case INDIVIDUAL: return 0;
            default:
                return 0;
        }
    }


    public int getId() {
        switch (this) {
            case SMALL: return 1;
            case LARGE: return 2;
            case INDIVIDUAL: return 3;
            default:
                return 0;
        }
    }
}