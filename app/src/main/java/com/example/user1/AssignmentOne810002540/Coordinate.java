package com.example.user1.AssignmentOne810002540;


public class Coordinate {

    private int id;
    private float longtitude;
    private float latitude;


    private String sensorName;
    private float xVal;
    private float yVal;
    private float zVal;

    public Coordinate(){}

    public Coordinate(float longtitude, float latitude) {
        super();
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    public Coordinate(String sensorName, float xVal, float yVal, float zVal) {
        super();
        this.xVal = xVal;
        this.yVal = yVal;
        this.zVal = zVal;
        this.sensorName = sensorName;

    }

    //getters & setters

    @Override
    public String toString() {
        return "Coordinates [id=" + id + ", Logtitude=" + longtitude + ", Latitude=" + latitude
                + "]";
    }

    public float getLongtitude(){
        return this.longtitude;
    }

    public float getLatitude(){
        return this.latitude;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setLongtitude(float longtitude){
        this.longtitude = longtitude;
    }

    public void setLatitude(float latitude){
        this.latitude = latitude;
    }

    public float getxVal() {
        return xVal;
    }

    public void setxVal(float xVal) {
        this.xVal = xVal;
    }

    public float getyVal() {
        return yVal;
    }

    public void setyVal(float yVal) {
        this.yVal = yVal;
    }

    public float getzVal() {
        return zVal;
    }

    public void setzVal(float zVal) {
        this.zVal = zVal;
    }


    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }
}