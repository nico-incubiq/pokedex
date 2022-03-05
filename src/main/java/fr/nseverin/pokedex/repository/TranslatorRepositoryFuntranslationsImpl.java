package fr.nseverin.pokedex.repository;

import fr.nseverin.pokedex.repository.model.funtranslations.FuntranslationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
public final class TranslatorRepositoryFuntranslationsImpl implements TranslatorRepository {

    private final RestTemplate restTemplate;

    private final String urlPattern;

    @Autowired
    public TranslatorRepositoryFuntranslationsImpl(final RestTemplate restTemplate,
                                                   @Value("${pokedex.external-apis.funtranslations.url-pattern}") final String urlPattern) {
        this.restTemplate = restTemplate;
        this.urlPattern = urlPattern;
    }

    @Override
    public String translate(final String source, final Language destinationLanguage) {
        final var translation = restTemplate.getForObject(urlPattern, FuntranslationResult.class,
                destinationLanguage.name().toLowerCase(), source);

        return Optional.ofNullable(translation)
                .map(FuntranslationResult::contents)
                .map(FuntranslationResult.Content::translated)
                .orElse(null);
    }

}
