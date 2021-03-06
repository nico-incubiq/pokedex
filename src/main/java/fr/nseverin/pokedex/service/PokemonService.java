package fr.nseverin.pokedex.service;

import fr.nseverin.pokedex.model.Pokemon;

public interface PokemonService {
    Pokemon fetchPokemon(final String pokemonName);

    Pokemon fetchTranslatedPokemon(final String pokemonName);
}
