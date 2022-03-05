package fr.nseverin.pokedex.model;

import java.time.LocalDateTime;

public record Error(
    LocalDateTime timestamp,
    int status,
    String error,
    String message,
    String path
) {}
