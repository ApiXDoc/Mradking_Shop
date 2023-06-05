package com.mradking.mradkingshop.kirana_store.Admin_panel.modal;

import com.mradking.mradkingshop.kirana_store.custmer.Model.DocId;

public class Userlist extends DocId {

    String name,phone,shop_message_token,store_uid,uid,user_message_token,lat,lon,itemId;
    public Userlist(){


    }


    public Userlist(String name, String phone, String shop_message_token, String store_uid, String uid, String user_message_token, String lat, String lon, String itemId) {
        this.name = name;
        this.phone = phone;
        this.shop_message_token = shop_message_token;
        this.store_uid = store_uid;
        this.uid = uid;
        this.user_message_token = user_message_token;
        this.lat = lat;
        this.lon = lon;
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShop_message_token() {
        return shop_message_token;
    }

    public void setShop_message_token(String shop_message_token) {
        this.shop_message_token = shop_message_token;
    }

    public String getStore_uid() {
        return store_uid;
    }

    public void setStore_uid(String store_uid) {
        this.store_uid = store_uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_message_token() {
        return user_message_token;
    }

    public void setUser_message_token(String user_message_token) {
        this.user_message_token = user_message_token;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
