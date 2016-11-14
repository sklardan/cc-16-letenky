package cz.codecamp.KiwiAPI;

/**
 * Created by jakubbares on 11/13/16.
 */
public class Query {
    private static String source = "brno_cz";
    private static String destination = "nis_rs";
    private static String daysInFrom = "6";
    private static String daysInTo = "8";
    private static String typeFlight = "return";
    private static String limit = "100";

    public static String buildQuery() {
        StringBuilder callString = new StringBuilder();
        callString.append("https://api.skypicker.com/flights?v=2");
        callString.append("&sort=quality&asc=1&locale=us");
        callString.append("&daysInDestinationFrom=" + daysInFrom);
        callString.append("&daysInDestinationTo=" + daysInTo);
        callString.append("&affilid=&children=0&infants=0");
        callString.append("&flyFrom=" + source);
        callString.append("&to=" + destination);
        callString.append("&featureName=results");
        callString.append("&dateFrom=12/11/2016");
        callString.append("&dateTo=12/12/2016");
        callString.append("&typeFlight=" + typeFlight);
        callString.append("&returnFrom=&returnTo=&one_per_date=0&oneforcity=0&wait_for_refresh=0");
        callString.append("&adults=1");
        callString.append("&limit=" + limit);
        String query = callString.toString();
        System.out.println(query);
        return query;



    }
}
