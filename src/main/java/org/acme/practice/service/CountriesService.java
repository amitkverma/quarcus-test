package org.acme.practice.service;

import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.practice.client.CountriesRestClient;
import org.acme.practice.errors.fallback.CountriesListFallback;
import org.acme.practice.model.dto.CountryDTO;
import org.acme.practice.model.dto.CountryMetaInfoDTO;
import org.acme.practice.model.dto.CurrencyDTO;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.mutiny.Multi;

@ApplicationScoped
public class CountriesService {
    
    private Logger logger = LoggerFactory.getLogger(CountriesService.class);

    @Inject
    @RestClient
    private CountriesRestClient countriesRestClient;

    @Retry(maxRetries = 5)
    @Timeout(value = 1, unit = ChronoUnit.SECONDS)
    @Fallback(CountriesListFallback.class)
    public List<CountryDTO> getListOfCountriesByName(String name){
        logger.debug("calling external api to get list of countries by name {}", name);
        return countriesRestClient.getByName(name);
    }

    public Multi<CountryMetaInfoDTO> getListOfCountriesByCurrency(String cur){
        logger.debug("calling external api to get list of countries by currency {}", cur);
        return Multi.createFrom().iterable(countriesRestClient.getByCurrency(cur));
    }

}