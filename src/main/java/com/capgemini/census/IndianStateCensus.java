package com.capgemini.census;

import com.opencsv.bean.CsvBindByName;

public class IndianStateCensus {
	@CsvBindByName(column = "State")
	private String stateName;

	@CsvBindByName(column = "Population")
	private String population;

	@CsvBindByName(column = "AreaInSqKm")
	private String area;

	@CsvBindByName(column = "DensityPerSqKm")
	private String density;

	@Override
	public String toString() {
		return "\nState Name : " + stateName + "|| Population :  " + population + "|| Area(persqkm) : " + area
				+ "|| Density(persqkm) : " + density;
	}
}
