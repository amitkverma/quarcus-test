package org.acme.practice.model.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class CurrencyDTO {
    private String code;
    private String name;
    private String symbol;

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}