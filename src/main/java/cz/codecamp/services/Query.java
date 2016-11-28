package cz.codecamp.services;

/**
 * Created by jakubbares on 11/13/16.
 */
public class Query {

    public Query(){};

    public String buildQuery(Integer daysInFrom, Integer daysInTo, String codeFrom, String codeTo, String typeFlight, Integer limit) {
        StringBuilder callString = new StringBuilder();
        callString.append("https://api.skypicker.com/flights?v=2");
        callString.append("&sort=quality&asc=1&locale=us");
        callString.append("&daysInDestinationFrom=" + daysInFrom);
        callString.append("&daysInDestinationTo=" + daysInTo);
        callString.append("&affilid=&children=0&infants=0");
        callString.append("&flyFrom=" + codeFrom);
        callString.append("&to=" + codeTo);
        callString.append("&featureName=results");
        callString.append("&dateFrom=12/11/2016");
        callString.append("&dateTo=12/12/2016");
        callString.append("&typeFlight=" + typeFlight);
        callString.append("&returnFrom=&returnTo=&one_per_date=0&oneforcity=0&wait_for_refresh=0");
        callString.append("&adults=1");
        callString.append("&limit=" + limit);
        String query = callString.toString();
        return query;



    }
}
