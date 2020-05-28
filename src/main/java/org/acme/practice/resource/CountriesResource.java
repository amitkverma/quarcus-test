package org.acme.practice.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.acme.practice.model.dto.CountryDTO;
import org.acme.practice.model.dto.CurrencyDTO;
import org.acme.practice.model.dto.SearchDTO;
import org.acme.practice.model.entity.Country;
import org.acme.practice.model.mapper.CountryMapper;
import org.acme.practice.model.mapper.CurrencyMapper;
import org.acme.practice.repository.CountryRepository;
import org.acme.practice.service.CountriesService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.reactive.streams.operators.spi.Stage.Collect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.panache.common.Sort;

@Path("/country")
public class CountriesResource {

    private Logger logger = LoggerFactory.getLogger(CountriesResource.class);
    
    @Inject
    private CountryRepository countryRepository;

    @Inject
    private CountriesService countryService;
    
    @Inject
    private CountryMapper countryMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(
            value = {
                @APIResponse(
                        responseCode = "404",
                        description = "No Country found"),
                @APIResponse(
                        responseCode = "200",
                        description = "Return the list of Countries")
            })
    @Operation(
        summary = "List of Countries",
        description = "Get all list of countries from database"
    )
    public Response getByName(@Parameter(description = "Name of the country to search") @QueryParam("name") String name){
        if(name == null){
            return Response.status(Status.ACCEPTED).entity(countryRepository.listAll(Sort.by("name"))).build();
        }
        return Response.status(Status.ACCEPTED).entity(countryRepository.findCountryByName(name)).build();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addCountry(SearchDTO search){
        logger.debug("User is adding country with name {}", search);
        List<Country> countryList = countryService.getListOfCountriesByName(search.getSearch()).stream()
        .map((countryDTO)-> {
            Country country = countryMapper.toEntity(countryDTO);
            if(countryDTO.getCurrencies().size() > 1){
                CurrencyDTO currency = countryDTO.getCurrencies().get(0);
                country.setSymbol(currency.getSymbol());
                country.setCode(currency.getCode());
            }
            return country;
        })
        .collect(Collectors.toList());

        Country.persist(countryList);

        return Response.ok(countryList).build();
    }

}