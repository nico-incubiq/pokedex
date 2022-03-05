package fr.nseverin.pokedex.repository;

import fr.nseverin.pokedex.model.Pokemon;

public interface PokemonRepository {
    Pokemon fetchPokemon(final String pokemonName);
}
