package fr.nseverin.pokedex.repository.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.List;

public record PokemonSpecies(
    @JsonProperty("flavor_text_entries") @NonNull List<FlavorText> flavorTextEntries,
    @NonNull Habitat habitat,
    @NonNull String name,
    @JsonProperty("is_legendary") @NonNull boolean isLegendary
) {

    public record FlavorText(
        @JsonProperty("flavor_text") @NonNull String flavorText
    ) {}

    public record Habitat(
        @NonNull String name
    ) {}

}
