package fr.nseverin.pokedex.repository.model.pokeapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PokemonSpecies(
    @JsonProperty("flavor_text_entries")
    List<FlavorText> flavorTextEntries,
    Habitat habitat,
    String name,
    @JsonProperty("is_legendary")
    boolean isLegendary
) {

    public record FlavorText(
        @JsonProperty("flavor_text")
        String flavorText
    ) {}

    public record Habitat(
        String name
    ) {}

}
