package fr.nseverin.pokedex.service;

import fr.nseverin.pokedex.dto.Pokemon;
import fr.nseverin.pokedex.repository.PokemonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceImplTest {

    @Mock
    private PokemonRepository pokemonRepository;

    private PokemonServiceImpl pokemonService;

    @BeforeEach
    void setUp() {
        pokemonService = new PokemonServiceImpl(
                pokemonRepository
        );
    }

    @Test
    void fetchPokemonPassesThroughResponseFromPokemonApi() {
        final var pokemon = new Pokemon(
                "Pokemon name",
                "Description.",
                "Habitat description",
                true
        );
        when(pokemonRepository.fetchPokemon("mewtwo")).thenReturn(pokemon);

        assertThat(pokemonService.fetchPokemon("mewtwo")).isEqualTo(pokemon);
    }
}
