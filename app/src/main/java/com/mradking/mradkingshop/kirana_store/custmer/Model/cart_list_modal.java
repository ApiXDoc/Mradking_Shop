package com.mradking.mradkingshop.kirana_store.custmer.Model;

public class cart_list_modal extends DocId {

    private String name_product, salling_price, product_image_url, itemId, market_price, item_id, quantity, total_amount, product_id;

    public cart_list_modal() {


    }


    public cart_list_modal(String name_product, String salling_price, String product_image_url, String itemId, String market_price, String item_id, String quantity, String total_amount, String product_id) {
        this.name_product = name_product;
        this.salling_price = salling_price;
        this.product_image_url = product_image_url;
        this.itemId = itemId;
        this.market_price = market_price;
        this.item_id = item_id;
        this.quantity = quantity;
        this.total_amount = total_amount;
        this.product_id = product_id;
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

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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