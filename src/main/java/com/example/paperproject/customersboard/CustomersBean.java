package com.example.paperproject.customersboard;

public class CustomersBean {
    String mobile,name,email,address,dos,area,hawker,paper,price,status;

    public CustomersBean(String mobile, String status, String price, String paper, String hawker, String area, String dos, String address, String email, String name) {
        this.mobile = mobile;
        this.status = status;
        this.price = price;
        this.paper = paper;
        this.hawker = hawker;
        this.area = area;
        this.dos = dos;
        this.address = address;
        this.email = email;
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getHawker() {
        return hawker;
    }

    public void setHawker(String hawker) {
        this.hawker = hawker;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
