package com.check_in_system.demo.service;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.check_in_system.demo.entities.Tables.Baggage;
import com.check_in_system.demo.entities.Tables.Flights;
import com.check_in_system.demo.entities.Tables.Travelers;
import com.check_in_system.demo.errors.NotFoundException;
import com.check_in_system.demo.repository.Baggage_rep;
import com.check_in_system.demo.repository.Flights_rep;
import com.check_in_system.demo.repository.Travelers_rep;


@Service
public class check_in_ser {
    
    @Autowired
    private Flights_rep repF ;
    @Autowired
    private Travelers_rep repT ;
    @Autowired
    private Baggage_rep repB ;


    public Flights add_flights(Flights flight) {
        return repF.save(flight);
    }
    
    public Travelers add_travelers(Travelers traveler) {
        return repT.save(traveler);
    }

    public Baggage add_baggage(Baggage baggage) {
        return repB.save(baggage);
    }
//////////////////////////////
    public Flights get_flights(String id) throws Exception {
        if(repF.findById(id).isPresent()) return repF.findById(id).get();
        else  throw new Exception("not found");
    }

    public Travelers get_travelers(String id) throws Exception {
        if(repT.findById(id).isPresent()) return repT.findById(id).get();
        else throw new Exception("not found");
    }
    

    public Baggage get_baggage(String id) throws Exception {
        if(repB.findById(id).isPresent()) return repB.findById(id).get();
        else throw new Exception("not found ");
    }

    public Travelers update_travelers(String id, Travelers traveler) throws Exception {
        if(!(repT.findById(id).isPresent())) throw new Exception("not found");
        Travelers old_trav = repT.findById(id).get();
        old_trav.setFirstName(traveler.getFirstName());
        old_trav.setLastName(traveler.getLastName());
        old_trav.setNationality(traveler.getNationality());
        System.out.println(traveler.getFirstName());
        return repT.save(old_trav);
    }

    public Flights update_flights(String id, Flights flight) throws Exception {
        if(!(repF.findById(id).isPresent())) throw new Exception("not found");
        Flights old_flight  = repF.findById(id).get();
        
        old_flight.setDeparture(flight.getDeparture());
        old_flight.setArrival(flight.getArrival());
        return repF.save(old_flight);


    }

    public Baggage update_baggage(String id , Baggage baggage) throws Exception {
        if(!(repB.findById(id).isPresent())) throw new Exception("not found");
        Baggage old_baggage = repB.findById(id).get();
        old_baggage.setConnected_bags(baggage.getConnected_bags());
        old_baggage.setWeight(baggage.getWeight());
        
        return repB.save(old_baggage);
    }

    public void delete_flights(String id) throws Exception {
        if(!(repF.findById(id).isPresent())) throw new NotFoundException("not found");
         repF.deleteById(id);
    }

}
