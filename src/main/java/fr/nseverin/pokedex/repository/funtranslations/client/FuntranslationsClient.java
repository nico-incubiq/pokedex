package fr.nseverin.pokedex.repository.funtranslations.client;

import fr.nseverin.pokedex.repository.funtranslations.configuration.FuntranslationsClientConfiguration;
import fr.nseverin.pokedex.repository.funtranslations.model.Funtranslation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "funtranslations", url = "${pokedex.external-apis.funtranslations.base-url}", configuration = FuntranslationsClientConfiguration.class)
public interface FuntranslationsClient {

    @RequestMapping(method = RequestMethod.GET, path = "/{language}.json?text={text}", produces = MediaType.APPLICATION_JSON_VALUE)
    Funtranslation translate(@PathVariable final String text, @PathVariable final String language);

}
