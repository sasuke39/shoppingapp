package com.example.shopping.shoppingcar.Bean;

import java.io.Serializable;

public class Goods implements Serializable {
    int number;
    int sid;
    String  goodName;

    @Override
    public String toString() {
        return "Goods{" +
                "number=" + number +
                ", sid=" + sid +
                ", goodName=" + goodName +
                '}';
    }

    public int getId() {
        return sid;
    }

    public void setId(int sid) {
        this.sid = sid;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String  goodName) {
        this.goodName = goodName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
