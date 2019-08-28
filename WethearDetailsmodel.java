package com.example.menu.Data;

public class WethearDetailsmodel extends RecyclerViewItems{


    public String ROW_WETHEAR_ID;
    public  double ROW_PRESSURE;
    public int ROW_HUMIDITY;
    public  double ROW_WIND_SPEED;
    public  double ROW_WIND;
    public double Row_temp_max;
    public  double Row_temp_min;
    public String ROW_DATE;

    public String getDICRYPTION() {
        return DICRYPTION;
    }

    public void setDICRYPTION(String DICRYPTION) {
        this.DICRYPTION = DICRYPTION;
    }

    public String DICRYPTION;

    public WethearDetailsmodel() {

    }

    public  double getRow_temp_max() {
        return Row_temp_max;
    }

    public  void setRow_temp_max(double row_temp_max) {
        Row_temp_max = row_temp_max;
    }

    public  double getRow_temp_min() {
        return Row_temp_min;
    }

    public  void setRow_temp_min(double row_temp_min) {
        Row_temp_min = row_temp_min;
    }


//    public WethearDetailsmodel(String wethearId,double pressure,int humidity,double speed,double deg,double max_temp,double min_temp) {
//
//        this.ROW_WETHEAR_ID=wethearId;
//        this.ROW_PRESSURE=pressure;
//        this.ROW_HUMIDITY=humidity;
//        this.ROW_WIND_SPEED=speed;
//        this.ROW_WIND=deg;
//        this.Row_temp_min=min_temp;
//        this.Row_temp_max=max_temp;
//    }

    public  String getRowWethearId() {
        return ROW_WETHEAR_ID;
    }

    public  void setRowWethearId(String rowWethearId) {
        ROW_WETHEAR_ID = rowWethearId;
    }

    public  double getRowPressure() {
        return ROW_PRESSURE;
    }

    public  void setRowPressure(double rowPressure) {
        ROW_PRESSURE = rowPressure;
    }

    public  int getRowHumidity() {
        return ROW_HUMIDITY;
    }

    public  void setRowHumidity(int rowHumidity) {
        ROW_HUMIDITY = rowHumidity;
    }

    public  double getRowWindSpeed() {
        return ROW_WIND_SPEED;
    }

    public  void setRowWindSpeed(double rowWindSpeed) {
        ROW_WIND_SPEED = rowWindSpeed;
    }

    public  double getRowWind() {
        return ROW_WIND;
    }

    public  void setRowWind(double rowWind) {
        ROW_WIND = rowWind;
    }

    public String getROW_DATE() {
        return ROW_DATE;
    }

    public void setROW_DATE(String ROW_DATE) {
        this.ROW_DATE = ROW_DATE;
    }
}
