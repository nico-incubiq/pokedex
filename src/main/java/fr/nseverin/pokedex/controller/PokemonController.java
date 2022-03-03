package fr.nseverin.pokedex.controller;

import fr.nseverin.pokedex.dto.Pokemon;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemon")
final class PokemonController {

    @GetMapping("/{pokemonName}")
    Pokemon getPokemon(@PathVariable String pokemonName) {
        return new Pokemon(
                "mewtwo",
                "It was created by a scientist after years of horrific gene splicing and DNA engineering experiments.",
                "rare",
                true
        );
    }

    @GetMapping("/translated/{pokemonName}")
    Pokemon getTranslatedPokemon(@PathVariable String pokemonName) {
        return new Pokemon(
                "mewtwo",
                "It was created by a scientist after years of horrific gene splicing and dna engineering experiments, it was.",
                "rare",
                true
        );
    }

}
