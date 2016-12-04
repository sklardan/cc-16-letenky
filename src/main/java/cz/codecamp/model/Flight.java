package cz.codecamp.model;

import cz.codecamp.repository.FlightRepository;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jakubbares on 11/13/16.
 */

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="flightId")
    private long flightId;

    @Column(name="dateAdded")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date dateAdded;
    @Column(name="cityFrom")
    private String cityFrom;
    @Column(name="cityTo")
    private String cityTo;
    @Column(name="price")
    private int price;

    @Column(name="flyDurationMinutes")
    private Integer flyDurationMinutes;

    @Column(name="avgPrice")
    private int avgPrice;

    @Column(name="depDate")
    private Date depDate;

    @Column(name="score")
    private Integer score;

    private static final AtomicInteger count = new AtomicInteger(1);

    public Flight() {}

    public Flight(String cityFrom, String cityTo, int price, Integer flyDur, long dTimeStamp) {
        this.flightId =  count.incrementAndGet();
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.price = price;
        this.flyDurationMinutes = flyDur;
        this.depDate=new Date(dTimeStamp);
        this.dateAdded = new Date();
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                ", price=" + price +
                ", flyDurationMinutes=" + flyDurationMinutes +
                ", depDate=" + depDate +
                '}';
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded() {
        Date dateAdded = new Date();
        this.dateAdded = dateAdded;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
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

    public Integer getFlyDurationMinutes() { return flyDurationMinutes;}

    public void setFlyDurationMinutes(Integer flyDurationMinutes) {
        this.flyDurationMinutes = flyDurationMinutes;
    }

    public Date getDepDate() {
        return depDate;
    }

    public void setDepDate(Date depDate) {
        this.depDate = depDate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public int getAvgPrice() { return avgPrice; }

    public void setAvgPrice(int avgPrice) { this.avgPrice = avgPrice; }
}
