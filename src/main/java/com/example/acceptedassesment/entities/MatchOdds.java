package com.example.acceptedassesment.entities;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class MatchOdds {
    @Id
    int id;
    String specifier;
    float odd;
    @JoinColumn(name="match_game_id",nullable = false)
    int  matchGame;
}
