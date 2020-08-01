package com.pkeugine.whewigo.data;

import java.io.Serializable;

public class PoiRepo implements Serializable {
    private String poiId;
    private String name;
    private String telNo;
    private String address;
    private double lat;
    private double lon;


    public PoiRepo(String poiId, String name, String telNo, String address, double lat, double lon) {
        this.poiId = poiId;
        this.name = name;
        this.telNo = telNo;
        this.address = address;
        this.lat = lat;
        this.lon = lon;
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }


}