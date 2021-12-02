package com.metacoder.transalvania.models;

import java.io.Serializable;

public class ProfileModel implements Serializable {
    String name, mail, user_id, pp;

    public ProfileModel() {
    }

    public ProfileModel(String name, String mail, String user_id, String pp) {
        this.name = name;
        this.mail = mail;
        this.user_id = user_id;
        this.pp = pp;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
