package org.acme.practice.model.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class CountryMetaInfoDTO {
    private String region;
    private int population;
    private String flag;


	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getPopulation() {
		return this.population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}