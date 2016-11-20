package com.example.kanchicoder.trackmychildparent;

/**
 * Created by FamilyAngel on 11/20/2016.
 */

public class ExpectedSchedule {
    private String busId, busStopId, arrivalTime, departureTime;


    public ExpectedSchedule(String busId, String busStopId, String arrivalTime, String departureTime) {
        this.busId=busId;
        this.busStopId=busStopId;
        this.arrivalTime=arrivalTime;
        this.departureTime=departureTime;
    }

    public String getBusId(){
        return busId;
    }
    public String getBusStopId(){
        return busStopId;
    }
    public String getArrivalTime(){
        return arrivalTime;
    }
    public String getDepartureTime(){
        return departureTime;
    }
}
