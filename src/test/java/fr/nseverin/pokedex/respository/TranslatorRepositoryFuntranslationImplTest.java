package fr.nseverin.pokedex.respository;

import fr.nseverin.pokedex.repository.Language;
import fr.nseverin.pokedex.repository.TranslatorRepositoryFuntranslationsImpl;
import fr.nseverin.pokedex.repository.entity.funtranslations.FuntranslationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TranslatorRepositoryFuntranslationImplTest {

    @Mock
    private RestTemplate restTemplate;

    private TranslatorRepositoryFuntranslationsImpl translatorRepository;

    @BeforeEach
    void setUp() {
        translatorRepository = new TranslatorRepositoryFuntranslationsImpl(restTemplate, "base-url");
    }

    @Test
    void translateToShakespeareUsesRestTemplate() {
        when(restTemplate.getForObject("base-url/shakespeare.json", FuntranslationResult.class, Map.of("text", "Can you repeat the question?")))
                .thenReturn(new FuntranslationResult(new FuntranslationResult.Content("To be, or not to be, that is the question.")));

        assertThat(translatorRepository.translate("Can you repeat the question?", Language.SHAKESPEARE))
                .isEqualTo("To be, or not to be, that is the question.");
    }

    @Test
    void translateToYodaUsesRestTemplate() {
        when(restTemplate.getForObject("base-url/yoda.json", FuntranslationResult.class, Map.of("text", "I'll give it a try")))
                .thenReturn(new FuntranslationResult(new FuntranslationResult.Content("No. Try not. Do or do not. There is no try.")));

        assertThat(translatorRepository.translate("I'll give it a try", Language.YODA))
                .isEqualTo("No. Try not. Do or do not. There is no try.");
    }

}
