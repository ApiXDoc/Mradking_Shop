package com.mradking.mradkingshop.kirana_store.custmer.Model;

import com.google.firebase.Timestamp;

public class CommonModal extends DocId {
    private String name, total_amt, delivery, net_payment, phone, house_no, colony, order_date, custmer_uid, lat, lon, near_place, store_uid,
            No_of_item, order_status, item_id, order_id, payment_method, remaing_amount,pickup_date,pickup_status,dilivery_partner_uid,
            withdraw_status, withraw_request_date, withdraw_screensort_proof,pickup_otp,delivered_otp,store_address;

    private String name_product, salling_price, product_image_url, itemId,
            market_price, stock, quantity, varition_status, number_of_varition, var_1_qt, var_2_qt, var_3_qt, var_4_qt,
            var_1_mrp, var_2_mrp, var_3_mrp, var_4_mrp,
            var_1_salling_price, var_2_salling_price, var_3_salling_price,
            var_4_salling_price, var_1_stock, var_2_stock, var_3_stock, var_4_stock,
            main_category, sub_category, brand,store_message_token,store_phone,pin,state,shop_address;
    ;

    private String starting_date_sub, end_date_sub;

    private long custmers, coins, partners;

    Timestamp timestamp, withdraw_timestamp,pickup_timeStamp;


    private String shop_name, uid, shop_message_token;

    private String withdraw_date_french, Payout_status_french, withdraing_amount_french, user_uid_french, phone_number_french;


    private String total_amount, product_id;

    public CommonModal() {

    }

    public CommonModal(String name, String total_amt, String delivery, String net_payment, String phone, String house_no, String colony, String order_date, String custmer_uid, String lat, String lon, String near_place, String store_uid, String no_of_item, String order_status, String item_id, String order_id, String payment_method, String remaing_amount, String pickup_date, String pickup_status, String dilivery_partner_uid, String withdraw_status, String withraw_request_date, String withdraw_screensort_proof, String pickup_otp, String delivered_otp, String store_address, String name_product, String salling_price, String product_image_url, String itemId, String market_price, String stock, String quantity, String varition_status, String number_of_varition, String var_1_qt, String var_2_qt, String var_3_qt, String var_4_qt, String var_1_mrp, String var_2_mrp, String var_3_mrp, String var_4_mrp, String var_1_salling_price, String var_2_salling_price, String var_3_salling_price, String var_4_salling_price, String var_1_stock, String var_2_stock, String var_3_stock, String var_4_stock, String main_category, String sub_category, String brand, String store_message_token, String store_phone, String pin, String state, String shop_address, String starting_date_sub, String end_date_sub, long custmers, long coins, long partners, Timestamp timestamp, Timestamp withdraw_timestamp, Timestamp pickup_timeStamp, String shop_name, String uid, String shop_message_token, String withdraw_date_french, String payout_status_french, String withdraing_amount_french, String user_uid_french, String phone_number_french, String total_amount, String product_id) {
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
        this.pickup_date = pickup_date;
        this.pickup_status = pickup_status;
        this.dilivery_partner_uid = dilivery_partner_uid;
        this.withdraw_status = withdraw_status;
        this.withraw_request_date = withraw_request_date;
        this.withdraw_screensort_proof = withdraw_screensort_proof;
        this.pickup_otp = pickup_otp;
        this.delivered_otp = delivered_otp;
        this.store_address = store_address;
        this.name_product = name_product;
        this.salling_price = salling_price;
        this.product_image_url = product_image_url;
        this.itemId = itemId;
        this.market_price = market_price;
        this.stock = stock;
        this.quantity = quantity;
        this.varition_status = varition_status;
        this.number_of_varition = number_of_varition;
        this.var_1_qt = var_1_qt;
        this.var_2_qt = var_2_qt;
        this.var_3_qt = var_3_qt;
        this.var_4_qt = var_4_qt;
        this.var_1_mrp = var_1_mrp;
        this.var_2_mrp = var_2_mrp;
        this.var_3_mrp = var_3_mrp;
        this.var_4_mrp = var_4_mrp;
        this.var_1_salling_price = var_1_salling_price;
        this.var_2_salling_price = var_2_salling_price;
        this.var_3_salling_price = var_3_salling_price;
        this.var_4_salling_price = var_4_salling_price;
        this.var_1_stock = var_1_stock;
        this.var_2_stock = var_2_stock;
        this.var_3_stock = var_3_stock;
        this.var_4_stock = var_4_stock;
        this.main_category = main_category;
        this.sub_category = sub_category;
        this.brand = brand;
        this.store_message_token = store_message_token;
        this.store_phone = store_phone;
        this.pin = pin;
        this.state = state;
        this.shop_address = shop_address;
        this.starting_date_sub = starting_date_sub;
        this.end_date_sub = end_date_sub;
        this.custmers = custmers;
        this.coins = coins;
        this.partners = partners;
        this.timestamp = timestamp;
        this.withdraw_timestamp = withdraw_timestamp;
        this.pickup_timeStamp = pickup_timeStamp;
        this.shop_name = shop_name;
        this.uid = uid;
        this.shop_message_token = shop_message_token;
        this.withdraw_date_french = withdraw_date_french;
        Payout_status_french = payout_status_french;
        this.withdraing_amount_french = withdraing_amount_french;
        this.user_uid_french = user_uid_french;
        this.phone_number_french = phone_number_french;
        this.total_amount = total_amount;
        this.product_id = product_id;
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

    public String getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(String pickup_date) {
        this.pickup_date = pickup_date;
    }

    public String getPickup_status() {
        return pickup_status;
    }

    public void setPickup_status(String pickup_status) {
        this.pickup_status = pickup_status;
    }

    public String getDilivery_partner_uid() {
        return dilivery_partner_uid;
    }

    public void setDilivery_partner_uid(String dilivery_partner_uid) {
        this.dilivery_partner_uid = dilivery_partner_uid;
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

    public String getWithdraw_screensort_proof() {
        return withdraw_screensort_proof;
    }

    public void setWithdraw_screensort_proof(String withdraw_screensort_proof) {
        this.withdraw_screensort_proof = withdraw_screensort_proof;
    }

    public String getPickup_otp() {
        return pickup_otp;
    }

    public void setPickup_otp(String pickup_otp) {
        this.pickup_otp = pickup_otp;
    }

    public String getDelivered_otp() {
        return delivered_otp;
    }

    public void setDelivered_otp(String delivered_otp) {
        this.delivered_otp = delivered_otp;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getSalling_price() {
        return salling_price;
    }

    public void setSalling_price(String salling_price) {
        this.salling_price = salling_price;
    }

    public String getProduct_image_url() {
        return product_image_url;
    }

    public void setProduct_image_url(String product_image_url) {
        this.product_image_url = product_image_url;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getVarition_status() {
        return varition_status;
    }

    public void setVarition_status(String varition_status) {
        this.varition_status = varition_status;
    }

    public String getNumber_of_varition() {
        return number_of_varition;
    }

    public void setNumber_of_varition(String number_of_varition) {
        this.number_of_varition = number_of_varition;
    }

    public String getVar_1_qt() {
        return var_1_qt;
    }

    public void setVar_1_qt(String var_1_qt) {
        this.var_1_qt = var_1_qt;
    }

    public String getVar_2_qt() {
        return var_2_qt;
    }

    public void setVar_2_qt(String var_2_qt) {
        this.var_2_qt = var_2_qt;
    }

    public String getVar_3_qt() {
        return var_3_qt;
    }

    public void setVar_3_qt(String var_3_qt) {
        this.var_3_qt = var_3_qt;
    }

    public String getVar_4_qt() {
        return var_4_qt;
    }

    public void setVar_4_qt(String var_4_qt) {
        this.var_4_qt = var_4_qt;
    }

    public String getVar_1_mrp() {
        return var_1_mrp;
    }

    public void setVar_1_mrp(String var_1_mrp) {
        this.var_1_mrp = var_1_mrp;
    }

    public String getVar_2_mrp() {
        return var_2_mrp;
    }

    public void setVar_2_mrp(String var_2_mrp) {
        this.var_2_mrp = var_2_mrp;
    }

    public String getVar_3_mrp() {
        return var_3_mrp;
    }

    public void setVar_3_mrp(String var_3_mrp) {
        this.var_3_mrp = var_3_mrp;
    }

    public String getVar_4_mrp() {
        return var_4_mrp;
    }

    public void setVar_4_mrp(String var_4_mrp) {
        this.var_4_mrp = var_4_mrp;
    }

    public String getVar_1_salling_price() {
        return var_1_salling_price;
    }

    public void setVar_1_salling_price(String var_1_salling_price) {
        this.var_1_salling_price = var_1_salling_price;
    }

    public String getVar_2_salling_price() {
        return var_2_salling_price;
    }

    public void setVar_2_salling_price(String var_2_salling_price) {
        this.var_2_salling_price = var_2_salling_price;
    }

    public String getVar_3_salling_price() {
        return var_3_salling_price;
    }

    public void setVar_3_salling_price(String var_3_salling_price) {
        this.var_3_salling_price = var_3_salling_price;
    }

    public String getVar_4_salling_price() {
        return var_4_salling_price;
    }

    public void setVar_4_salling_price(String var_4_salling_price) {
        this.var_4_salling_price = var_4_salling_price;
    }

    public String getVar_1_stock() {
        return var_1_stock;
    }

    public void setVar_1_stock(String var_1_stock) {
        this.var_1_stock = var_1_stock;
    }

    public String getVar_2_stock() {
        return var_2_stock;
    }

    public void setVar_2_stock(String var_2_stock) {
        this.var_2_stock = var_2_stock;
    }

    public String getVar_3_stock() {
        return var_3_stock;
    }

    public void setVar_3_stock(String var_3_stock) {
        this.var_3_stock = var_3_stock;
    }

    public String getVar_4_stock() {
        return var_4_stock;
    }

    public void setVar_4_stock(String var_4_stock) {
        this.var_4_stock = var_4_stock;
    }

    public String getMain_category() {
        return main_category;
    }

    public void setMain_category(String main_category) {
        this.main_category = main_category;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStore_message_token() {
        return store_message_token;
    }

    public void setStore_message_token(String store_message_token) {
        this.store_message_token = store_message_token;
    }

    public String getStore_phone() {
        return store_phone;
    }

    public void setStore_phone(String store_phone) {
        this.store_phone = store_phone;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getStarting_date_sub() {
        return starting_date_sub;
    }

    public void setStarting_date_sub(String starting_date_sub) {
        this.starting_date_sub = starting_date_sub;
    }

    public String getEnd_date_sub() {
        return end_date_sub;
    }

    public void setEnd_date_sub(String end_date_sub) {
        this.end_date_sub = end_date_sub;
    }

    public long getCustmers() {
        return custmers;
    }

    public void setCustmers(long custmers) {
        this.custmers = custmers;
    }

    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }

    public long getPartners() {
        return partners;
    }

    public void setPartners(long partners) {
        this.partners = partners;
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

    public Timestamp getPickup_timeStamp() {
        return pickup_timeStamp;
    }

    public void setPickup_timeStamp(Timestamp pickup_timeStamp) {
        this.pickup_timeStamp = pickup_timeStamp;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getShop_message_token() {
        return shop_message_token;
    }

    public void setShop_message_token(String shop_message_token) {
        this.shop_message_token = shop_message_token;
    }

    public String getWithdraw_date_french() {
        return withdraw_date_french;
    }

    public void setWithdraw_date_french(String withdraw_date_french) {
        this.withdraw_date_french = withdraw_date_french;
    }

    public String getPayout_status_french() {
        return Payout_status_french;
    }

    public void setPayout_status_french(String payout_status_french) {
        Payout_status_french = payout_status_french;
    }

    public String getWithdraing_amount_french() {
        return withdraing_amount_french;
    }

    public void setWithdraing_amount_french(String withdraing_amount_french) {
        this.withdraing_amount_french = withdraing_amount_french;
    }

    public String getUser_uid_french() {
        return user_uid_french;
    }

    public void setUser_uid_french(String user_uid_french) {
        this.user_uid_french = user_uid_french;
    }

    public String getPhone_number_french() {
        return phone_number_french;
    }

    public void setPhone_number_french(String phone_number_french) {
        this.phone_number_french = phone_number_french;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}