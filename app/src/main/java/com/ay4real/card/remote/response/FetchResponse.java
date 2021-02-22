package com.ay4real.card.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class FetchResponse {
    @SerializedName("number")
    private HashMap<String, String> number;

    @SerializedName("scheme")
    private String scheme;

    @SerializedName("type")
    private String type;

    @SerializedName("brand")
    private String brand;

    @SerializedName("prepaid")
    private Boolean prepaid;

    @SerializedName("country")
    private HashMap<String, String> country;

    @SerializedName("bank")
    private HashMap<String, String> bank;

    public HashMap<String, String> getNumber() {
        return number;
    }

    public void setNumber(HashMap<String, String> number) {
        this.number = number;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Boolean getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(Boolean prepaid) {
        this.prepaid = prepaid;
    }

    public HashMap<String, String> getCountry() {
        return country;
    }

    public void setCountry(HashMap<String, String> country) {
        this.country = country;
    }

    public HashMap<String, String> getBank() {
        return bank;
    }

    public void setBank(HashMap<String, String> bank) {
        this.bank = bank;
    }
}
