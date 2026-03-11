package com.example.paperproject.billcollector;

public class UserBillBean {
    String from,to,lessdays,bill;

    public UserBillBean(String from, String to, String lessdays, String bill) {
        this.from = from;
        this.to = to;
        this.lessdays = lessdays;
        this.bill = bill;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getLessdays() {
        return lessdays;
    }

    public void setLessdays(String lessdays) {
        this.lessdays = lessdays;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }
}
