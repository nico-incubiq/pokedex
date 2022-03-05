package fr.nseverin.pokedex.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class RestConfiguration {

    @Bean
    RestTemplate restTemplate() {
        // TODO: Remove once migrated to Feign.
        return new RestTemplate();
    }

}
