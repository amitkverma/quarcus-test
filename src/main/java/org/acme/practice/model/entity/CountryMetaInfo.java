package org.acme.practice.model.entity;

public class CountryMetaInfo {
    private String region;
    private int population;
    private String flag;
    private Long id;


    public CountryMetaInfo(){

    }

    public CountryMetaInfo(String region, int population, String flag){
        this.region = region;
        this.population = population;
        this.flag = flag;
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


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
    
    @Override
    public String toString(){
        return "R: " + this.getRegion() + " P: " + this.getFlag() + " F: " + this.getFlag();
    }

    
}