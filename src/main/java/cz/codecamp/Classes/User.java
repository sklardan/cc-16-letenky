package cz.codecamp.Classes;

import java.util.ArrayList;

/**
 * Created by jakubbares on 11/13/16.
 */
public class User {

    private String loginName;
    private String password;
    private ArrayList<Location> locations;

    public User(String loginName, String password){
        this.loginName = loginName;
        this.password = password;
    }

    public User() {
    }

    public void addLocation(String code){
        //locations.add(code);
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

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }
}
