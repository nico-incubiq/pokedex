package fr.nseverin.pokedex.repository.entity.pokeapi;

import java.util.List;

public record PokemonSpecies(
    List<FlavorText> flavorTextEntries,
    Habitat habitat,
    String name,
    boolean isLegendary
) {

    public record FlavorText(
        String flavorText
    ) {};

    public record Habitat(
        String name
    ) {};

};
