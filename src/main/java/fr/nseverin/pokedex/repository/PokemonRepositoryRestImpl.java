package fr.nseverin.pokedex.repository;

import fr.nseverin.pokedex.dto.Pokemon;

public class PokemonRepositoryRestImpl implements PokemonRepository {

    @Override
    public Pokemon fetchPokemon(String pokemonName) {
        return new Pokemon(
                pokemonName,
                "It was created by a scientist after years of horrific gene splicing and DNA engineering experiments.",
                "rare",
                true
        );
    }

}
