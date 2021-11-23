package com.example.acceptedassesment.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class MatchOdds {
    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String specifier;
    float odd;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="match_game_id",nullable = false)
    private MatchGame matchGame;
}
