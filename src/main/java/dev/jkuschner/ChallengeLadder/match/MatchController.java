package dev.jkuschner.ChallengeLadder.match;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchRepository matchRepository;

    public MatchController(MatchRepository matchRepository) {
       this.matchRepository = matchRepository;
    }

    @GetMapping("")
    List<Match> findAll() {
        return matchRepository.findAll();
    }

    @GetMapping("/{id}")
    Match findById(@PathVariable Integer id) {
        Optional<Match> match = matchRepository.findById(id);
        if (match.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return match.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Match match) {
        matchRepository.create(match);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Match match, @PathVariable Integer id) {
        matchRepository.update(match, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        matchRepository.delete(id);
    }

    @GetMapping("/hello")
    String home() {
        return "Hello there";
    }


}
