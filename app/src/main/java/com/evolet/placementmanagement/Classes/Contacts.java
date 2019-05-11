package com.evolet.placementmanagement.Classes;

public class Contacts {
  public String pno,rname,dname,id;

    public Contacts() {
    }

    public Contacts(String pno, String rname, String dname,String id) {
        this.pno = pno;
        this.rname = rname;
        this.dname = dname;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
