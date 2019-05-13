package com.evolet.placementmanagement.Classes;

import java.io.Serializable;

public class Data implements Serializable {
    public String rid,applicantsName,age,fathersName,email,phoneNo,address,sex,highestQualification,cgpa,collegeName
            ,dept,passedOut,workExperience,softwareLanguages,applyingFor,score,startImmediately,skills,appliedFor,friends,status;

    public Data() {
    }

    public Data(String applicantsName, String age, String fathersName, String email, String phoneNo, String address, String sex, String highestQualification, String cgpa, String collegeName, String dept, String passedOut, String workExperience, String softwareLanguages, String applyingFor, String score, String startImmediately, String skills, String appliedFor, String friends,String rid,String status) {
        this.applicantsName = applicantsName;
        this.age = age;
        this.fathersName = fathersName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.sex = sex;
        this.status = status;
        this.highestQualification = highestQualification;
        this.cgpa = cgpa;
        this.collegeName = collegeName;
        this.dept = dept;
        this.passedOut = passedOut;
        this.workExperience = workExperience;
        this.softwareLanguages = softwareLanguages;
        this.applyingFor = applyingFor;
        this.score = score;
        this.startImmediately = startImmediately;
        this.skills = skills;
        this.appliedFor = appliedFor;
        this.friends = friends;
        this.rid = rid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getApplicantsName() {
        return applicantsName;
    }

    public void setApplicantsName(String applicantsName) {
        this.applicantsName = applicantsName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHighestQualification() {
        return highestQualification;
    }

    public void setHighestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPassedOut() {
        return passedOut;
    }

    public void setPassedOut(String passedOut) {
        this.passedOut = passedOut;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getSoftwareLanguages() {
        return softwareLanguages;
    }

    public void setSoftwareLanguages(String softwareLanguages) {
        this.softwareLanguages = softwareLanguages;
    }

    public String getApplyingFor() {
        return applyingFor;
    }

    public void setApplyingFor(String applyingFor) {
        this.applyingFor = applyingFor;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStartImmediately() {
        return startImmediately;
    }

    public void setStartImmediately(String startImmediately) {
        this.startImmediately = startImmediately;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getAppliedFor() {
        return appliedFor;
    }

    public void setAppliedFor(String appliedFor) {
        this.appliedFor = appliedFor;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }
}
