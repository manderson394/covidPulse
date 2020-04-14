package edu.matc.covidPulse.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a single data point for a given StateResponse.
 * Each data point represents the cumulative case and death counts on the specified date.
 */
public class StateDataItem {

	@JsonProperty("date")
	private String date;

	@JsonProperty("cases")
	private int cases;

	@JsonProperty("deaths")
	private int deaths;

	/**
	 * Set date.
	 *
	 * @param date the date
	 */
	public void setDate(String date){
		this.date = date;
	}

	/**
	 * Get date string.
	 *
	 * @return the string
	 */
	public String getDate(){
		return date;
	}

	/**
	 * Set cases.
	 *
	 * @param cases the cases
	 */
	public void setCases(int cases){
		this.cases = cases;
	}

	/**
	 * Get cases int.
	 *
	 * @return the int
	 */
	public int getCases(){
		return cases;
	}

	/**
	 * Set deaths.
	 *
	 * @param deaths the deaths
	 */
	public void setDeaths(int deaths){
		this.deaths = deaths;
	}

	/**
	 * Get deaths int.
	 *
	 * @return the int
	 */
	public int getDeaths(){
		return deaths;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		StateDataItem that = (StateDataItem) o;
		return cases == that.cases &&
				deaths == that.deaths &&
				date.equals(that.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, cases, deaths);
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