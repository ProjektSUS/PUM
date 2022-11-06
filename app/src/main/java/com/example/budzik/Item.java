package com.example.budzik;

public class Item {

    String time;
    String active;

    public Item(String time, String active) {
        this.time = time;
        this.active = active;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
