package com.example.shopping.Myorders.bean;

import java.io.Serializable;
import java.util.List;

public class Orderbean implements Serializable {


    /**
     * msg : get success
     * MedOrder : [{"createTime":"2020-06-01 07:04:13","goodsNumber":1,"id":7,"medicine":{"cover_price":199,"details":"功能主治：保持血管畅通，降低血液粘稠度，具有降血脂、预防血栓的功效，适合中老年人群日常保养。","img_url":"047e267dc96e4f6f864c0183905f7cb0.jpg","product_id":31,"product_name":"gnc健安喜美国深海鱼油","type":"心脑血管"},"sid":31,"total":199,"uid":1,"userAddress":"test address"},{"createTime":"2020-06-01 07:04:14","goodsNumber":1,"id":8,"medicine":{"cover_price":72,"details":"功能主治：心绞痛发作的预防性治疗，眩晕和耳鸣的辅助性对症治疗。","img_url":"8f6edd247e6b49fb8ae7cee37e260e9b.jpg","product_id":38,"product_name":"万爽力盐酸曲美他嗪缓释片","type":"心脑血管"},"sid":38,"total":72,"uid":1,"userAddress":"test address"},{"createTime":"2020-06-01 07:05:47","goodsNumber":3,"id":9,"medicine":{"cover_price":173,"details":"功能主治：健康血脂血压，优化肝脏环境，增强大脑环境","img_url":"b650daf78ef94559a2e2a505b5729e9c.jpg","product_id":42,"product_name":"双心大豆卵磷脂胶囊","type":"心脑血管"},"sid":42,"total":519,"uid":1,"userAddress":"test address"},{"createTime":"2020-06-01 07:05:47","goodsNumber":2,"id":10,"medicine":{"cover_price":34.5,"details":"功能主治：益气通脉，宁心安神，生津止渴。用于胸痹、心悸、不寐，消渴气虚证，症见痛胸闷；心悸不安，失眠健忘，口渴多饮气短乏力；冠心病，心绞痛，心律失常，神经衰弱，2型糖尿病见上述证候者。","img_url":"109b80b01bfa4719952f223e2e7ebb9b.jpg","product_id":40,"product_name":"益盛振源胶囊","type":"心脑血管"},"sid":40,"total":69,"uid":1,"userAddress":"test address"},{"createTime":"2020-06-01 07:07:02","goodsNumber":3,"id":11,"medicine":{"cover_price":173,"details":"功能主治：健康血脂血压，优化肝脏环境，增强大脑环境","img_url":"b650daf78ef94559a2e2a505b5729e9c.jpg","product_id":42,"product_name":"双心大豆卵磷脂胶囊","type":"心脑血管"},"sid":42,"total":519,"uid":1,"userAddress":"test address"},{"createTime":"2020-06-01 07:07:02","goodsNumber":2,"id":12,"medicine":{"cover_price":34.5,"details":"功能主治：益气通脉，宁心安神，生津止渴。用于胸痹、心悸、不寐，消渴气虚证，症见痛胸闷；心悸不安，失眠健忘，口渴多饮气短乏力；冠心病，心绞痛，心律失常，神经衰弱，2型糖尿病见上述证候者。","img_url":"109b80b01bfa4719952f223e2e7ebb9b.jpg","product_id":40,"product_name":"益盛振源胶囊","type":"心脑血管"},"sid":40,"total":69,"uid":1,"userAddress":"test address"}]
     * code : 200
     * userId : 1
     */

    private String msg;
    private String code;
    private int userId;
    private List<MedOrderBean> MedOrder;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<MedOrderBean> getMedOrder() {
        return MedOrder;
    }

    public void setMedOrder(List<MedOrderBean> MedOrder) {
        this.MedOrder = MedOrder;
    }

    public static class MedOrderBean {
        /**
         * createTime : 2020-06-01 07:04:13
         * goodsNumber : 1
         * id : 7
         * medicine : {"cover_price":199,"details":"功能主治：保持血管畅通，降低血液粘稠度，具有降血脂、预防血栓的功效，适合中老年人群日常保养。","img_url":"047e267dc96e4f6f864c0183905f7cb0.jpg","product_id":31,"product_name":"gnc健安喜美国深海鱼油","type":"心脑血管"}
         * sid : 31
         * total : 199.0
         * uid : 1
         * userAddress : test address
         */

        private String createTime;
        private int goodsNumber;
        private int id;
        private MedicineBean medicine;
        private int sid;
        private double total;
        private int uid;
        private String userAddress;
        private boolean selectEd = false;

        public boolean isSelectEd() {
            return selectEd;
        }

        public void setSelectEd(boolean selectEd) {
            this.selectEd = selectEd;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getGoodsNumber() {
            return goodsNumber;
        }

        public void setGoodsNumber(int goodsNumber) {
            this.goodsNumber = goodsNumber;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public MedicineBean getMedicine() {
            return medicine;
        }

        public void setMedicine(MedicineBean medicine) {
            this.medicine = medicine;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        public static class MedicineBean {
            /**
             * cover_price : 199.0
             * details : 功能主治：保持血管畅通，降低血液粘稠度，具有降血脂、预防血栓的功效，适合中老年人群日常保养。
             * img_url : 047e267dc96e4f6f864c0183905f7cb0.jpg
             * product_id : 31
             * product_name : gnc健安喜美国深海鱼油
             * type : 心脑血管
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
}
