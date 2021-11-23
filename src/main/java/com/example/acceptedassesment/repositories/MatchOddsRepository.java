package com.example.acceptedassesment.repositories;

import com.example.acceptedassesment.entities.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchOddsRepository extends JpaRepository<MatchOdds,Integer> {
}
