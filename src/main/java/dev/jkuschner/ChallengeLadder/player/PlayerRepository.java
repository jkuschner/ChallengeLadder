package dev.jkuschner.ChallengeLadder.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlayerRepository {
    private static final Logger log = LoggerFactory.getLogger(PlayerRepository.class);
    private final JdbcClient jdbcClient;

    public PlayerRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Player> findAll() {
        // TODO: implement
        return null;
    }

    public Optional<Player> findById(Integer id) {
        // TODO: implement
        return null;
    }

    public void create(Player player) {
        //TODO: implement
        // make sure players have unique names
        // new players start with an empty match history
    }

    public void update(Player player, Integer id) {
        // TODO: implement
        // consider overloading to handle name changes vs. match history updates
    }

    public void delete(Integer id) {
        //TODO: implement
        // deleting a player should keep them in the database along with their matches, but free up their name
    }

    public int count() {
        // TODO: implement
        return 0;
    }

    public void saveAll(List<Player> players) {
        players.stream().forEach(this::create);
    }

}
