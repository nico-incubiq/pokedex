package fr.nseverin.pokedex.repository.pokeapi.client;

import fr.nseverin.pokedex.repository.pokeapi.configuration.PokeapiClientConfiguration;
import fr.nseverin.pokedex.repository.pokeapi.model.PokemonSpecies;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "pokeapi", url = "${pokedex.external-apis.pokeapi.base-url}", configuration = PokeapiClientConfiguration.class)
public interface PokeapiClient {

    @RequestMapping(method = RequestMethod.GET, path = "/{pokemonName}", produces = MediaType.APPLICATION_JSON_VALUE)
    PokemonSpecies fetchPokemon(@PathVariable final String pokemonName);

}
