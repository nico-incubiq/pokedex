package fr.nseverin.pokedex.controller;

import fr.nseverin.pokedex.dto.Pokemon;
import fr.nseverin.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemon")
final class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(final PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/{pokemonName}")
    Pokemon getPokemon(@PathVariable String pokemonName) {
        return pokemonService.fetchPokemon(pokemonName);
    }

    @GetMapping("/translated/{pokemonName}")
    Pokemon getTranslatedPokemon(@PathVariable String pokemonName) {
        return pokemonService.fetchTranslatedPokemon(pokemonName);
    }

}
