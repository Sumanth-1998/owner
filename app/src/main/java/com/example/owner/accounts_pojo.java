package com.example.owner;

import java.util.Date;

public class accounts_pojo {
    String order_id,endTime,startTime,amount;
    String startDate;
    String endDate;
    public accounts_pojo() {
    }

    public accounts_pojo(String order_id, String endTime, String startDate, String startTime,String amount,String endDate) {
        this.order_id = order_id;
        this.endTime = endTime;
        this.startDate = startDate;
        this.startTime = startTime;
        this.amount=amount;
        this.endDate=endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getAmount(){return  amount;}

    public void setAmount(String amount) {this.amount=amount; }
}
