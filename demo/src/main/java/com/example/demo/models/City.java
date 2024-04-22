package com.example.demo.models;

public class City {
    private String name;
    private String region;
    private String country;
    private Coordinates coordinates;

    public City(String name, String region, String country, Coordinates coordinates) {
        this.name = name;
        this.region = region;
        this.country = country;
        this.coordinates = coordinates;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    };
}
