package fr.nseverin.pokedex.repository;

public interface TranslatorRepository {
    String translate(final String source, final Language destinationLanguage);
}
