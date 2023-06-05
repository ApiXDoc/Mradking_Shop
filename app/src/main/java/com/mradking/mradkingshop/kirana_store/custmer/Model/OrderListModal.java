package com.mradking.mradkingshop.kirana_store.custmer.Model;

import com.google.firebase.Timestamp;

public class OrderListModal extends DocId {

    private String name, total_amt, delivery, net_payment, phone, house_no, colony, order_date, custmer_uid, lat, lon, near_place, store_uid,
            No_of_item, order_status, item_id, order_id, payment_method, remaing_amount,
            withdraw_status,withraw_request_date,withdraw_screensort_proof;


    Timestamp timestamp,withdraw_timestamp;


    public OrderListModal() {


    }

    public String getWithdraw_screensort_proof() {
        return withdraw_screensort_proof;
    }

    public void setWithdraw_screensort_proof(String withdraw_screensort_proof) {
        this.withdraw_screensort_proof = withdraw_screensort_proof;
    }

    public OrderListModal(String name, String total_amt, String delivery, String net_payment, String phone, String house_no, String colony, String order_date, String custmer_uid, String lat, String lon, String near_place, String store_uid, String no_of_item, String order_status, String item_id, String order_id, String payment_method, String remaing_amount, String withdraw_status, String withraw_request_date, String withdraw_screensort_proof, Timestamp timestamp, Timestamp withdraw_timestamp) {
        this.name = name;
        this.total_amt = total_amt;
        this.delivery = delivery;
        this.net_payment = net_payment;
        this.phone = phone;
        this.house_no = house_no;
        this.colony = colony;
        this.order_date = order_date;
        this.custmer_uid = custmer_uid;
        this.lat = lat;
        this.lon = lon;
        this.near_place = near_place;
        this.store_uid = store_uid;
        No_of_item = no_of_item;
        this.order_status = order_status;
        this.item_id = item_id;
        this.order_id = order_id;
        this.payment_method = payment_method;
        this.remaing_amount = remaing_amount;
        this.withdraw_status = withdraw_status;
        this.withraw_request_date = withraw_request_date;
        this.withdraw_screensort_proof = withdraw_screensort_proof;
        this.timestamp = timestamp;
        this.withdraw_timestamp = withdraw_timestamp;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal_amt() {
        return total_amt;
    }

    public void setTotal_amt(String total_amt) {
        this.total_amt = total_amt;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getNet_payment() {
        return net_payment;
    }

    public void setNet_payment(String net_payment) {
        this.net_payment = net_payment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHouse_no() {
        return house_no;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getCustmer_uid() {
        return custmer_uid;
    }

    public void setCustmer_uid(String custmer_uid) {
        this.custmer_uid = custmer_uid;
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

    public String getNear_place() {
        return near_place;
    }

    public void setNear_place(String near_place) {
        this.near_place = near_place;
    }

    public String getStore_uid() {
        return store_uid;
    }

    public void setStore_uid(String store_uid) {
        this.store_uid = store_uid;
    }

    public String getNo_of_item() {
        return No_of_item;
    }

    public void setNo_of_item(String no_of_item) {
        No_of_item = no_of_item;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getRemaing_amount() {
        return remaing_amount;
    }

    public void setRemaing_amount(String remaing_amount) {
        this.remaing_amount = remaing_amount;
    }

    public String getWithdraw_status() {
        return withdraw_status;
    }

    public void setWithdraw_status(String withdraw_status) {
        this.withdraw_status = withdraw_status;
    }

    public String getWithraw_request_date() {
        return withraw_request_date;
    }

    public void setWithraw_request_date(String withraw_request_date) {
        this.withraw_request_date = withraw_request_date;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Timestamp getWithdraw_timestamp() {
        return withdraw_timestamp;
    }

    public void setWithdraw_timestamp(Timestamp withdraw_timestamp) {
        this.withdraw_timestamp = withdraw_timestamp;
    }
}