package cz.codecamp.KiwiAPI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KiwiAPI {

    public static void main(String[] args) throws IOException, JSONException {
        entity();
    }

    private static void entity() throws IOException, JSONException {
        HttpClient client = new DefaultHttpClient();
        String query = Query.buildQuery();
        HttpGet request = new HttpGet(query);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        JSONObject json = new JSONObject(result.toString());
        System.out.println(json);
    }

}
