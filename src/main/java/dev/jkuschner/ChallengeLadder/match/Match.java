package dev.jkuschner.ChallengeLadder.match;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public record Match(
        @Positive
        Integer id,

        @Positive
        Integer player1,

        @Positive
        Integer player2,

        LocalDateTime date,
        //TODO refactor date to be named matchDate

        @NotEmpty
        String score,

        @Positive
        Integer winner
) {}
