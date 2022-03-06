package fr.nseverin.pokedex.repository.funtranslations.model;

import org.springframework.lang.NonNull;

public record Funtranslation(
    @NonNull Content contents
) {

    public record Content (
        @NonNull String translated
    ) {}

}
