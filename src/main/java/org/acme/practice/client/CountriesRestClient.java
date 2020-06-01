package org.acme.practice.client;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.practice.model.dto.CountryDTO;
import org.acme.practice.model.dto.CountryMetaInfoDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;


@ApplicationScoped
@RegisterRestClient(configKey = "country-api")
@Path("/v2")
public interface CountriesRestClient {
    
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    List<CountryDTO> getByName(@PathParam String name);

    @GET
    @Path("currency/{cur}")
    List<CountryMetaInfoDTO> getByCurrency(@PathParam String cur);
}