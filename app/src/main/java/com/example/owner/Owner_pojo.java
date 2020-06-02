package com.example.owner;

import java.util.Date;

public class Owner_pojo
{
        String Email,Idcard,Latitude,Longitude,Name,Phone;
        boolean status;
        Date date,last_online;
        long sec_online;

    public Owner_pojo(String email, String idcard, String latitude, String longitude, String name, String phone,boolean status) {
        Email = email;
        Idcard = idcard;
        Latitude = latitude;
        Longitude = longitude;
        Name = name;
        Phone = phone;
        this.status=status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getLast_online() {
        return last_online;
    }

    public void setLast_online(Date last_online) {
        this.last_online = last_online;
    }

    public long getSec_online() {
        return sec_online;
    }

    public void setSec_online(long sec_online) {
        this.sec_online = sec_online;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getIdcard() {
        return Idcard;
    }

    public void setIdcard(String idcard) {
        Idcard = idcard;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Owner_pojo() {
    }


}
