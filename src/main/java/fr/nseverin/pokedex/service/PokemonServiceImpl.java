package fr.nseverin.pokedex.service;

import fr.nseverin.pokedex.dto.Pokemon;
import fr.nseverin.pokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(final PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public Pokemon fetchPokemon(final String pokemonName) {
        return pokemonRepository.fetchPokemon(pokemonName);
    }

    @Override
    public Pokemon fetchTranslatedPokemon(final String pokemonName) {
        return new Pokemon(
                pokemonName,
                "It was created by a scientist after years of horrific gene splicing and dna engineering experiments, it was.",
                "rare",
                true
        );
    }

}
