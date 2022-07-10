package com.huang.mphealth.entity;

public class temTableModel extends DoctorPatientEntity{

    private String patientName;

    private String doctorName;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }

    public String getConditionHash() {
        return conditionHash;
    }

    public void setConditionHash(String conditionHash) {
        this.conditionHash = conditionHash;
    }

    /**
     * 医治方案
     */
    private String condition;
    /**
     * 价格
     */
    private String conditionValue;
    /**
     * 确认病症上链哈希
     */
    private String conditionHash;






}
