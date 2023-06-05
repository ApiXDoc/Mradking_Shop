package com.mradking.mradkingshop.kirana_store.shopkeer.modal;

import com.mradking.mradkingshop.kirana_store.custmer.Model.DocId;

public class ProductListImageModal extends DocId {

    public ProductListImageModal(){


    }

    String product_image_url,market_price,name_product,item_id,sub_category,main_category,quantity,brand;

    public ProductListImageModal(String product_image_url, String market_price, String name_product, String item_id, String sub_category, String main_category, String quantity, String brand) {
        this.product_image_url = product_image_url;
        this.market_price = market_price;
        this.name_product = name_product;
        this.item_id = item_id;
        this.sub_category = sub_category;
        this.main_category = main_category;
        this.quantity = quantity;
        this.brand = brand;
    }

    public String getProduct_image_url() {
        return product_image_url;
    }

    public void setProduct_image_url(String product_image_url) {
        this.product_image_url = product_image_url;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getMain_category() {
        return main_category;
    }

    public void setMain_category(String main_category) {
        this.main_category = main_category;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
