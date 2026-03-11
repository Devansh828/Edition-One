package com.example.paperproject.billboard;

public class BillsBean {
    String rid,mobile,dos,doe,totaldays,lessdays,bill;

    public BillsBean(String rid, String mobile, String dos, String doe, String totaldays, String lessdays, String bill) {
        this.rid = rid;
        this.mobile = mobile;
        this.dos = dos;
        this.doe = doe;
        this.totaldays = totaldays;
        this.lessdays = lessdays;
        this.bill = bill;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getLessdays() {
        return lessdays;
    }

    public void setLessdays(String lessdays) {
        this.lessdays = lessdays;
    }

    public String getTotaldays() {
        return totaldays;
    }

    public void setTotaldays(String totaldays) {
        this.totaldays = totaldays;
    }

    public String getDoe() {
        return doe;
    }

    public void setDoe(String doe) {
        this.doe = doe;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
