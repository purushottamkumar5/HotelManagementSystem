package com.Icwd.user.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="micro_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @Column(name="ID")
    private String userId;
    @Column(name="NAME")
    private String name;
    @Column(name="EMAIL")
    private String email;
    @Column(name="ABOUT")
    private String about;
    @Transient    //Donot store this feild in database
    private List<Rating> ratings=new ArrayList<>();

//    @Transient
//    private List<Hotel> hotel;


}
