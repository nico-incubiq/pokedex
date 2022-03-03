package fr.nseverin.pokedex.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PokemonController.class)
public class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getPokemonReturnsMewtwo() throws Exception {
        mockMvc.perform(get("/pokemon/mewtwo")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "name": "mewtwo",
                            "description": "It was created by a scientist after years of horrific gene splicing and DNA engineering experiments.",
                            "habitat": "rare",
                            "isLegendary": true
                        }
                        """, true));
    }

}
