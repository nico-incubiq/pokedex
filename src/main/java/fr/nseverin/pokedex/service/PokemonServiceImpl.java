package fr.nseverin.pokedex.service;

import fr.nseverin.pokedex.dto.Pokemon;
import fr.nseverin.pokedex.repository.Language;
import fr.nseverin.pokedex.repository.PokemonRepository;
import fr.nseverin.pokedex.repository.TranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    private final TranslatorRepository translatorRepository;

    @Autowired
    public PokemonServiceImpl(final PokemonRepository pokemonRepository, TranslatorRepository translatorRepository) {
        this.pokemonRepository = pokemonRepository;
        this.translatorRepository = translatorRepository;
    }

    @Override
    public Pokemon fetchPokemon(final String pokemonName) {
        return pokemonRepository.fetchPokemon(pokemonName);
    }

    @Override
    public Pokemon fetchTranslatedPokemon(final String pokemonName) {
        var pokemon = pokemonRepository.fetchPokemon(pokemonName);

        return new Pokemon(
                pokemon.name(),
                translatorRepository.translate(pokemon.description(), selectDestinationLanguage(pokemon)),
                pokemon.habitat(),
                pokemon.isLegendary()
        );
    }

    private Language selectDestinationLanguage(final Pokemon pokemon) {
        return (pokemon.isLegendary() || "cave".equals(pokemon.habitat())) ? Language.YODA : Language.SHAKESPEARE;
    }

}
