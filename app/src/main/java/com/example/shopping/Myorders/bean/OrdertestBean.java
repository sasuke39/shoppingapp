package com.example.shopping.Myorders.bean;

import java.io.Serializable;
import java.util.List;

public class OrdertestBean implements Serializable {


    /**
     * msg : get success
     * MedOrder : [{"createTime":"2020-05-23 12:50:06","goodsid":10,"id":1,"medicine":{"cover_price":29,"details":"抗生素类药，功效主要是消除炎症，主治上、下呼吸道感染下呼吸道感染，各种皮肤和软组织感染，泌尿生殖道感染，急性单纯性淋病。","img_url":"timg-2.jpeg","product_id":10,"product_name":"阿莫西林","type":"呼吸系统"},"uid":1},{"createTime":"2020-05-23 12:50:06","goodsid":15,"id":2,"medicine":{"cover_price":159,"details":"祛斑舒缓强效装：洁面乳+滋养面膜+柔肤水+修复霜皮宝修护中药护肤套装，具有500年品牌质量保障，集合清洁皮肤，补水保湿，舒缓修复肌肤，淡化色素沉着。一套护理大概2个月，增强皮肤屏障，防止色斑再生，为肌肤保湿滋养、舒缓修复肌肤，减少外部刺激。联合护肤，让肌肤保持年轻活力","img_url":"CgAgFVzeVbGAPz5BAABcPWGw0O8472.jpg","product_id":15,"product_name":"舒缓滋养面膜","type":"五官科药"},"uid":1}]
     * code : 200
     * userId : 1
     */

    private String msg;
    private String  code;
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
         * createTime : 2020-05-23 12:50:06
         * goodsid : 10
         * id : 1
         * medicine : {"cover_price":29,"details":"抗生素类药，功效主要是消除炎症，主治上、下呼吸道感染下呼吸道感染，各种皮肤和软组织感染，泌尿生殖道感染，急性单纯性淋病。","img_url":"timg-2.jpeg","product_id":10,"product_name":"阿莫西林","type":"呼吸系统"}
         * uid : 1
         */

        private String createTime;
        private int goodsid;
        private int id;
        private MedicineBean medicine;
        private int uid;
        private Boolean selectEd=Boolean.FALSE;

        public Boolean getSelectEd() {
            return selectEd;
        }

        public void setSelectEd(Boolean selectEd) {
            this.selectEd = selectEd;
        }


        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

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
