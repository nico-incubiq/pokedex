package fr.nseverin.pokedex.repository;

import fr.nseverin.pokedex.dto.Pokemon;
import fr.nseverin.pokedex.repository.entity.pokeapi.PokemonSpecies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
public final class PokemonRepositoryPokeapiImpl implements PokemonRepository {

    private final RestTemplate restTemplate;

    private final String urlPattern;

    @Autowired
    public PokemonRepositoryPokeapiImpl(final RestTemplate restTemplate,
                                        @Value("${pokedex.external-apis.pokeapi.url-pattern}") final String urlPattern) {
        this.restTemplate = restTemplate;
        this.urlPattern = urlPattern;
    }

    @Override
    public Pokemon fetchPokemon(final String pokemonName) {
        var pokemonSpecies = restTemplate.getForObject(urlPattern, PokemonSpecies.class, pokemonName);

        return new Pokemon(
                Optional.ofNullable(pokemonSpecies)
                        .map(PokemonSpecies::name)
                        .orElse(null),
                Optional.ofNullable(pokemonSpecies)
                        .map(PokemonSpecies::flavorTextEntries)
                        .flatMap(list -> list.stream().findFirst())
                        .map(PokemonSpecies.FlavorText::flavorText)
                        // The test contains all sorts of unwanted blank characters, replace them with normal spaces.
                        .map(text -> text.replaceAll("\\s", " "))
                        .orElse(null),
                Optional.ofNullable(pokemonSpecies)
                        .map(PokemonSpecies::habitat)
                        .map(PokemonSpecies.Habitat::name)
                        .orElse(null),
                Optional.ofNullable(pokemonSpecies)
                        .map(PokemonSpecies::isLegendary)
                        .orElse(false)
        );
    }

}
