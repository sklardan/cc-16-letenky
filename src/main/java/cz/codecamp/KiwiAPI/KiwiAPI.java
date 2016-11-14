package cz.codecamp.KiwiAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class KiwiAPI {

    public static void main(String[] args) throws IOException, JSONException {
        entity();
    }

    private static void entity() throws IOException, JSONException {
        RestTemplate restTemplate = new RestTemplate();
        String query = Query.buildQuery();
        ResponseEntity<String> response = restTemplate.getForEntity(query, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode data = root.get("data");
        for (JsonNode flight : data) {
            JsonNode flyDuration = flight.get("fly_duration");
            JsonNode price = flight.get("conversion").get("EUR");
            JsonNode nightsInDest = flight.get("nightsInDest");
            JsonNode cityTo = flight.get("cityTo");
            JsonNode cityFrom = flight.get("cityFrom");
            JsonNode route = flight.get("route");
            for (JsonNode interFlight : route) {
                JsonNode IFcityFrom = interFlight.get("cityFrom");
                JsonNode IFcityTo = interFlight.get("cityTo");
                System.out.println(IFcityFrom);
            }

        }
        

        System.out.println(data);


        JsonObject jsonObject = new JsonParser().parse("{\"name\": \"John\"}").getAsJsonObject();
        System.out.println(jsonObject.get("name").getAsString()); //John



//        JSONObject obj = new JSONObject(root);
//        String test2 = obj.getJSONObject("search_params").getString("to_type");

//        JSONArray arr = obj.getJSONArray("posts");
//        for (int i = 0; i < arr.length(); i++)
//        {
//            String post_id = arr.getJSONObject(i).getString("post_id");
//
//        }

    }


}
