package dev.jkuschner.ChallengeLadder.match;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class MatchJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MatchJsonDataLoader.class);
    private final MatchRepository matchRepository;
    private final ObjectMapper objectMapper;

    public MatchJsonDataLoader(MatchRepository matchRepository, ObjectMapper objectMapper) {
        this.matchRepository = matchRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (matchRepository.count() == 0) {
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/matches.json")) {
                Matches allMatches = objectMapper.readValue(inputStream, Matches.class);
                log.info("Reading {} matches from JSON data and saving to database.", allMatches.matches().size());
                matchRepository.saveAll(allMatches.matches());
                log.info("Saving all {} matches to matchRepository.", allMatches.matches().size());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            log.info("Not loading matches from JSON data because the databse contains data.");
        }
    }
}
