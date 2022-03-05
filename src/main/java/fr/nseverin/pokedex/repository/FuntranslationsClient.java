package fr.nseverin.pokedex.repository;

import fr.nseverin.pokedex.repository.model.funtranslations.Funtranslation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "funtranslations", url = "${pokedex.external-apis.funtranslations.base-url}")
public interface FuntranslationsClient {

    @RequestMapping(method = RequestMethod.GET, path = "/{language}.json?text={text}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Funtranslation translate(@PathVariable final String text, @PathVariable final String language);

}
