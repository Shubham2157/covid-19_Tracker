package com.shubham.covid_19track;

public class HelpData {

    String number;
    String loc;

    public HelpData(String number, String loc)
    {
        this.number = number;
        this.loc = loc;
    }

    public String getNumber() {
        return number;
    }

    public String getLoc() {
        return loc;
    }

}
