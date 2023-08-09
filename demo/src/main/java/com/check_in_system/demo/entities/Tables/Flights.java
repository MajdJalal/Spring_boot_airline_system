package com.check_in_system.demo.entities.Tables;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data 
public class Flights {
    @Id
    private String num;
    private String departure;
    private String arrival;
    private String terminal;
    private String booked_seats;
    private String duration;

    @OneToMany(mappedBy = "flight" , cascade = CascadeType.ALL)
    @JsonIgnoreProperties("flight") ////handling circular refrencing
    private List<Travelers> travelers;






}
