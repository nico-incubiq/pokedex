package fr.nseverin.pokedex.repository.model.funtranslations;

public record Funtranslation(
    Content contents
) {

    public record Content (
        String translated
    ) {}

}
