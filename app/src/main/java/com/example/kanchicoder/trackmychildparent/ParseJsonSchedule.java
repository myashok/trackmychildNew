package com.example.kanchicoder.trackmychildparent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by FamilyAngel on 11/8/2016.
 */

public class ParseJsonSchedule {
    public static String[] busIds;
    public static String[] busStopNames;
    public static String[] arrivalTimes;
    public static String[] departureTimes;

    public static final String BUS_ID_KEY = "bus_id";
    public static final String BUS_STOP_ID_KEY = "bus_stop_id";
    public static final String ARRIVAL_TIME_KEY = "arrival_time";
    public static final String DEPARTURE_TIME_KEY = "departure_time";

    private JSONArray array = null;

    private String json;

    public ParseJsonSchedule(String json){
        this.json = json;
    }

    protected void parseJSON(){
        try {
            array = new JSONArray(json);

            busIds = new String[array.length()];
            busStopNames = new String[array.length()];
            arrivalTimes = new String[array.length()];
            departureTimes = new String[array.length()];

            for(int i=0;i<array.length();i++){
                JSONObject jo = array.getJSONObject(i);
                busIds[i] = jo.getString(BUS_ID_KEY);
                busStopNames[i] = jo.getString(BUS_STOP_ID_KEY);
                arrivalTimes[i] = jo.getString(ARRIVAL_TIME_KEY);
                departureTimes[i] = jo.getString(DEPARTURE_TIME_KEY);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}