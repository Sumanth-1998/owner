package com.example.owner;

public class notifications_pojo {
    String marker,order_id,endTime,startDate,startTime,msg;

    public notifications_pojo(String marker, String order_id, String endTime, String startDate, String startTime) {
        this.marker = marker;
        this.order_id = order_id;
        this.endTime = endTime;
        this.startDate = startDate;
        this.startTime = startTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
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
}
