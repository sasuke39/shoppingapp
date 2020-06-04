package com.example.shopping.user.bean;

import java.io.Serializable;

public class UserBean implements Serializable {

    /**
     * msg : 登陆成功
     * code : 200
     * Med_user : {"id":1,"password":"123123..","phoneNumber":"111231","username":"zzz"}
     */

    private String msg;
    private String code;
    private MedUserBean Med_user;

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

    public MedUserBean getMed_user() {
        return Med_user;
    }

    public void setMed_user(MedUserBean Med_user) {
        this.Med_user = Med_user;
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
        private String user_icon;

        @Override
        public String toString() {
            return "MedUserBean{" +
                    "id=" + id +
                    ", password='" + password + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", username='" + username + '\'' +
                    ", user_icon='" + user_icon + '\'' +
                    '}';
        }

        public String getUser_icon() {
            return user_icon;
        }

        public void setUser_icon(String user_icon) {
            this.user_icon = user_icon;
        }

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

    @Override
    public String toString() {
        return "UserBean{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", Med_user=" + Med_user +
                '}';
    }
}
