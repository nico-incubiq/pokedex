package fr.nseverin.pokedex.repository.mapper;

import fr.nseverin.pokedex.model.Pokemon;
import fr.nseverin.pokedex.repository.model.pokeapi.PokemonSpecies;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public final class PokemonSpeciesToPokemonMapperImpl implements PokemonSpeciesToPokemonMapper {

    @Override
    public Pokemon map(final PokemonSpecies pokemonSpecies) {
        return new Pokemon(
                Optional.ofNullable(pokemonSpecies)
                        .map(PokemonSpecies::name)
                        .orElse(null),
                Optional.ofNullable(pokemonSpecies)
                        .map(PokemonSpecies::flavorTextEntries)
                        .flatMap(list -> list.stream().findFirst())
                        .map(PokemonSpecies.FlavorText::flavorText)
                        // The test contains all sorts of unwanted blank characters, replace them with normal spaces.
                        .map(text -> text.replaceAll("\\s", " "))
                        .orElse(null),
                Optional.ofNullable(pokemonSpecies)
                        .map(PokemonSpecies::habitat)
                        .map(PokemonSpecies.Habitat::name)
                        .orElse(null),
                Optional.ofNullable(pokemonSpecies)
                        .map(PokemonSpecies::isLegendary)
                        .orElse(false)
        );
    }

}
