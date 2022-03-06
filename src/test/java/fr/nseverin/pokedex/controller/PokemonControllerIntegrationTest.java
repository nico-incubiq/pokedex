package fr.nseverin.pokedex.controller;

import fr.nseverin.pokedex.model.Pokemon;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("It can request a legendary pokemon like Mewtwo")
    void getLegendaryPokemonMewtwo() {
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
    @DisplayName("It can request a cave pokemon like Mewtwo")
    void getCavePokemonZubat() {
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
    @DisplayName("It can request any other pokemon like Pikachu")
    void getRegularPokemonPikachu() {
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
    @DisplayName("When requesting a non-existing Pokemon, return \"Pokemon not found\"")
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
    @DisplayName("When pokeapi errors, return an \"Internal server error\"")
    void getPokemonUnexpectedPokeapiError() {
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
    @DisplayName("When requesting a legendary Pokemon like Mewtwo, apply the Yoda transformation to its description")
    void getTranslatedMewtwoReturnsYodaDescription() {
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
    @DisplayName("When requesting a cave Pokemon like Zubat, apply the Yoda transformation to its description")
    void getTranslatedZubatReturnsYodaDescription() {
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
    @DisplayName("When requesting a non-cave non-legendary Pokemon like Pikachu, apply the Shakespeare transformation to its description")
    void getTranslatedPikachuReturnsShakespeareDescription() {
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

    @Test
    @DisplayName("When the funtranslations API errors, return the original description")
    void getTranslatedPokemonReturnsDefaultDescriptionOnTranslationError() {
        final var response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/translated/ditto".formatted(port), Pokemon.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isEqualTo(new Pokemon(
                "ditto",
                "Capable of copying an enemy's genetic code to instantly transform itself into a duplicate of the enemy.",
                "urban",
                false
        ));
    }

    @Test
    @DisplayName("When requesting a non-existing translated Pokemon, return \"Pokemon not found\"")
    void getTranslatedPokemonNotFound() {
        final var response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/translated/notfound".formatted(port), Map.class);
        assertThat(response.getStatusCode().value()).isEqualTo(404);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).containsAllEntriesOf(Map.of(
                "message", "Pokemon not found",
                "path", "/pokemon/translated/notfound",
                "status", 404
        ));
    }

    @Test
    @DisplayName("When pokeapi errors on the translated endpoint, return an \"Internal server error\"")
    void getTranslatedPokemonUnexpectedPokeapiError() {
        final var response = testRestTemplate.getForEntity("http://localhost:%d/pokemon/translated/servererror".formatted(port), Map.class);
        assertThat(response.getStatusCode().value()).isEqualTo(500);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).containsAllEntriesOf(Map.of(
                "message", "Internal server error",
                "path", "/pokemon/translated/servererror",
                "status", 500
        ));
    }

}
