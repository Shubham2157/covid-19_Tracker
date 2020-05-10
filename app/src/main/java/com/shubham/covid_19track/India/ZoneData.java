package com.shubham.covid_19track.India;

public class ZoneData {

    String district;
    String zone;

    public ZoneData(String district, String zone){
        this.district =district;
        this.zone = zone;
    }
    public String getDistrictName() {
        return district;
    }

    public String getZone() {
        return zone;
    }
}
