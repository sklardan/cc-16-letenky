package cz.codecamp.Classes;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jakubbares on 11/13/16.
 */

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="flightId")
    private long flightId;
    @Column(name="cityFrom")
    private String cityFrom;
    @Column(name="cityTo")
    private String cityTo;
    @Column(name="price")
    private int price;
    @Column(name="nightsIdDest")
    private int nightsInDest;
    @Column(name="flyDurationMinutes")
    private Long flyDurationMinutes;
    @Column(name="depTime")
    private Date depTime;
    @Column(name="score")
    private Integer score;

    DateFormat format = new SimpleDateFormat("HH- mm-");


    public Flight() {}

    public Flight(String cFrom, String cTo, int pri, int nights, String flyDur, int dTimeStamp) throws ParseException {
        this.cityFrom = cFrom;

        this.cityTo = cTo;
        this.price = pri;
        this.nightsInDest = nights;
        flyDur = flyDur.replace('h','-').replace('m','-');
        Date flyDurationDate = format.parse(flyDur);
        this.flyDurationMinutes = flyDurationDate.getTime() / 60000;
        this.depTime=new Date((long)dTimeStamp*1000);

    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                ", price=" + price +
                ", nightsInDest=" + nightsInDest +
                ", flyDurationMinutes=" + flyDurationMinutes +
                ", depTime=" + depTime +
                ", score=" + score +
                '}';
    }

    public long getId() {
        return flightId;
    }

    public void setId(long flightId) {
        this.flightId = flightId;
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

    public Long getFlyDurationMinutes() { return flyDurationMinutes;}

    public void setFlyDurationMinutes(Long flyDurationMinutes) {
        this.flyDurationMinutes = flyDurationMinutes;
    }

    public Date getDepTime() {
        return depTime;
    }

    public void setDepTime(Date depTime) {
        this.depTime = depTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public DateFormat getFormat() {
        return format;
    }

    public void setFormat(DateFormat format) {
        this.format = format;
    }
}
