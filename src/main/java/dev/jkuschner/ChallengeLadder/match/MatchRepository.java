package dev.jkuschner.ChallengeLadder.match;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class MatchRepository {

    private static final Logger log = LoggerFactory.getLogger(MatchRepository.class);
    private final JdbcClient jdbcClient;

    public MatchRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Match> findAll() {
        return jdbcClient.sql("select * from matches")
                .query(Match.class)
                .list();
    }

    public Optional<Match> findById(Integer id) {
        return jdbcClient.sql("SELECT id,player1,player2,match_date,score,winner FROM matches WHERE id = :id")
                .param("id", id)
                .query(Match.class)
                .optional();
    }

    public void create(Match match) {
        var updated = jdbcClient.sql("INSERT INTO matches(id,player1,player2,match_date,score,winner) values(?,?,?,?,?,?)")
                .params(List.of(match.id(), match.player1(), match.player2(), match.matchDate(), match.score(), match.winner()))
                .update();

        Assert.state(updated == 1, "Failed to create match " + match.id());
    }

    public void update(Match match, Integer id) {
        // TODO: make sure match changes also propagate to players' match history
        var updated = jdbcClient.sql("update matches set player1 = ?, player2 = ?, match_date = ?, score = ?, winner = ? where id = ?")
                .params(List.of(match.player1(), match.player2(), match.matchDate(), match.score(), match.winner(), id))
                .update();

        Assert.state(updated == 1, "Failed to update match " + match.id());
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("delete from matches where id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete match " + id);
    }

    public int count() {
        return jdbcClient.sql("select * from matches")
                .query()
                .listOfRows()
                .size();
    }

    public void saveAll(List<Match> matches) {
        matches.stream().forEach(this::create);
    }
    /*
    *** IN-MEMORY List REPRESENTATION(no database) ***

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

     */
}
