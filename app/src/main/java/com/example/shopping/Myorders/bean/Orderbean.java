package com.example.shopping.Myorders.bean;

import java.io.Serializable;
import java.util.List;

public class Orderbean implements Serializable {


    /**
     * msg : get successfully!
     * code : 200
     * med_order : [{"goodsid":10,"id":1,"med_user":{"id":1,"password":"123123..","phoneNumber":"111231","username":"zzz"},"medicine":{"cover_price":29,"details":"抗生素类药，功效主要是消除炎症，主治上、下呼吸道感染下呼吸道感染，各种皮肤和软组织感染，泌尿生殖道感染，急性单纯性淋病。","img_url":"timg-2.jpeg","product_id":10,"product_name":"阿莫西林","type":"呼吸系统"},"uid":1}]
     */

    private String msg;
    private String code;
    private List<MedOrderBean> med_order;

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

    public List<MedOrderBean> getMed_order() {
        return med_order;
    }

    public void setMed_order(List<MedOrderBean> med_order) {
        this.med_order = med_order;
    }

    public static class MedOrderBean {
        /**
         * goodsid : 10
         * id : 1
         * med_user : {"id":1,"password":"123123..","phoneNumber":"111231","username":"zzz"}
         * medicine : {"cover_price":29,"details":"抗生素类药，功效主要是消除炎症，主治上、下呼吸道感染下呼吸道感染，各种皮肤和软组织感染，泌尿生殖道感染，急性单纯性淋病。","img_url":"timg-2.jpeg","product_id":10,"product_name":"阿莫西林","type":"呼吸系统"}
         * uid : 1
         */

        private int goodsid;
        private int id;
        private MedUserBean med_user;
        private MedicineBean medicine;
        private int uid;

        public int getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(int goodsid) {
            this.goodsid = goodsid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public MedUserBean getMed_user() {
            return med_user;
        }

        public void setMed_user(MedUserBean med_user) {
            this.med_user = med_user;
        }

        public MedicineBean getMedicine() {
            return medicine;
        }

        public void setMedicine(MedicineBean medicine) {
            this.medicine = medicine;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public static class MedUserBean {
            /**
             * id : 1
             * password : 123123..
             * phoneNumber : 111231
             * username : zzz
             */

            private int id;
            private String password;
            private String phoneNumber;
            private String username;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }

        public static class MedicineBean {
            /**
             * cover_price : 29.0
             * details : 抗生素类药，功效主要是消除炎症，主治上、下呼吸道感染下呼吸道感染，各种皮肤和软组织感染，泌尿生殖道感染，急性单纯性淋病。
             * img_url : timg-2.jpeg
             * product_id : 10
             * product_name : 阿莫西林
             * type : 呼吸系统
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
