package dev.jkuschner.ChallengeLadder.match;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MatchRepository {

    private List<Match> matches = new ArrayList<>();

    List<Match> findAll() {
        return matches;
    }
}
