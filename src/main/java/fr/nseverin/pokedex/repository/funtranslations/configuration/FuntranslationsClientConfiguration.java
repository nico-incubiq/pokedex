package fr.nseverin.pokedex.repository.funtranslations.configuration;

import feign.Logger;
import feign.codec.ErrorDecoder;
import fr.nseverin.pokedex.exception.InternalServerError;
import org.springframework.context.annotation.Bean;

public class FuntranslationsClientConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> switch (response.status()) {
            default -> new InternalServerError();
        };
    }

    @Bean
    Logger.Level loggerLevel() {
        return Logger.Level.BASIC;
    }

}
