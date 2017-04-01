package com.keshasosiska;

public class Session {
    private final String time;
    private final String price;

    public Session(final String time, final String price) {
        this.time = time;
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return time + "(" + price + ")";
    }
}
