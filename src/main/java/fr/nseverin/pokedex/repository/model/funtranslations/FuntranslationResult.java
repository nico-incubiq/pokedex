package fr.nseverin.pokedex.repository.model.funtranslations;

public record FuntranslationResult(
    Content contents
) {

    public record Content (
        String translated
    ) {}

}
