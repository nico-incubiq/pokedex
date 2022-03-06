package fr.nseverin.pokedex.repository.pokeapi.mapper;

import fr.nseverin.pokedex.model.Pokemon;
import fr.nseverin.pokedex.repository.pokeapi.model.PokemonSpecies;
import org.springframework.stereotype.Component;

@Component
public final class PokemonSpeciesToPokemonMapperImpl implements PokemonSpeciesToPokemonMapper {

    @Override
    public Pokemon map(final PokemonSpecies pokemonSpecies) {
        return new Pokemon(
                pokemonSpecies.name(),
                pokemonSpecies.flavorTextEntries().stream().findFirst()
                        .map(PokemonSpecies.FlavorText::flavorText)
                        // The description text contains all sorts of blank characters like \n \f etc.,
                        // replace them all with a single normal space.
                        .map(text -> text.replaceAll("\\s+", " "))
                        .orElseThrow(),
                pokemonSpecies.habitat().name(),
                pokemonSpecies.isLegendary()
        );
    }

}
