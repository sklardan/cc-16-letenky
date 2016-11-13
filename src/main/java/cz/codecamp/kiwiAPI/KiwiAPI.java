package cz.codecamp.kiwiAPI;

/**
 * Created by Lenovo on 13.11.2016.
 */


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
        // console();
        entity();
    }

    private static void entity() throws IOException, JSONException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://api.fixer.io/latest");
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

    public static void console() throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://api.fixer.io/latest");
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }
}
