package fr.nseverin.pokedex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public final class InternalServerError extends ResponseStatusException {
    public InternalServerError() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }
}
