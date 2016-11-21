package com.example.kanchicoder.trackmychildparent;

import java.io.Serializable;

/**
 * Created by FamilyAngel on 11/8/2016.
 */

public class Student  implements Serializable {
    private String studentName, schoolName, photo_url, orgNo;


    public Student(String name, String school, String photo, String orgNo) {
        this.studentName = name;
        this.photo_url = photo;
        this.schoolName = school;
        this.orgNo=orgNo;
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

    public String getOrgNo(){
        return orgNo;
    }
}
