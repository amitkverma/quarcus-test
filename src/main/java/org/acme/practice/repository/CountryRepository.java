package org.acme.practice.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.acme.practice.model.entity.Country;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CountryRepository implements PanacheRepository<Country> {
    
    public List<Country> findCountryByName(String name){
        return find("name", name).list();
    }
}