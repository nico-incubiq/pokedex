package fr.nseverin.pokedex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PokemonNotFound extends ResponseStatusException {
    public PokemonNotFound() {
        super(HttpStatus.NOT_FOUND, "Pokemon not found");
    }
}
