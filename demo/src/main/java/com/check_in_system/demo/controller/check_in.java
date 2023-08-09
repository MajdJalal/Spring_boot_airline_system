package com.check_in_system.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.check_in_system.demo.entities.Tables.Baggage;
import com.check_in_system.demo.entities.Tables.Flights;
import com.check_in_system.demo.entities.Tables.Travelers;
import com.check_in_system.demo.service.check_in_ser;


@RestController
public class check_in {
    
    @Autowired
    private check_in_ser  ser;


    @PostMapping("/Flights/save")
    public Flights add_flights(@Valid @RequestBody Flights flight) {
        return ser.add_flights(flight);
    }
    @PostMapping("/Travelers/save")
    public Travelers add_travelers(@Valid @RequestBody Travelers traveler) {
        return ser.add_travelers(traveler);
    }
    
    @PostMapping("/Baggage/save")
    public Baggage add_baggage(@Valid @RequestBody Baggage baggage) {
        return ser.add_baggage(baggage);
    }
    ////////////////
    @GetMapping("/Flights/get/{id}")
    public Flights get_flights( @PathVariable String id) throws Exception{
        return ser.get_flights(id);
    }

    @GetMapping("/Travelers/get/{id}")
    public Travelers get_travelers(@PathVariable String id) throws Exception{
        return ser.get_travelers(id);
    }

    @GetMapping("/Baggage/get/{id}")
    public Baggage get_baggage(@PathVariable String id) throws Exception{
        return ser.get_baggage(id);
    }
/////////////////////////
    @PutMapping("/Flights/update/{id}")
    public Flights update_flights(@PathVariable String id , @Valid @RequestBody Flights flight) throws Exception {
        return ser.update_flights(id, flight);
    }
    @PutMapping("/Travelers/update/{id}")
    public Travelers update_travelers(@PathVariable String id ,@Valid @RequestBody Travelers traveler) throws Exception {
        return ser.update_travelers(id, traveler);
    }
    @PutMapping("/Baggage/update/{id}")
    public Baggage update_baggage(@PathVariable String id,@Valid @RequestBody Baggage baggage)  throws Exception{
        return ser.update_baggage(id, baggage);
    }
    ///////////////
    @DeleteMapping("/Flights/delete/{id}")
    public void delete_flights( @PathVariable String id) throws Exception{
         ser.delete_flights(id);
    }



    
}