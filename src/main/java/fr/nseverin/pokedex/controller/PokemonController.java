package fr.nseverin.pokedex.controller;

import fr.nseverin.pokedex.model.Error;
import fr.nseverin.pokedex.model.Pokemon;
import fr.nseverin.pokedex.service.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemon")
final class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(final PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @Operation(summary = "Get the Pokemon information", tags = "Pokedex")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pokemon information",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pokemon.class))),
            @ApiResponse(responseCode = "404", description = "Pokemon not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @GetMapping("/{pokemonName}")
    Pokemon getPokemon(@Parameter(description = "name of the Pokemon to search") @PathVariable @NonNull final String pokemonName) {
        return pokemonService.fetchPokemon(pokemonName);
    }

    @Operation(summary = "Get the Pokemon information with a funny description", tags = "Pokedex")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pokemon funny information",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pokemon.class))),
            @ApiResponse(responseCode = "404", description = "Pokemon not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @GetMapping("/translated/{pokemonName}")
    Pokemon getTranslatedPokemon(@Parameter(description = "name of the Pokemon to search") @PathVariable @NonNull final String pokemonName) {
        return pokemonService.fetchTranslatedPokemon(pokemonName);
    }

}
