package com.check_in_system.demo.entities.Tables;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data 
public class Travelers {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String nationality;
    private String phoneNum;
//the column name gonna be flight_num , but the json format property passed gonna be flight 
    @ManyToOne
    @JoinColumn(
        name = "flight_num",
        referencedColumnName = "num"
    )
    @JsonIgnoreProperties("travelers") //handling circular refrencing 
    private Flights flight;//u will store a Fligh obj here in this cell

    @OneToMany(mappedBy = "traveler")//majd:writing mappedBy so i dont make any new relations(the relation is already made)
    @JsonIgnoreProperties("travelers")
    private List<Baggage> baggage;

//the join column gonna be in the Many table (majd)

    
    
    
}
