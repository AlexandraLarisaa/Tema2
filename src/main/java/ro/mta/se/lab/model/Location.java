package ro.mta.se.lab.model;

import javafx.beans.property.StringProperty;

public class Location {
    private String country;
    private String city;
    private String latitude;
    private String longitude;
    private String ID;

    public Location(String country, String city, String latitude, String longitude, String ID) {
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ID = ID;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getID() {
        return ID;
    }
}
