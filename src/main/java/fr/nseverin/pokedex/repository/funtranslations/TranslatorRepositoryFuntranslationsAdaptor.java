package fr.nseverin.pokedex.repository.funtranslations;

import fr.nseverin.pokedex.repository.Language;
import fr.nseverin.pokedex.repository.TranslatorRepository;
import fr.nseverin.pokedex.repository.funtranslations.client.FuntranslationsClient;
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
        return funtranslationsClient.translate(source, destinationLanguage.name().toLowerCase())
                .contents()
                .translated();
    }

}
