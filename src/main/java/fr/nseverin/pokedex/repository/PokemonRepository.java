package fr.nseverin.pokedex.repository;

import fr.nseverin.pokedex.dto.Pokemon;

public interface PokemonRepository {
    Pokemon fetchPokemon(final String pokemonName);
}
