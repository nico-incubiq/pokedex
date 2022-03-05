package fr.nseverin.pokedex.repository;

import fr.nseverin.pokedex.repository.entity.funtranslations.FuntranslationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Repository
public class TranslatorRepositoryFuntranslationsImpl implements TranslatorRepository {

    private final RestTemplate restTemplate;

    private final String baseUrl;

    @Autowired
    public TranslatorRepositoryFuntranslationsImpl(final RestTemplate restTemplate,
                                                   @Value("${pokedex.external-apis.funtranslations.base-url}") final String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public String translate(final String source, final Language destinationLanguage) {
        var translation = restTemplate.getForObject("%s/%s.json".formatted(baseUrl, destinationLanguage.name().toLowerCase()),
                FuntranslationResult.class, Map.of("text", source));

        return translation.content().translated();
    }

}
