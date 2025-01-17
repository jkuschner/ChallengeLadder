package dev.jkuschner.ChallengeLadder.match;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MatchRepository {

    private List<Match> matches = new ArrayList<>();

    public List<Match> findAll() {
        return matches;
    }

    public Optional<Match> findById(Integer id) {
        return matches.stream()
                .filter(match -> match.id().equals(id))
                .findFirst();
    }

    public void create(Match match) {
        matches.add(match);
    }

    public void update(Match match, Integer id) {
        Optional<Match> existingMatch = findById(id);
        if(existingMatch.isPresent()) {
            matches.set(matches.indexOf(existingMatch.get()), match);
        }
    }

    public void delete(Integer id) {
        matches.removeIf(match -> match.id().equals(id));
    }
}
