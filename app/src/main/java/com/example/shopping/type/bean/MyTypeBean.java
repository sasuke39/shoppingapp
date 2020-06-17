package com.example.shopping.type.bean;

import java.io.Serializable;
import java.util.List;

public class MyTypeBean implements Serializable {


    /**
     * msg : success
     * code : 200
     * medicineBeanList : [{"cover_price":199,"details":"功能主治：保持血管畅通，降低血液粘稠度，具有降血脂、预防血栓的功效，适合中老年人群日常保养。","img_url":"047e267dc96e4f6f864c0183905f7cb0.jpg","product_id":31,"product_name":"gnc健安喜美国深海鱼油","type":"心脑血管"},{"cover_price":159,"details":"功能主治：微细乳化制法提高吸收率，嫩肤抗衰解压抗疲","img_url":"f0f4cc1f187b49bd9108c55d5a3aed6d.jpg","product_id":34,"product_name":"FANCL辅酶Q10心脑血管保护抗氧化胶囊","type":"心脑血管"},{"cover_price":156,"details":"功能主治：降低血管粘稠度，增加血管的弹性，避免血管硬化，预防心脏病、动脉硬化、冠心病、高血压、四脂冰冷，抑制血液凝固，防止血栓形成，减少脂肪的形成，防止肥胖症产生，预防老年痴呆症。","img_url":"7f53c72c0c5a4c2f91d8c7a21eaa22e5.jpg","product_id":36,"product_name":"德国双心浓缩DHA","type":"心脑血管"},{"cover_price":72,"details":"功能主治：心绞痛发作的预防性治疗，眩晕和耳鸣的辅助性对症治疗。","img_url":"8f6edd247e6b49fb8ae7cee37e260e9b.jpg","product_id":38,"product_name":"万爽力盐酸曲美他嗪缓释片","type":"心脑血管"},{"cover_price":34.5,"details":"功能主治：益气通脉，宁心安神，生津止渴。用于胸痹、心悸、不寐，消渴气虚证，症见痛胸闷；心悸不安，失眠健忘，口渴多饮气短乏力；冠心病，心绞痛，心律失常，神经衰弱，2型糖尿病见上述证候者。","img_url":"109b80b01bfa4719952f223e2e7ebb9b.jpg","product_id":40,"product_name":"益盛振源胶囊","type":"心脑血管"},{"cover_price":173,"details":"功能主治：健康血脂血压，优化肝脏环境，增强大脑环境","img_url":"b650daf78ef94559a2e2a505b5729e9c.jpg","product_id":42,"product_name":"双心大豆卵磷脂胶囊","type":"心脑血管"},{"cover_price":26.9,"details":"功能主治：用于心脑血管疾病及习惯性流产不孕症的辅助治疗。","img_url":"7e8fbdf4d7174797b088dfeaa31004cb.jpg","product_id":44,"product_name":"白云山维生素E软胶囊","type":"心脑血管"},{"cover_price":68,"details":"功能主治： 用于心、脑血管疾病及习惯性流产、不孕症的辅助治疗。","img_url":"d97b8450cc384fffa203b69def091736.jpg","product_id":45,"product_name":"修正维生素E软胶囊","type":"心脑血管"},{"cover_price":49,"details":"功能主治：原发性高胆固醇血症，纯合子家族性高胆固醇血症(HoFH)，纯合子谷甾醇血症(或植物甾醇血症)，详见说明书。","img_url":"03d6dd68f2f946f1af8c83402ebbb519.jpg","product_id":46,"product_name":"益适纯依折布麦片","type":"心脑血管"},{"cover_price":43.2,"details":"功能主治：用于心、脑血管疾病及习惯性流产、不孕症的辅助治疗。长期过量服用可引起恶心、呕吐、眩晕、头痛、视力模糊、皮肤皲裂、唇炎、口角炎、腹泻、乳腺肿大、乏力。","img_url":"00e0a44e8bb744fd9b2b80454a02583b.jpg","product_id":47,"product_name":"来益维生素E软胶囊","type":"心脑血管"},{"cover_price":42.1,"details":"功能主治：用于高血压病、脑血栓、脑动脉硬化引起的头晕、头胀、头痛、目眩、肢体麻木以及心脑血管疾病引起的偏瘫等病症。","img_url":"462fa8e3ae2a4e1b8ea2868e512f449c.jpg","product_id":48,"product_name":"复方天麻蜜环糖肽片","type":"心脑血管"}]
     */

    private String msg;
    private int code;
    private List<MedicineBeanListBean> medicineBeanList;

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

    public List<MedicineBeanListBean> getMedicineBeanList() {
        return medicineBeanList;
    }

    public void setMedicineBeanList(List<MedicineBeanListBean> medicineBeanList) {
        this.medicineBeanList = medicineBeanList;
    }

    public static class MedicineBeanListBean {
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
