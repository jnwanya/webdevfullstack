package com.devopsbuddy.enums;

/**
 * Created by Jnwanya on
 * Wed, 27 Dec, 2017.
 */
public enum PlanEnums {

    BASIC(1, "Basic"),
    PRO(2, "Pro");

    private final int id;
    private final String planName;

    PlanEnums(int id, String planName){
        this.id = id;
        this.planName = planName;
    }

    public int getId() {
        return id;
    }

    public String getPlanName() {
        return planName;
    }
}
