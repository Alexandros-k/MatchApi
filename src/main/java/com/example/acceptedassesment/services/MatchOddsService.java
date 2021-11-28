package com.example.acceptedassesment.services;

import com.example.acceptedassesment.controllers.AddResponse;
import com.example.acceptedassesment.entities.MatchGame;
import com.example.acceptedassesment.entities.MatchOdds;
import com.example.acceptedassesment.repositories.MatchGameRepository;
import com.example.acceptedassesment.repositories.MatchOddsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MatchOddsService {

    @Autowired
    MatchGameRepository matchGameRepository;

    @Autowired
    MatchOddsRepository matchOddsRepository;

    public ResponseEntity saveMatchOdds(MatchOdds matchOdds, int matchGameId){
        AddResponse ar = new AddResponse();
        if(!checkMatchOddAlreadyExist(matchOdds.getId())) {
            matchGameRepository.findById(matchGameId).map(matchGame -> {
                        matchOdds.setMatchGame(matchGame.getId());
                matchOddsRepository.save(matchOdds);
                ar.setMsg("Success MatchOdds are Added");
                ar.setId(matchOdds.getId());
                return new ResponseEntity(ar, HttpStatus.CREATED);
            });
            return new ResponseEntity(ar, HttpStatus.CREATED);
        }else{
            ar.setMsg("MatchOdds Already Exists");
            ar.setId(matchOdds.getId());
            return new ResponseEntity(ar, HttpStatus.ACCEPTED);
        }
    }

    public Boolean checkMatchOddAlreadyExist(int id) {
        Optional<MatchOdds> matchOdds = matchOddsRepository.findById(id);
        if(matchOdds.isPresent()){
            return true;
        } else return false;
    }

    public MatchOdds findMatchOddsById(int match_id, int id) {
        MatchOdds matchOdds = null;
        MatchGame mg = matchGameRepository.findById(match_id).get();
        for (MatchOdds matchOdd : mg.getMatchOdds()) {
            if (matchOdd.getId() == id) {
              return matchOddsRepository.findById(id).get();
            }
        }
      /*  try {
            matchOdds = matchOddsRepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }*/
        return matchOdds;
    }

    public ResponseEntity updateMatchOdds(MatchOdds newMatchOdds, int matchGame_Id, int matchOddsId) {
        try {
           Optional<MatchGame> mg = matchGameRepository.findById(matchGame_Id);
            matchOddsRepository.findById(matchOddsId)
                    .map(matchOdds -> {
                        matchOdds.setId(newMatchOdds.getId());
                        matchOdds.setSpecifier(newMatchOdds.getSpecifier());
                        matchOdds.setOdd(newMatchOdds.getOdd());
                        matchOdds.setMatchGame(mg.get().getId());
                        matchOddsRepository.save(matchOdds);
                        return new ResponseEntity<MatchOdds>(matchOdds, HttpStatus.OK);
                    })
                    .orElseGet(() -> {
                        newMatchOdds.setId(matchOddsId);
                        matchOddsRepository.save(newMatchOdds);
                        return new ResponseEntity<MatchOdds>(newMatchOdds, HttpStatus.OK);
                    });
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<MatchOdds>(newMatchOdds, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity removeMatchOdd(int matchGameId, int id) {
        MatchGame mg = matchGameRepository.findById(matchGameId).get();
        MatchOdds m1 = null;
        for (MatchOdds matchOdd : mg.getMatchOdds()) {
            if (matchOdd.getId() == id) {
                try {
                    m1 = matchOddsRepository.findById(id).get();
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
                matchOddsRepository.delete(m1);
                return new ResponseEntity<String>("MatchOdd is deleted", HttpStatus.CREATED);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

/*    @Transactional
    public ResponseEntity removeMatchOdd(int id) {
        MatchOdds mo = null;
        try {
            mo = matchOddsRepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        matchOddsRepository.delete(mo);
        return new ResponseEntity<String>("MatchOdd is deleted", HttpStatus.CREATED);
    }*/

}
