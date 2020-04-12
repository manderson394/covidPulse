package edu.matc.covidPulse.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class StateDataItem {

	@JsonProperty("date")
	private LocalDate date;

	@JsonProperty("cases")
	private int cases;

	@JsonProperty("deaths")
	private int deaths;

	public void setDate(LocalDate date){
		this.date = date;
	}

	public LocalDate getDate(){
		return date;
	}

	public void setCases(int cases){
		this.cases = cases;
	}

	public int getCases(){
		return cases;
	}

	public void setDeaths(int deaths){
		this.deaths = deaths;
	}

	public int getDeaths(){
		return deaths;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"date = '" + date + '\'' + 
			",cases = '" + cases + '\'' + 
			",deaths = '" + deaths + '\'' + 
			"}";
		}
}