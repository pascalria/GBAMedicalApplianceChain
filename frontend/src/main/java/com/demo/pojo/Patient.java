package com.demo.pojo;

public class Patient {
    private Integer patientId;
    private String patientName;
    private String patientIllness;
    private String patientNeed;

    public Patient(Integer patientId, String patientName, String patientIllness, String patientNeed) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientIllness = patientIllness;
        this.patientNeed = patientNeed;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", patientIllness='" + patientIllness + '\'' +
                ", patientNeed='" + patientNeed + '\'' +
                '}';
    }
}
