package com.tch.work.dailywork;

public class Locations {

    private String globalid;
    private String name;
    private String geocategoryid;

    private String type;

    private String  hotelid;

    private String hotelname;

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getGlobalid() {
        return globalid;
    }

    public void setGlobalid(String globalid) {
        this.globalid = globalid;
    }

    public String getGeocategoryid() {
        return geocategoryid;
    }

    public void setGeocategoryid(String geocategoryid) {
        this.geocategoryid = geocategoryid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
