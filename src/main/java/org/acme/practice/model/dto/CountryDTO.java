package org.acme.practice.model.dto;

import java.util.List;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class CountryDTO {
    private String name;
    private String alpha2Code;
    private String capital;
    private String region;
    private List<CurrencyDTO> currencies;


	public List<CurrencyDTO> getCurrencies() {
		return this.currencies;
	}

	public void setCurrencies(List<CurrencyDTO> currencies) {
		this.currencies = currencies;
	}

    public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlpha2Code() {
		return this.alpha2Code;
	}

	public void setAlpha2Code(String alpha2Code) {
		this.alpha2Code = alpha2Code;
	}

	public String getCapital() {
		return this.capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}