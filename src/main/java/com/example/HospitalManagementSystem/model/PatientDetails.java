package com.example.HospitalManagementSystem.model;

import lombok.Data;

@Data
public class PatientDetails {
    private int id ;
    private String name ;
    private String sex ;
    private String problem ;
    private String AppointedDoctorName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getAppointedDoctorName() {
        return AppointedDoctorName;
    }

    public void setAppointedDoctorName(String appointedDoctorName) {
        AppointedDoctorName = appointedDoctorName;
    }
}
