package fr.nseverin.pokedex.respository;

import fr.nseverin.pokedex.model.Pokemon;
import fr.nseverin.pokedex.repository.pokeapi.client.PokeapiClient;
import fr.nseverin.pokedex.repository.pokeapi.PokemonRepositoryPokeapiAdaptor;
import fr.nseverin.pokedex.repository.pokeapi.mapper.PokemonSpeciesToPokemonMapper;
import fr.nseverin.pokedex.repository.pokeapi.model.PokemonSpecies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
final class PokemonRepositoryPokeapiAdaptorTest {

    @Mock
    private PokeapiClient pokeapiClient;

    @Mock
    private PokemonSpeciesToPokemonMapper pokemonSpeciesToPokemonMapper;

    private PokemonRepositoryPokeapiAdaptor pokemonRepository;

    @BeforeEach
    void setUp() {
        pokemonRepository = new PokemonRepositoryPokeapiAdaptor(pokeapiClient, pokemonSpeciesToPokemonMapper);
    }

    @Test
    void fetchPokemonUsesClient() {
        final var pokemonSpecies = new PokemonSpecies(
                List.of(new PokemonSpecies.FlavorText("Description.")),
                new PokemonSpecies.Habitat("Habitat description"),
                "Pokemon name",
                true
        );
        when(pokeapiClient.fetchPokemon("mewtwo")).thenReturn(pokemonSpecies);
        final var pokemon = new Pokemon(
                "Pokemon name",
                "Description.",
                "Habitat description",
                true
        );
        when(pokemonSpeciesToPokemonMapper.map(pokemonSpecies)).thenReturn(pokemon);

        assertThat(pokemonRepository.fetchPokemon("mewtwo")).isEqualTo(pokemon);
    }

}
