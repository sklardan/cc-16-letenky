package cz.codecamp.model;

import cz.codecamp.repository.LocationRepository;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jakubbares on 11/13/16.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="userId")
    private int userId;
    @Column(name="emailLogin")
    private String emailLogin;
    @Column(name="password")
    private String password;
    @Column(name="cityFrom")
    private Location cityFrom;

    @JoinColumn(name = "locationId")
    @ManyToMany
    @Column(name="citiesTo")
    private List<Location> citiesTo;

    @Column(name="nightsInDestinationMin")
    private Integer nightsInDestinationMin;
    @Column(name="nightsInDestinationMax")
    private Integer nightsInDestinationMax;
    @Column(name="flyDurationMinutesMax")
    private Long flyDurationMinutesMax;
    @Column(name="flyDurationHoursMax")
    private Integer flyDurationHoursMax;
    @Column(name="pctAvgPriceMax")
    private Float pctAvgPriceMax;

    @Column(name="dateFrom")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date dateFrom;

    @Column(name="dateTo")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date dateTo;

    public User(String emailLogin, String password){
        this.emailLogin = emailLogin;
        this.password = password;
    }

    public User() {
    }

    public void addLocation(String cityTo, LocationRepository repository){
        Location location = repository.findByCity(cityTo);
        List<Location> locations = getCitiesTo();
        locations.add(location);
        setCitiesTo(locations);
    }

    public Float getPctAvgPriceMax() {
        return pctAvgPriceMax;
    }

    public void setPctAvgPriceMax(Float pctAvgPriceMax) {
        this.pctAvgPriceMax = pctAvgPriceMax;
    }

    public Location getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(Location cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getEmailLogin() {
        return emailLogin;
    }

    public void setEmailLogin(String emailLogin) {
        this.emailLogin = emailLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Location> getCitiesTo() {
        return citiesTo;
    }

    public void setCitiesTo(List<Location> citiesTo) {
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
                ", emailLogin='" + emailLogin + '\'' +
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

