package fr.nseverin.pokedex.repository.pokeapi.mapper;

import fr.nseverin.pokedex.model.Pokemon;
import fr.nseverin.pokedex.repository.pokeapi.model.PokemonSpecies;

public interface PokemonSpeciesToPokemonMapper {
    Pokemon map(final PokemonSpecies pokemonSpecies);
}
