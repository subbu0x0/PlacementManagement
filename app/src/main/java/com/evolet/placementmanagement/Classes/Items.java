package com.evolet.placementmanagement.Classes;

public class Items {
    public Items() {
    }

    public String n,age,skills;

    public Items(String n, String age, String skills) {
        this.n = n;
        this.age = age;
        this.skills = skills;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
