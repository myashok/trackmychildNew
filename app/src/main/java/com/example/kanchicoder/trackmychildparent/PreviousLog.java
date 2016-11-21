package com.example.kanchicoder.trackmychildparent;

/**
 * Created by FamilyAngel on 11/20/2016.
 */

public class PreviousLog {
    private String busId, startBusStopName, endBusStopName, startTime, endTime;


    public PreviousLog(String busId, String startBusStopName,
                       String endBusStopName, String startTime, String endTime) {
        this.busId=busId;
        this.startBusStopName=startBusStopName;
        this.endBusStopName=endBusStopName;
        this.startTime=startTime;
        this.endTime=endTime;
    }

    public String getBusId(){
        return busId;
    }
    public String getStartBusStopName(){
        return startBusStopName;
    }
    public String getEndBusStopName(){
        return endBusStopName;
    }
    public String getStartTime(){
        return startTime;
    }
    public String getEndTime(){
        return endTime;
    }
}
