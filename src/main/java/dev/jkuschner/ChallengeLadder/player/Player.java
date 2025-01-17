package dev.jkuschner.ChallengeLadder.player;

import jakarta.validation.constraints.Positive;

public record Player(
        @Positive
        Integer id,
        String name
) {
}
