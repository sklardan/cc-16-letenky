package cz.codecamp.Classes;

import java.util.Date;

/**
 * Created by jakubbares on 11/13/16.
 */
public class Flight {

    private int id;
    private String source;
    private String destination;
    private int price;
    private int duration;
    private Date dTimeStamp;

    public Flight(int id,String source,String destination,int price,int duration,int dTimeStamp) {
        id = id;
        source = source;
        destination = destination;
        price = price;
        duration = duration;
        dTimeStamp = dTimeStamp;
        Date depTime=new Date((long)dTimeStamp*1000);
    }
}
