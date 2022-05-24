package com.example.springday03.controller;

import com.example.springday03.model.Park;
import com.example.springday03.model.TickitID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/park")
public class ParkController {

    private ArrayList<Park> parks = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ArrayList<Park>> getParks(){
        return ResponseEntity.status(200).body(parks);
    }

    @PostMapping
    public ResponseEntity<String> putParks(@RequestBody @Valid Park park, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        parks.add(park);
        return ResponseEntity.status(200).body("add park");
    }

    @PutMapping("/{index}")
    public ResponseEntity<String> updatePark(@PathVariable @Valid Integer index,@RequestBody @Valid Park park, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if ((int)index > parks.size()-1 || index < 0 ){
            return ResponseEntity.status(400).body("index out of range " + parks.size());
        }
        parks.set((int)index,park);
        return ResponseEntity.status(200).body("update park");
    }
    @DeleteMapping("/{index}")
    public ResponseEntity<String> deletePark(@PathVariable Integer index){
        if (index > parks.size()-1 || index < 0 ){
            return ResponseEntity.status(400).body("index out of range " + parks.size());
        }
        parks.remove((int)index);
        return ResponseEntity.status(200).body("update park");
    }
    @PutMapping("/sell/{amount}")
    public ResponseEntity<String> sellRides(@RequestBody TickitID tickitID,@PathVariable int amount){


        Park idPark = null;

        for (Park park : parks){
            if(park.getRideID().equals(tickitID.getRideID())){
                idPark = park;
            }
        }
        if (idPark.getTickets() < 0){
            return ResponseEntity.status(400).body("Tickets is finish");
        }
        if (idPark.getPrice() > amount){
            return ResponseEntity.status(400).body("Amount is not enough");
        }
        idPark.setTickets(idPark.getTickets()-1);
        return ResponseEntity.status(201).body("Number of tickets: "+idPark.getTickets());
    }
}
