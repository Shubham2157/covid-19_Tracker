package com.shubham.covid_19track.India;

public class ZoneData {

    String district;
    String zone;
    String state;

    public ZoneData(String district, String zone, String state){
        this.district =district;
        this.zone = zone;
        this.state = state;
    }
    public String getDistrictName() {
        return district;
    }

    public String getZone() {
        return zone;
    }

    public String getStateName() {
        return state;
    }
}
