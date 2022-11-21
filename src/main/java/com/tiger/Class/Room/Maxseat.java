package com.tiger.Class.Room;

public enum Maxseat {
    SMALL(5), LARGE(10), INDIVIDUAL(1);
    private int maxseat;
    Maxseat(int maxseat) {
        this.maxseat = maxseat;
    }
    public int getMaxseat() {
        return maxseat;
    }
}
