package cz.codecamp.Classes;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jakubbares on 11/13/16.
 */

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private long id;
    @Column(name="cityFrom")
    private String cityFrom;
    @Column(name="cityTo")
    private String cityTo;
    @Column(name="price")
    private int price;
    @Column(name="nightsIdDest")
    private int nightsInDest;
    @Column(name="flyDuration")
    private String flyDuration;
    @Column(name="depTime")
    private Date depTime;

    protected Flight() {}

    public Flight(String cFrom, String cTo, int pri, int nights, String flyDur, int dTimeStamp) {
        this.cityFrom = cFrom;

        this.cityTo = cTo;
        this.price = pri;
        this.nightsInDest = nights;
        this.flyDuration = flyDur;
        this.depTime=new Date((long)dTimeStamp*1000);
    }

    @Override
    public String toString() {
        return String.format(
                "Flights[id=%d, cityFrom='%s', cityTo='%s',  price='%d',  nightsInDest='%d',  flyDuration='%s' , depTime='%s']",
                id, cityFrom, cityTo, price, nightsInDest, flyDuration, depTime);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNightsInDest() {
        return nightsInDest;
    }

    public void setNightsInDest(int nightsInDest) {
        this.nightsInDest = nightsInDest;
    }

    public String getFlyDuration() {
        return flyDuration;
    }

    public void setFlyDuration(String flyDuration) {
        this.flyDuration = flyDuration;
    }

    public Date getDepTime() {
        return depTime;
    }

    public void setDepTime(Date depTime) {
        this.depTime = depTime;
    }
}
