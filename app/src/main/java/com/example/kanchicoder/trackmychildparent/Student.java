package com.example.kanchicoder.trackmychildparent;

/**
 * Created by FamilyAngel on 11/8/2016.
 */

public class Student {
    private String studentName, schoolName, photo_url;


    public Student(String name, String school, String photo) {
        this.studentName = name;
        this.photo_url = photo;
        this.schoolName = school;
    }
    public String getStudentName(){
        return studentName;
    }

    public String getSchoolName(){
        return schoolName;
    }

    public String getStudentPhoto(){
        return photo_url;
    }
}
