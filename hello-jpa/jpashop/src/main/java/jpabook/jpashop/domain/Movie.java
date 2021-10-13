package jpabook.jpashop.domain;

import jdk.jfr.Enabled;

import javax.persistence.Entity;

@Entity
public class Movie extends Item {
    private String director;
    public String actor;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
