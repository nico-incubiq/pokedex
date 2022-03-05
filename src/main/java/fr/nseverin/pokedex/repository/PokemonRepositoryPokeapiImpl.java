package fr.nseverin.pokedex.repository;

import fr.nseverin.pokedex.dto.Pokemon;
import fr.nseverin.pokedex.repository.entity.pokeapi.PokemonSpecies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
public class PokemonRepositoryPokeapiImpl implements PokemonRepository {

    private final RestTemplate restTemplate;

    private final String baseUrl;

    @Autowired
    public PokemonRepositoryPokeapiImpl(final RestTemplate restTemplate,
                                        @Value("${pokedex.external-apis.pokeapi.base-url}") final String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public Pokemon fetchPokemon(final String pokemonName) {
        var pokemonSpecies = restTemplate.getForObject("%s/%s".formatted(baseUrl, pokemonName), PokemonSpecies.class);

        return new Pokemon(
                pokemonSpecies.name(),
                Optional.ofNullable(pokemonSpecies.flavorTextEntries())
                        .flatMap(list -> list.stream().findFirst())
                        .map(PokemonSpecies.FlavorText::flavorText)
                        // The test contains all sorts of unwanted blank characters, replace them with normal spaces.
                        .map(text -> text.replaceAll("\\s", " "))
                        .orElse(null),
                Optional.ofNullable(pokemonSpecies.habitat())
                        .map(PokemonSpecies.Habitat::name)
                        .orElse(null),
                pokemonSpecies.isLegendary()
        );
    }

}
