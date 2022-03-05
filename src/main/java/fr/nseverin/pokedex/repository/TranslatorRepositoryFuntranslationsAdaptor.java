package fr.nseverin.pokedex.repository;

import fr.nseverin.pokedex.exception.InternalServerError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public final class TranslatorRepositoryFuntranslationsAdaptor implements TranslatorRepository {

    private final FuntranslationsClient funtranslationsClient;

    @Autowired
    public TranslatorRepositoryFuntranslationsAdaptor(final FuntranslationsClient funtranslationsClient) {
        this.funtranslationsClient = funtranslationsClient;
    }

    @Override
    public String translate(final String source, final Language destinationLanguage) {
        try {
            return funtranslationsClient.translate(source, destinationLanguage.name().toLowerCase())
                    .contents()
                    .translated();
        } catch (InternalServerError e) {
            return source;
        }
    }

}
