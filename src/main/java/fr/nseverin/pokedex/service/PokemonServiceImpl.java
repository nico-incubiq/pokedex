package fr.nseverin.pokedex.service;

import fr.nseverin.pokedex.dto.Pokemon;
import org.springframework.stereotype.Service;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Override
    public Pokemon fetchPokemon(String pokemonName) {
        return new Pokemon(
                pokemonName,
                "It was created by a scientist after years of horrific gene splicing and DNA engineering experiments.",
                "rare",
                true
        );
    }

    @Override
    public Pokemon fetchTranslatedPokemon(String pokemonName) {
        return new Pokemon(
                pokemonName,
                "It was created by a scientist after years of horrific gene splicing and dna engineering experiments, it was.",
                "rare",
                true
        );
    }

}
