package fr.nseverin.pokedex.repository;

import fr.nseverin.pokedex.repository.model.pokeapi.PokemonSpecies;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "pokeapi", url = "${pokedex.external-apis.pokeapi.base-url}")
public interface PokeapiClient {

    @RequestMapping(method = RequestMethod.GET, path = "/{pokemonName}", consumes = MediaType.APPLICATION_JSON_VALUE)
    PokemonSpecies fetchPokemon(@PathVariable final String pokemonName);

}
