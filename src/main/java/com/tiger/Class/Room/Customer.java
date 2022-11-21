package com.tiger.Class.Room;

public class Customer {
    private String phoneNum;
    private Boolean isMember;

    public Customer(String phoneNum, Boolean isMember) {
        this.phoneNum = phoneNum;
        this.isMember = isMember;
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
}
