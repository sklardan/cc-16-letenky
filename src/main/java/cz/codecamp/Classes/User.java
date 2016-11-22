package cz.codecamp.Classes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jakubbares on 11/13/16.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="userId")
    private int userId;
    @Column(name="loginName")
    private String loginName;
    @Column(name="password")
    private String password;
    @Column(name="cityFrom")
    private Location cityFrom;
    @JoinColumn(name = "locationId")
    @ManyToMany
    @Column(name="citiesTo")
    private ArrayList<Location> citiesTo;
    @Column(name="nightsInDestinationMin")
    private Integer nightsInDestinationMin;
    @Column(name="nightsInDestinationMax")
    private Integer nightsInDestinationMax;
    @Column(name="flyDurationMinutesMax")
    private Long flyDurationMinutesMax;
    @Column(name="flyDurationHoursMax")
    private Integer flyDurationHoursMax;
    @Column(name="dateFrom")
    private Date dateFrom;
    @Column(name="dateTo")
    private Date dateTo;

    public User(String loginName, String password){
        this.loginName = loginName;
        this.password = password;
    }

    public User() {
    }

    public void addLocation(String code){
        //locations.add(code);
    }

    public Location getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(Location cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Location> getCitiesTo() {
        return citiesTo;
    }

    public void setCitiesTo(ArrayList<Location> citiesTo) {
        this.citiesTo = citiesTo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getNightsInDestinationMin() {
        return nightsInDestinationMin;
    }

    public void setNightsInDestinationMin(Integer nightsInDestinationMin) {
        this.nightsInDestinationMin = nightsInDestinationMin;
    }

    public Integer getNightsInDestinationMax() {
        return nightsInDestinationMax;
    }

    public void setNightsInDestinationMax(Integer nightsInDestinationMax) {
        this.nightsInDestinationMax = nightsInDestinationMax;
    }

    public Long getFlyDurationMinutesMax() {
        return flyDurationMinutesMax;
    }

    public void setFlyDurationMinutesMax(Long flyDurationMinutesMax) {
        this.flyDurationMinutesMax = flyDurationMinutesMax;
    }

    public void setFlyDurationHoursToMinutesMax(Integer flyDurationHoursMax) {
        this.flyDurationMinutesMax = Long.valueOf(flyDurationHoursMax*60);
    }

    public Integer getFlyDurationHoursMax() {
        return flyDurationHoursMax;
    }

    public void setFlyDurationHoursMax(Integer flyDurationHoursMax) {
        this.flyDurationHoursMax = flyDurationHoursMax;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", cityFrom='" + cityFrom + '\'' +
                ", citiesTo=" + citiesTo +
                ", nightsInDestinationMin=" + nightsInDestinationMin +
                ", nightsInDestinationMax=" + nightsInDestinationMax +
                ", flyDurationMax=" + flyDurationMinutesMax +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}

