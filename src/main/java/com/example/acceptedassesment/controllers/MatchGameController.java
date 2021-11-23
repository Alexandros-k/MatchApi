package com.example.acceptedassesment.controllers;

import com.example.acceptedassesment.entities.MatchGame;
import com.example.acceptedassesment.services.MatchGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MatchGameController {

    @Autowired
    MatchGameService matchGameService;

    @PostMapping("/matches")
    public ResponseEntity createMatchGame(@RequestBody MatchGame match) {
        ResponseEntity re = matchGameService.saveMatch(match);
        return re;
    }

    @GetMapping("/matches/{id}")
    public MatchGame getMatchGameById(@PathVariable(value = "id") int id) {
        MatchGame match = matchGameService.findMatchById(id);
        return match;
    }

    @GetMapping("/matches")
    public List<MatchGame> getMatchGames(){
        return matchGameService.getAllMatchGames();
    }

    @PutMapping("/matchs/{id}")
    public ResponseEntity updateMatchGame(@RequestBody MatchGame newMatch, @PathVariable(value ="id") int id){
     ResponseEntity re = matchGameService.updateMatchGame(newMatch,id);
        return re;
    }

    @DeleteMapping("/matches/{id}")
    public ResponseEntity deleteMatchGame(@PathVariable  int id){
        ResponseEntity re = matchGameService.removeMatchGame(id);
        return re;
    }

}
