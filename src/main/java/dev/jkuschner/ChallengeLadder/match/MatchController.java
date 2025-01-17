package dev.jkuschner.ChallengeLadder.match;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchRepository matchRepository;

    public MatchController(MatchRepository matchRepository) {
       this.matchRepository = matchRepository;
    }

    @GetMapping("/hello")
    String home() {
        return "Hello there";
    }


    //populate with CRUD methods for match data
}
