package dev.jkuschner.ChallengeLadder.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

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
        return jdbcClient.sql("select * from players")
                .query(Player.class)
                .list();
    }

    public Optional<Player> findById(Integer id) {
        return jdbcClient.sql("SELECT id,name FROM players WHERE id = :id")
                .param("id", id)
                .query(Player.class)
                .optional();
    }

    public void create(Player player) {
        // TODO make sure players have unique names
        var updated = jdbcClient.sql("INSERT INTO players(id, name) values(?,?)")
                .params(List.of(player.id(), player.name()))
                .update();
        Assert.state(updated == 1, "Failed to create player " + player.id());
    }

    public void update(Player player, Integer id) {
        var updated = jdbcClient.sql("update players set name = ? where id = ?")
                .params(List.of(player.name(), id))
                .update();
        Assert.state(updated == 1, "Failed to update player " + player.id());
    }

    public void delete(Integer id) {
        //TODO: implement
        // deleting a player should keep them in the database along with their matches, but free up their name
    }

    public int count() {
        return jdbcClient.sql("select * from players")
                .query()
                .listOfRows()
                .size();
    }

    public void saveAll(List<Player> players) {
        players.stream().forEach(this::create);
    }

}
