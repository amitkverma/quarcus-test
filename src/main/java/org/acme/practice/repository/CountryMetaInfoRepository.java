package org.acme.practice.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.acme.practice.model.entity.CountryMetaInfo;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Tuple;

@ApplicationScoped
public final class CountryMetaInfoRepository {

    @Inject
    private  MySQLPool client;

    public Uni<Long> save(CountryMetaInfo country){
        System.out.println(country.toString());
        return client.preparedQuery("INSERT INTO country_meta_info (region, population, flag) VALUES ($1, $2, $3) RETURNING (id)", Tuple.of(country.getRegion(), country.getPopulation(), country.getFlag()))
        .onItem().apply(pgRowSet -> pgRowSet.iterator().next().getLong("id"));
    }
}