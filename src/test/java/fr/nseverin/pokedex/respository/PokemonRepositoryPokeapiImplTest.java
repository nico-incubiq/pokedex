package fr.nseverin.pokedex.respository;

import fr.nseverin.pokedex.dto.Pokemon;
import fr.nseverin.pokedex.repository.PokemonRepositoryPokeapiImpl;
import fr.nseverin.pokedex.repository.entity.pokeapi.PokemonSpecies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PokemonRepositoryPokeapiImplTest {

    @Mock
    private RestTemplate restTemplate;

    private PokemonRepositoryPokeapiImpl pokemonRepository;

    @BeforeEach
    void setUp() {
        pokemonRepository = new PokemonRepositoryPokeapiImpl(restTemplate, "base-url");
    }

    @Test
    void fetchPokemonUsesRestTemplate() {
        final var pokemon = new PokemonSpecies(
                List.of(new PokemonSpecies.FlavorText("Description.")),
                new PokemonSpecies.Habitat("Habitat description"),
                "Pokemon name",
                true
        );
        when(restTemplate.getForObject("base-url/mewtwo", PokemonSpecies.class)).thenReturn(pokemon);

        assertThat(pokemonRepository.fetchPokemon("mewtwo")).isEqualTo(new Pokemon(
                "Pokemon name",
                "Description.",
                "Habitat description",
                true
        ));
    }

}