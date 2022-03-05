package fr.nseverin.pokedex.repository.entity.funtranslations;

public record FuntranslationResult(
    Content contents
) {

    public record Content (
        String translated
    ) {}

}
