package com.example.shopping.shoppingcar.Bean;

import java.io.Serializable;

public class SimOrderBean implements Serializable {

    int id;
    int UserId;
    int sid;
    int goodsNumber;
    Double OnePrice;
    String UserAddress;
    String CreateTime;
    Double Total;

    @Override
    public String toString() {
        return "SimOrderBean{" +
                "id=" + id +
                ", UserId=" + UserId +
                ", sid=" + sid +
                ", goodsNumber=" + goodsNumber +
                ", OnePrice=" + OnePrice +
                ", UserAddress='" + UserAddress + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", Total=" + Total +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Double getOnePrice() {
        return OnePrice;
    }

    public void setOnePrice(Double onePrice) {
        OnePrice = onePrice;
    }

    public String getUserAddress() {
        return UserAddress;
    }

    public void setUserAddress(String userAddress) {
        UserAddress = userAddress;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }
}
