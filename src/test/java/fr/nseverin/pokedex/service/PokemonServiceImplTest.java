package fr.nseverin.pokedex.service;

import fr.nseverin.pokedex.dto.Pokemon;
import fr.nseverin.pokedex.repository.Language;
import fr.nseverin.pokedex.repository.PokemonRepository;
import fr.nseverin.pokedex.repository.TranslatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
final class PokemonServiceImplTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @Mock
    private TranslatorRepository translatorRepository;

    private PokemonServiceImpl pokemonService;

    @BeforeEach
    void setUp() {
        pokemonService = new PokemonServiceImpl(pokemonRepository, translatorRepository);
    }

    @Test
    void fetchPokemonPassesThroughResponseFromPokemonRepository() {
        final var pokemon = new Pokemon(
                "Pokemon name",
                "Description.",
                "Habitat description",
                true
        );
        when(pokemonRepository.fetchPokemon("mewtwo")).thenReturn(pokemon);

        assertThat(pokemonService.fetchPokemon("mewtwo")).isEqualTo(pokemon);
    }

    @Test
    void fetchTranslatedPokemonReturnsYodaForLegendaryPokemon() {
        final var pokemon = new Pokemon(
                "Pokemon name",
                "Original description.",
                "Certainly not a cave",
                true
        );
        when(pokemonRepository.fetchPokemon("mewtwo")).thenReturn(pokemon);
        when(translatorRepository.translate("Original description.", Language.YODA)).thenReturn("Yoda translated description.");

        assertThat(pokemonService.fetchTranslatedPokemon("mewtwo")).isEqualTo(new Pokemon(
                "Pokemon name",
                "Yoda translated description.",
                "Certainly not a cave",
                true
        ));
    }

    @Test
    void fetchTranslatedPokemonReturnsYodaForCavePokemon() {
        final var pokemon = new Pokemon(
                "Pokemon name",
                "Original description.",
                "cave",
                false
        );
        when(pokemonRepository.fetchPokemon("zubat")).thenReturn(pokemon);
        when(translatorRepository.translate("Original description.", Language.YODA)).thenReturn("Yoda translated description.");

        assertThat(pokemonService.fetchTranslatedPokemon("zubat")).isEqualTo(new Pokemon(
                "Pokemon name",
                "Yoda translated description.",
                "cave",
                false
        ));
    }

    @Test
    void fetchTranslatedPokemonReturnsShakespeareDescriptionForNonCaveNonLegendaryPokemon() {
        final var pokemon = new Pokemon(
                "Pokemon name",
                "Original description.",
                "Certainly not a cave",
                false
        );
        when(pokemonRepository.fetchPokemon("pikachu")).thenReturn(pokemon);
        when(translatorRepository.translate("Original description.", Language.SHAKESPEARE)).thenReturn("Shakespeare translated description.");

        assertThat(pokemonService.fetchTranslatedPokemon("pikachu")).isEqualTo(new Pokemon(
                "Pokemon name",
                "Shakespeare translated description.",
                "Certainly not a cave",
                false
        ));
    }
}
