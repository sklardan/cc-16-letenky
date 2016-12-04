package cz.codecamp.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jakubbares on 11/13/16.
 */
public class Query {

    public Query(){};

    public String buildQuery(Integer daysInFrom, Integer daysInTo, String codeFrom, String codeTo, Date dateFrom, Date dateTo, String typeFlight, Integer limit) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dateFromString = format.format(dateFrom);
        String dateToString = format.format(dateTo);
        StringBuilder callString = new StringBuilder();
        callString.append("https://api.skypicker.com/flights?v=2");
        callString.append("&sort=quality&asc=1&locale=us");
        callString.append("&daysInDestinationFrom=" + daysInFrom);
        callString.append("&daysInDestinationTo=" + daysInTo);
        callString.append("&affilid=&children=0&infants=0");
        callString.append("&flyFrom=" + codeFrom);
        callString.append("&to=" + codeTo);
        callString.append("&featureName=results");
        callString.append("&dateFrom="+dateFromString);
        callString.append("&dateTo="+dateToString);
        callString.append("&typeFlight=" + typeFlight);
        callString.append("&returnFrom=&returnTo=&one_per_date=0&oneforcity=0&wait_for_refresh=0");
        callString.append("&adults=1");
        callString.append("&limit=" + limit);
        String query = callString.toString();
        return query;



    }
}
