package com.stackroute.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
Resource class, with its own properties and suitable setters, getters and constructors.
 */
@Entity
//Used to specify as resource or domain object will be mapped to database

@NoArgsConstructor
//To create a constructor without any argument
@AllArgsConstructor
//To create a constructor with all arguments
@Data
//To create getters and setters for all properties, to override toString(),
// to create equals, canEquals and HashCode.
public class Track {
    @Id
    //Used to specify the primary key of the entity.
    private int id;
    private String name;
    private String comments;


}
