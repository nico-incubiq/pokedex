package fr.nseverin.pokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PokedexApplication {

    public static void main(final String[] args) {
        SpringApplication.run(PokedexApplication.class, args);
    }

}
