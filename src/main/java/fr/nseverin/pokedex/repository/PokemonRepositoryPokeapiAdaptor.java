package fr.nseverin.pokedex.repository;

import fr.nseverin.pokedex.model.Pokemon;
import fr.nseverin.pokedex.repository.mapper.PokemonSpeciesToPokemonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public final class PokemonRepositoryPokeapiAdaptor implements PokemonRepository {

    private final PokeapiClient pokeapiClient;

    private final PokemonSpeciesToPokemonMapper pokemonSpeciesToPokemonMapper;

    @Autowired
    public PokemonRepositoryPokeapiAdaptor(final PokeapiClient pokeapiClient,
                                           final PokemonSpeciesToPokemonMapper pokemonSpeciesToPokemonMapper) {
        this.pokeapiClient = pokeapiClient;
        this.pokemonSpeciesToPokemonMapper = pokemonSpeciesToPokemonMapper;
    }

    @Override
    public Pokemon fetchPokemon(final String pokemonName) {
        var pokemonSpecies = pokeapiClient.fetchPokemon(pokemonName);

        return pokemonSpeciesToPokemonMapper.map(pokemonSpecies);
    }

}
