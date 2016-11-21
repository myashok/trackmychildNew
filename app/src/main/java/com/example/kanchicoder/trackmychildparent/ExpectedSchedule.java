package com.example.kanchicoder.trackmychildparent;

/**
 * Created by FamilyAngel on 11/20/2016.
 */

public class ExpectedSchedule {
    private String busId, busStopName, arrivalTime, departureTime;


    public ExpectedSchedule(String busId, String busStopName, String arrivalTime, String departureTime) {
        this.busId=busId;
        this.busStopName=busStopName;
        this.arrivalTime=arrivalTime;
        this.departureTime=departureTime;
    }

    public String getBusId(){
        return busId;
    }
    public String getBusStopName(){
        return busStopName;
    }
    public String getArrivalTime(){
        return arrivalTime;
    }
    public String getDepartureTime(){
        return departureTime;
    }
}
