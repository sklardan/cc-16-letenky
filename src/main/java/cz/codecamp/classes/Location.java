package cz.codecamp.classes;

import javax.persistence.*;

/**
 * Created by jakubbares on 11/13/16.
 */

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="locationId")
    private int locationId;
    @Column(name="city")
    private String city;
    @Column(name="country")
    private String country;
    @Column(name="code")
    private String code;

    public Location(String city, String country, String code) {
        this.city = city;
        this.country = country;
        this.code = code;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
