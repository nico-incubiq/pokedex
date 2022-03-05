package fr.nseverin.pokedex.configuration;

import feign.codec.ErrorDecoder;
import fr.nseverin.pokedex.exception.InternalServerError;
import fr.nseverin.pokedex.exception.PokemonNotFound;
import org.springframework.context.annotation.Bean;

public class PokeapiClientConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> switch (response.status()) {
            case 404 -> new PokemonNotFound();
            default -> new InternalServerError();
        };
    }

}
