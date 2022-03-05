package fr.nseverin.pokedex.service;

import fr.nseverin.pokedex.model.Pokemon;
import fr.nseverin.pokedex.repository.Language;
import fr.nseverin.pokedex.repository.PokemonRepository;
import fr.nseverin.pokedex.repository.TranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class PokemonServiceImpl implements PokemonService {

    private static final String CAVE_HABITAT = "cave";

    private final PokemonRepository pokemonRepository;

    private final TranslatorRepository translatorRepository;

    @Autowired
    public PokemonServiceImpl(final PokemonRepository pokemonRepository,
                              final TranslatorRepository translatorRepository) {
        this.pokemonRepository = pokemonRepository;
        this.translatorRepository = translatorRepository;
    }

    @Override
    public Pokemon fetchPokemon(final String pokemonName) {
        return pokemonRepository.fetchPokemon(pokemonName);
    }

    @Override
    public Pokemon fetchTranslatedPokemon(final String pokemonName) {
        final var pokemon = pokemonRepository.fetchPokemon(pokemonName);

        return new Pokemon(
                pokemon.name(),
                translatorRepository.translate(pokemon.description(), selectDestinationLanguage(pokemon)),
                pokemon.habitat(),
                pokemon.isLegendary()
        );
    }

    private Language selectDestinationLanguage(final Pokemon pokemon) {
        return (pokemon.isLegendary() || CAVE_HABITAT.equals(pokemon.habitat()))
                ? Language.YODA
                : Language.SHAKESPEARE;
    }

}
