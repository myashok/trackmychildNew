package com.example.kanchicoder.trackmychildparent;

/**
 * Created by FamilyAngel on 11/20/2016.
 */

public class PreviousLog {
    private String studentId, busId, startBusStopId, endBusStopId, startTime, endTime;


    public PreviousLog(String studentId, String busId, String startBusStopId,
                       String endBusStopId, String startTime, String endTime) {
        this.studentId=studentId;
        this.busId=busId;
        this.startBusStopId=startBusStopId;
        this.endBusStopId=endBusStopId;
        this.startTime=startTime;
        this.endTime=endTime;
    }

    public String getStudentId(){
        return studentId;
    }
    public String getBusId(){
        return busId;
    }
    public String getStartBusStopId(){
        return startBusStopId;
    }
    public String getEndBusStopId(){
        return endBusStopId;
    }
    public String getStartTime(){
        return startTime;
    }
    public String getEndTime(){
        return endTime;
    }
}
