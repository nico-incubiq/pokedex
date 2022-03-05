package fr.nseverin.pokedex.repository.entity.funtranslations;

public record FuntranslationResult(
    Content content
) {

    public record Content (
        String translated
    ) {}

}
