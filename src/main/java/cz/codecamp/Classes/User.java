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
        loginName = loginName;
        password = password;
    }

    public void addLocation(String code){
        //locations.add(code);
    }

}
