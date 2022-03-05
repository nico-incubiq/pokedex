package fr.nseverin.pokedex.controller;

import fr.nseverin.pokedex.model.Pokemon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("test")
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
    void getPokemonReturnsZubat() {
        final var response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/zubat".formatted(port), Pokemon.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isEqualTo(new Pokemon(
                "zubat",
                "Forms colonies in perpetually dark places. Uses ultrasonic waves to identify and approach targets.",
                "cave",
                false
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
    void getPokemonNotFound() {
        final var response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/notfound".formatted(port), Map.class);
        assertThat(response.getStatusCode().value()).isEqualTo(404);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).containsAllEntriesOf(Map.of(
                "message", "Pokemon not found",
                "path", "/pokemon/notfound",
                "status", 404
        ));
    }

    @Test
    void getPokemonUnexpectedError() {
        final var response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/servererror".formatted(port), Map.class);
        assertThat(response.getStatusCode().value()).isEqualTo(500);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).containsAllEntriesOf(Map.of(
                "message", "Internal server error",
                "path", "/pokemon/servererror",
                "status", 500
        ));
    }

    @Test
    void getTranslatedPokemonReturnsMewtwo() {
        final var response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/translated/mewtwo".formatted(port), Pokemon.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isEqualTo(new Pokemon(
                "mewtwo",
                "Created by a scientist after years of horrific gene splicing and dna engineering experiments,  it was.",
                "rare",
                true
        ));
    }

    @Test
    void getTranslatedPokemonReturnsZubat() {
        final var response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/translated/zubat".formatted(port), Pokemon.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isEqualTo(new Pokemon(
                "zubat",
                "Forms colonies in perpetually dark places.Ultrasonic waves to identify and approach targets,  uses.",
                "cave",
                false
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
