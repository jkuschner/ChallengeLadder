package dev.jkuschner.ChallengeLadder.player;

import jakarta.validation.constraints.Positive;
import dev.jkuschner.ChallengeLadder.match.Match;
import java.util.List;

public record Player(
        @Positive
        Integer id,
        String name,
        List<Match> matchHistory
) {
}
