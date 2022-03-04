package fr.nseverin.pokedex.repository;

import fr.nseverin.pokedex.dto.Pokemon;
import fr.nseverin.pokedex.repository.entity.pokeapi.PokemonSpecies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class PokemonRepositoryRestImpl implements PokemonRepository {

    private final RestTemplate restTemplate;

    @Autowired
    public PokemonRepositoryRestImpl(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Pokemon fetchPokemon(String pokemonName) {
        var pokemonSpecies = restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon-species/mewtwo", PokemonSpecies.class);

        return new Pokemon(
                pokemonSpecies.name(),
                pokemonSpecies.flavorTextEntries().stream().findFirst().map(PokemonSpecies.FlavorText::flavorText).orElse(null),
                pokemonSpecies.habitat().name(),
                pokemonSpecies.isLegendary()
        );
    }

}
