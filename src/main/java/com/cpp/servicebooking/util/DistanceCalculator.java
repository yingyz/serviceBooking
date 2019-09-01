package com.cpp.servicebooking.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DistanceCalculator {
    private double distance;

    public DistanceCalculator() {
        distance = (double) Integer.MAX_VALUE;
    }

    private void calculateDistance(int origin, int destination) throws Exception {
        String apikey = "ohMS4bV8UNtFzXeY23XnzTV8k6LBH1Dsqr8vKIcT5htdd729sw4jWfmTZqx0uMTQ";
        String url = "https://www.zipcodeapi.com/rest/" +  apikey + "/distance.json/" + origin + "/" + destination + "/mile";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String ans = modifyStr(response.toString());
        this.distance = Double.parseDouble(ans);
    }

    private String modifyStr(String str) {
        int len = str.length();
        return str.substring(12, len - 1);
    }

    public double getDistance(int origin, int destination) {
        try {
            calculateDistance(origin, destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return distance;
    }
}
