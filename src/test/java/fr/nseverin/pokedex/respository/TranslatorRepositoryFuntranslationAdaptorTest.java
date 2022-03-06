package fr.nseverin.pokedex.respository;

import fr.nseverin.pokedex.repository.Language;
import fr.nseverin.pokedex.repository.funtranslations.TranslatorRepositoryFuntranslationsAdaptor;
import fr.nseverin.pokedex.repository.funtranslations.client.FuntranslationsClient;
import fr.nseverin.pokedex.repository.funtranslations.model.Funtranslation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
final class TranslatorRepositoryFuntranslationAdaptorTest {

    @Mock
    private FuntranslationsClient funtranslationsClient;

    private TranslatorRepositoryFuntranslationsAdaptor translatorRepository;

    @BeforeEach
    void setUp() {
        translatorRepository = new TranslatorRepositoryFuntranslationsAdaptor(funtranslationsClient);
    }

    @Test
    void translateToShakespeareUsesClient() {
        when(funtranslationsClient.translate("Can you repeat the question?", "shakespeare"))
                .thenReturn(new Funtranslation(new Funtranslation.Content("To be, or not to be, that is the question.")));

        assertThat(translatorRepository.translate("Can you repeat the question?", Language.SHAKESPEARE))
                .isEqualTo("To be, or not to be, that is the question.");
    }

    @Test
    void translateToYodaUsesClient() {
        when(funtranslationsClient.translate("I'll give it a try", "yoda"))
                .thenReturn(new Funtranslation(new Funtranslation.Content("No. Try not. Do or do not. There is no try.")));

        assertThat(translatorRepository.translate("I'll give it a try", Language.YODA))
                .isEqualTo("No. Try not. Do or do not. There is no try.");
    }

}
