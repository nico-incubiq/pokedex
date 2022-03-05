package fr.nseverin.pokedex.repository;

import fr.nseverin.pokedex.repository.model.funtranslations.Funtranslation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public final class TranslatorRepositoryFuntranslationsAdaptor implements TranslatorRepository {

    private final FuntranslationsClient funtranslationsClient;

    @Autowired
    public TranslatorRepositoryFuntranslationsAdaptor(final FuntranslationsClient funtranslationsClient) {
        this.funtranslationsClient = funtranslationsClient;
    }

    @Override
    public String translate(final String source, final Language destinationLanguage) {
        final var translation = funtranslationsClient.translate(source, destinationLanguage.name().toLowerCase());

        return Optional.ofNullable(translation)
                .map(Funtranslation::contents)
                .map(Funtranslation.Content::translated)
                .orElse(null);
    }

}
