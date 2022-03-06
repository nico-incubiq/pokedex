package fr.nseverin.pokedex.model;

import java.time.LocalDateTime;

// This is only used for the OpenApi documentation of API
// errors, as Spring returns a Map rather than an object.
public record Error(
    LocalDateTime timestamp,
    int status,
    String error,
    String message,
    String path
) {}
