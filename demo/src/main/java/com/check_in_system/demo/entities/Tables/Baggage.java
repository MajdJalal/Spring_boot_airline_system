package com.check_in_system.demo.entities.Tables;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Entity 
@Data 
public class Baggage {
    @Id
    private String bag_num;
    private double weight;
    private int connected_bags;


    @ManyToOne
    @JoinColumn(
        name = "travler_id",
        referencedColumnName ="id"
    )
    @JsonIgnoreProperties("baggage")
    private Travelers traveler;


     





}
