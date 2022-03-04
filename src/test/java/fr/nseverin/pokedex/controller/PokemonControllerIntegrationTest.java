package fr.nseverin.pokedex.controller;

import fr.nseverin.pokedex.dto.Pokemon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
final class PokemonControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private Integer port;

    @Test
    void getPokemonReturnsMewtwo() {
        final var response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/mewtwo".formatted(port), Pokemon.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isEqualTo(new Pokemon(
                "mewtwo",
                "It was created by a scientist after years of horrific gene splicing and DNA engineering experiments.",
                "rare",
                true
        ));
    }

    @Test
    void getPokemonReturnsPikachu() {
        final var response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/pikachu".formatted(port), Pokemon.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isEqualTo(new Pokemon(
                "pikachu",
                "When several of these POKéMON gather, their electricity could build and cause lightning storms.",
                "forest",
                false
        ));
    }

    @Test
    void getTranslatedPokemonReturnsMewtwo() {
        final var response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/translated/mewtwo".formatted(port), Pokemon.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isEqualTo(new Pokemon(
                "mewtwo",
                "It was created by a scientist after years of horrific gene splicing and dna engineering experiments, it was.",
                "rare",
                true
        ));
    }

    @Test
    void getTranslatedPokemonReturnsPikachu() {
        final var response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/translated/pikachu".formatted(port), Pokemon.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isEqualTo(new Pokemon(
                "pikachu",
                "At which hour several of these pokémon gather,  their electricity couldst buildeth and cause lightning storms.",
                "forest",
                false
        ));
    }

}
