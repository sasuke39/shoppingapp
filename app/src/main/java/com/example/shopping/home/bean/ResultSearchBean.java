package com.example.shopping.home.bean;

import java.io.Serializable;
import java.util.List;

public class ResultSearchBean implements Serializable {


    /**
     * msg : Search success!
     * code : 200
     * Medicines : [{"cover_price":15,"details":"健胃消食，用于脾胃虚弱所致的食积，症见不思饮食，脘腹胀满，消化不良等症状；","img_url":"B1772FB31F1179A6D64AB754C653DEA6.jpg","product_id":11,"product_name":"江中健胃消食片","type":"肠胃用药"}]
     */

    private String msg;
    private int code;
    private List<MedicinesBean> Medicines;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<MedicinesBean> getMedicines() {
        return Medicines;
    }

    public void setMedicines(List<MedicinesBean> Medicines) {
        this.Medicines = Medicines;
    }

    public static class MedicinesBean {
        /**
         * cover_price : 15.0
         * details : 健胃消食，用于脾胃虚弱所致的食积，症见不思饮食，脘腹胀满，消化不良等症状；
         * img_url : B1772FB31F1179A6D64AB754C653DEA6.jpg
         * product_id : 11
         * product_name : 江中健胃消食片
         * type : 肠胃用药
         */

        private double cover_price;
        private String details;
        private String img_url;
        private int product_id;
        private String product_name;
        private String type;

        public double getCover_price() {
            return cover_price;
        }

        public void setCover_price(double cover_price) {
            this.cover_price = cover_price;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
