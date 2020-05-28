package org.acme.practice.model.mapper;

import javax.enterprise.context.ApplicationScoped;

import org.acme.practice.model.dto.CurrencyDTO;
import org.acme.practice.model.entity.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi", uses = {})
@ApplicationScoped
public interface CurrencyMapper extends EntityMapper<CurrencyDTO, Country> {
    
}