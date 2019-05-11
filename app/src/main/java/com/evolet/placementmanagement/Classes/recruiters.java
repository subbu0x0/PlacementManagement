package com.evolet.placementmanagement.Classes;

public class recruiters {
    String name,email,pno,doj,type;

    public recruiters() {
    }

    public recruiters(String name, String email, String pno, String doj, String type) {
        this.name = name;
        this.email = email;
        this.pno = pno;
        this.doj = doj;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
