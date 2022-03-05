package fr.nseverin.pokedex.repository.mapper;

import fr.nseverin.pokedex.model.Pokemon;
import fr.nseverin.pokedex.repository.model.pokeapi.PokemonSpecies;
import org.springframework.stereotype.Component;

@Component
public final class PokemonSpeciesToPokemonMapperImpl implements PokemonSpeciesToPokemonMapper {

    @Override
    public Pokemon map(final PokemonSpecies pokemonSpecies) {
        return new Pokemon(
                pokemonSpecies.name(),
                pokemonSpecies.flavorTextEntries().stream().findFirst()
                        .map(PokemonSpecies.FlavorText::flavorText)
                        // The test contains all sorts of unwanted blank characters, replace them with normal spaces.
                        .map(text -> text.replaceAll("\\s", " "))
                        .orElseThrow(),
                pokemonSpecies.habitat().name(),
                pokemonSpecies.isLegendary()
        );
    }

}
