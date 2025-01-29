package dev.jkuschner.ChallengeLadder;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jkuschner.ChallengeLadder.match.MatchRepository;
import dev.jkuschner.ChallengeLadder.match.Matches;
import dev.jkuschner.ChallengeLadder.player.PlayerRepository;
import dev.jkuschner.ChallengeLadder.player.Players;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class JsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(JsonDataLoader.class);
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final ObjectMapper objectMapper;

    public JsonDataLoader(MatchRepository matchRepository, PlayerRepository playerRepository, ObjectMapper objectMapper) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (playerRepository.count() == 0) {
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/players.json")) {
                Players allPlayers = objectMapper.readValue(inputStream, Players.class);
                log.info("Reading {} players from JSON data and saving to database.", allPlayers.players().size());
                playerRepository.saveAll(allPlayers.players());
                log.info("Saving all {} players to playerRepository.", allPlayers.players().size());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read player JSON data", e);
            }
        } else {
            log.info("Not loading players from JSON data because the database contains data.");
        }

        if (matchRepository.count() == 0) {
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/matches.json")) {
                Matches allMatches = objectMapper.readValue(inputStream, Matches.class);
                log.info("Reading {} matches from JSON data and saving to database.", allMatches.matches().size());
                matchRepository.saveAll(allMatches.matches());
                log.info("Saving all {} matches to matchRepository.", allMatches.matches().size());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read match JSON data", e);
            }
        } else {
            log.info("Not loading matches from JSON data because the database contains data.");
        }
    }
}
