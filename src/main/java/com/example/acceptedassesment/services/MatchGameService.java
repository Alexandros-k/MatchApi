package com.example.acceptedassesment.services;

import com.example.acceptedassesment.controllers.AddResponse;
import com.example.acceptedassesment.entities.MatchGame;
import com.example.acceptedassesment.repositories.MatchGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MatchGameService {

    @Autowired
    MatchGameRepository matchGameRepository;

    public ResponseEntity saveMatch(MatchGame match){
        AddResponse ar = new AddResponse();
        if(!checkMatchAlreadyExist(match.getId())) {
            matchGameRepository.save(match);
            ar.setMsg("Success Match is Added");
            ar.setId(match.getId());
            return new ResponseEntity(ar, HttpStatus.CREATED);
        }else{
            ar.setMsg("Match Already Exists");
            ar.setId(match.getId());
            return new ResponseEntity(ar, HttpStatus.ACCEPTED);
        }
    }

public boolean checkMatchAlreadyExist(int id){
    Optional<MatchGame> match = matchGameRepository.findById(id);
    if(match.isPresent()){
        return true;
    } else return false;
    }

    public MatchGame findMatchById(int id) {
        MatchGame match = null;
        try {
            match = matchGameRepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return match;
    }

    public ResponseEntity updateMatchGame(MatchGame newMatch, int id) {
        try {
            matchGameRepository.findById(id)
                    .map(match -> {
                        match.setId(newMatch.getId());
                        match.setDescription(newMatch.getDescription());
                        match.setMatch_date(newMatch.getMatch_date());
                        match.setMatch_time(newMatch.getMatch_time());
                        match.setTeam_a(newMatch.getTeam_a());
                        match.setTeam_b(newMatch.getTeam_b());
                        match.setSport(newMatch.getSport());
                         matchGameRepository.save(match);
                         return new ResponseEntity<MatchGame>(match, HttpStatus.OK);
                    })
                    .orElseGet(() -> {
                        newMatch.setId(id);
                        matchGameRepository.save(newMatch);
                        return new ResponseEntity<MatchGame>(newMatch, HttpStatus.OK);
                    });
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<MatchGame>(newMatch, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity removeMatchGame(int id) {
        MatchGame mg = null;
        try {
            mg = matchGameRepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        matchGameRepository.delete(mg);
        return new ResponseEntity<String>("MatchGame is deleted", HttpStatus.CREATED);
    }

    public List<MatchGame> getAllMatchGames() {
        return matchGameRepository.findAll();
    }
}
