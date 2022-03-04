package fr.nseverin.pokedex.controller;

import fr.nseverin.pokedex.dto.Pokemon;
import fr.nseverin.pokedex.service.PokemonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PokemonController.class)
final class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokemonService pokemonService;

    @Test
    void getPokemonReturnsMewtwo() throws Exception {
        when(pokemonService.fetchPokemon("mewtwo")).thenReturn(new Pokemon(
                "Pokemon name",
                "Description.",
                "Habitat description",
                true
        ));

        mockMvc.perform(get("/pokemon/mewtwo")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "name": "Pokemon name",
                            "description": "Description.",
                            "habitat": "Habitat description",
                            "isLegendary": true
                        }
                        """, true));
    }

    @Test
    void getTranslatedPokemonReturnsMewtwo() throws Exception {
        when(pokemonService.fetchTranslatedPokemon("mewtwo")).thenReturn(new Pokemon(
                "Pokemon name",
                "Translated description.",
                "Habitat description",
                true
        ));

        mockMvc.perform(get("/pokemon/translated/mewtwo")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "name": "Pokemon name",
                            "description": "Translated description.",
                            "habitat": "Habitat description",
                            "isLegendary": true
                        }
                        """, true));
    }

}
