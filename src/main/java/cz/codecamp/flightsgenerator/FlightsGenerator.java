package cz.codecamp.flightsgenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.google.gson.*;
import cz.codecamp.model.Flight;

/**
 * Created by Lenovo on 02.12.2016.
 */
public class FlightsGenerator {
    private String cityFrom = "Prague";
    private String cityTo = "Berlin";
    private Date sampleDateFrom;
    private Date sampleDateTo;
    private SimpleDateFormat sdf;

    private class FlightAnonymous {
        private Date depDate;
        private int nightsInDest;

        public FlightAnonymous(Date depDate, int nightsInDest) {
            this.depDate = depDate;
            this.nightsInDest = nightsInDest;
        }
    }

    public FlightsGenerator() throws ParseException {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        sampleDateFrom = sdf.parse("22/12/2007");
        sampleDateFrom.setHours(13);
        sampleDateFrom.setHours(37);
        System.out.println(sampleDateFrom);
        sampleDateTo = sdf.parse("25/12/2007");
        sampleDateTo.setHours(13);
        sampleDateTo.setHours(37);
        List<String> list = generateFlights(sampleDateFrom, sampleDateTo);
    }

    //generating dates every day from @param: start to @param: end
    private List<String> generateFlights(Date start, Date end) throws ParseException {
        for (long date = start.getTime(); date < end.getTime(); date += 86400000) {
            Date newDate = new Date(date);
            System.out.println(newDate);
            FlightAnonymous flightAnonymous = new FlightAnonymous(newDate, 2);
        }
        return null;
    }

    public static void main(String[] args) throws ParseException {
        System.out.println("hello world");
        FlightsGenerator fg = new FlightsGenerator();
    }
}
