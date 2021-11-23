package com.example.acceptedassesment.controllers;

import com.example.acceptedassesment.entities.MatchOdds;
import com.example.acceptedassesment.services.MatchOddsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MatchOddsController {

    @Autowired
    MatchOddsService matchOddsService;

    @PostMapping("/matches/{matchGame_Id}/odds")
    public ResponseEntity createMatchOdds(@RequestBody MatchOdds matchOdds, @PathVariable(value ="matchGame_Id") int matchGame_Id) {
        ResponseEntity re = matchOddsService.saveMatchOdds(matchOdds,matchGame_Id);
        return re;
    }//TODO error handling if no Match Exists for this Matchodd

    @GetMapping("/odds/{id}")
    public MatchOdds getMatchOddsById(@PathVariable(value = "id") int id) {
        MatchOdds matchOdds = matchOddsService.findMatchOddsById(id);
        return matchOdds;
    }

    @PutMapping("/matches/{match_id}/odds/{id}")
    public ResponseEntity updateMatchOdds(@RequestBody MatchOdds newOdds,@PathVariable(value ="match_id") int match_id, @PathVariable(value ="id") int id){
        ResponseEntity re = matchOddsService.updateMatchOdds(newOdds,match_id,id);
        return re;
    }

    @DeleteMapping("/odds/{id}")
    public ResponseEntity deleteMatchOdds(@PathVariable int id){
        ResponseEntity re = matchOddsService.removeMatchOdd(id);
        return re;
    }
}
