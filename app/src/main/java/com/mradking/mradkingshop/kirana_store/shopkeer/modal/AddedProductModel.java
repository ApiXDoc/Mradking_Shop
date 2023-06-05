package com.mradking.mradkingshop.kirana_store.shopkeer.modal;

import com.mradking.mradkingshop.kirana_store.custmer.Model.DocId;

public class AddedProductModel extends DocId {

    private String name_product, salling_price, product_image_url, itemId,
            market_price, stock, quantity, varition_status, number_of_varition, var_1_qt, var_2_qt, var_3_qt, var_4_qt,
            var_1_mrp, var_2_mrp, var_3_mrp, var_4_mrp,
            var_1_salling_price, var_2_salling_price, var_3_salling_price,
            var_4_salling_price, var_1_stock, var_2_stock, var_3_stock, var_4_stock,
            main_category, sub_category, brand;;

    public AddedProductModel() {


    }


    public AddedProductModel(String name_product, String salling_price, String product_image_url, String itemId, String market_price, String stock, String quantity, String varition_status, String number_of_varition, String var_1_qt, String var_2_qt, String var_3_qt, String var_4_qt, String var_1_mrp, String var_2_mrp, String var_3_mrp, String var_4_mrp, String var_1_salling_price, String var_2_salling_price, String var_3_salling_price, String var_4_salling_price, String var_1_stock, String var_2_stock, String var_3_stock, String var_4_stock, String main_category, String sub_category, String brand) {
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
}