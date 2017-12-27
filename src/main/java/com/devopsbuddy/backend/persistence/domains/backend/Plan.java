package com.devopsbuddy.backend.persistence.domains.backend;

import com.devopsbuddy.enums.PlanEnums;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Jnwanya on
 * Wed, 27 Dec, 2017.
 */
@Entity
public class Plan implements Serializable {
    /** The serialversionUID for a serializabled class **/
    private static final long serialVersionUID = 1L;
    @Id
    private int id;

    private String name;

    /** Default constructor **/
    public Plan(){ }

    public Plan(PlanEnums planEnums){
        this.id = planEnums.getId();
        this.name = planEnums.getPlanName();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plan plan = (Plan) o;

        if (id != plan.id) return false;
        return name.equals(plan.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
