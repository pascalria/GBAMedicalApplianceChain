package com.demo.pojo;

public class Case {
    private int caseId;
    private int patientId;
    private String medicalNeed;
    private String createDate;


    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Case(int patientId, String medicalNeed, String createDate, int caseId) {
        this.patientId = patientId;
        this.medicalNeed = medicalNeed;
        this.createDate = createDate;
        this.caseId = caseId;
    }

    public int getCaseId() {
        return caseId;
    }

    @Override
    public String toString() {
        return "Case{" +
                "caseId=" + caseId +
                ", patientId=" + patientId +
                ", medicalNeed='" + medicalNeed + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
