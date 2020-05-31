package com.example.shopping.shoppingcar.Bean;

import java.io.Serializable;
import java.util.List;

public class SimOrderBean implements Serializable {

    int UserId;
    List<Goods> goodsList;
    String CreateTime;
    Double Total;
    String AllGoods;

    @Override
    public String toString() {
        return "SimOrderBean{" +
                "UserId=" + UserId +
                ", goodsList=" + goodsList +
                ", CreateTime='" + CreateTime + '\'' +
                ", Total=" + Total +
                ", AllGoods='" + AllGoods + '\'' +
                '}';
    }

    public String getAllGoods() {
        return AllGoods;
    }

    public void setAllGoods(String allGoods) {
        AllGoods = allGoods;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
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

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
