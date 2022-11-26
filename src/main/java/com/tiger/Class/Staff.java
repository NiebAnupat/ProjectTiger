package com.tiger.Class;

public class Staff {
    private String ID;
    private String name;

    public Staff(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String getID () {
        return ID;
    }

    public void setID (String ID) {
        this.ID = ID;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }
}