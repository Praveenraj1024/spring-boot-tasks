package com.stackroute.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
Resource class, with its own properties and suitable setters, getters and constructors.
 */
@Entity
//Used to specify as resource or domain object will be mapped to database
public class Track {
    @Id
    //Used to specify the primary key of the entity.
    private int id;
    private String name;
    private String comments;

    public Track() {
    }

    public Track(int id, String name, String comments) {
        this.id = id;
        this.name = name;
        this.comments = comments;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
