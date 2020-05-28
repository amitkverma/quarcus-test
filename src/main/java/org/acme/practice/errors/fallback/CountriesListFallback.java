package org.acme.practice.errors.fallback;

import java.util.Collections;
import java.util.List;

import org.acme.practice.model.dto.CountryDTO;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountriesListFallback implements FallbackHandler<List<CountryDTO>> {

    private Logger logger = LoggerFactory.getLogger(CountriesListFallback.class);

	@Override
	public List<CountryDTO> handle(ExecutionContext context) {
        logger.info("Countries fallback class called returning empty list");
		return Collections.emptyList();
	}
    
}