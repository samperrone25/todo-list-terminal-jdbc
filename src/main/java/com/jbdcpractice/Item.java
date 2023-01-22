package com.jbdcpractice;

public class Item {
    private String title;
    private int timestamp;
    private String desc;
    private boolean done;

    public Item(String title, int timestamp, String desc, boolean done) {
        this.title = title;
        this.timestamp = timestamp;
        this.desc = desc;
        this.done = done;
    }

    @Override
    public String toString() {
        return String.format("Item with title (%s) timestamp (%d) description (%s) and done (%s).", this.title, this.timestamp, this.desc, this.done);
    }

}
