package fr.nseverin.pokedex.repository.mapper;

import fr.nseverin.pokedex.model.Pokemon;
import fr.nseverin.pokedex.repository.model.pokeapi.PokemonSpecies;

public interface PokemonSpeciesToPokemonMapper {
    Pokemon map(final PokemonSpecies pokemonSpecies);
}
