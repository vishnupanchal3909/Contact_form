package com.vishnu.contact_form;

public class UserInfoClassHelper {
    String name,address,registrtionFee,ExamFees,emailId,collegeFee,totalFees,date,gender;

    public UserInfoClassHelper() {
    }

    public UserInfoClassHelper(String name, String address, String registrtionFee, String examFees, String emailId, String collegeFee, String totalFees, String date, String gender) {
        this.name = name;
        this.address = address;
        this.registrtionFee = registrtionFee;
        ExamFees = examFees;
        this.emailId = emailId;
        this.collegeFee = collegeFee;
        this.totalFees = totalFees;
        this.date = date;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegistrtionFee() {
        return registrtionFee;
    }

    public void setRegistrtionFee(String registrtionFee) {
        this.registrtionFee = registrtionFee;
    }

    public String getExamFees() {
        return ExamFees;
    }

    public void setExamFees(String examFees) {
        ExamFees = examFees;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCollegeFee() {
        return collegeFee;
    }

    public void setCollegeFee(String collegeFee) {
        this.collegeFee = collegeFee;
    }

    public String getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(String totalFees) {
        this.totalFees = totalFees;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
