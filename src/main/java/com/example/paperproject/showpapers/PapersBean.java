package com.example.paperproject.showpapers;

public class PapersBean {
    String paper, language,price;

    public PapersBean(String paper, String price, String language) {
        this.paper = paper;
        this.price = price;
        this.language = language;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
