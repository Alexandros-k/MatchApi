package com.example.acceptedassesment.repositories;

import com.example.acceptedassesment.entities.MatchGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchGameRepository extends JpaRepository<MatchGame,Integer> {
}
