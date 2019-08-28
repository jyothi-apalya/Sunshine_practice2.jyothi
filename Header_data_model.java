package com.example.menu.Data;

public class Header_data_model extends RecyclerViewItems {

//    public Header_data_model(String header_date, double header_temp_maximum, double header_temp_minimum, String discryption) {
//        this.header_date = header_date;
//        this.header_temp_maximum = header_temp_maximum;
//        this.header_temp_minimum = header_temp_minimum;
//        this.discryption = discryption;
//    }

    public Header_data_model() {

    }

    public String getHeader_date() {
        return header_date;
    }

    public void setHeader_date(String header_date) {
        this.header_date = header_date;
    }

    public double getHeader_temp_maximum() {
        return header_temp_maximum;
    }

    public void setHeader_temp_maximum(double header_temp_maximum) {
        this.header_temp_maximum = header_temp_maximum;
    }

    public double getHeader_temp_minimum() {
        return header_temp_minimum;
    }

    public void setHeader_temp_minimum(double header_temp_minimum) {
        this.header_temp_minimum = header_temp_minimum;
    }

    public String getDiscryption() {
        return discryption;
    }

    public void setDiscryption(String discryption) {
        this.discryption = discryption;
    }

    String header_date;
    double header_temp_maximum;
    double header_temp_minimum;
    String discryption;
}
