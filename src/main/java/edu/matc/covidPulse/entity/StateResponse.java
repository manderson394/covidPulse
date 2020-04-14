package edu.matc.covidPulse.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The object that represents a single state and its data to be mapped into the response body
 * of an HTML response object.
 */
public class StateResponse {
	@JsonProperty("state")
	private String state;

	@JsonProperty("state_fips")
	private String stateFips;

	@JsonProperty("data")
	private List<StateDataItem> data;

	/**
	 * Instantiates a new State response.
	 */
	public StateResponse() {
		data = new ArrayList<>();
	}

	/**
	 * Set data.
	 *
	 * @param data the data
	 */
	public void setData(List<StateDataItem> data){
		this.data = data;
	}

	/**
	 * Get data list.
	 *
	 * @return the list
	 */
	public List<StateDataItem> getData(){
		return data;
	}

	/**
	 * Set state.
	 *
	 * @param state the state
	 */
	public void setState(String state){
		this.state = state;
	}

	/**
	 * Get state string.
	 *
	 * @return the string
	 */
	public String getState(){
		return state;
	}

	/**
	 * Gets state fips.
	 *
	 * @return the state fips
	 */
	public String getStateFips() {
		return stateFips;
	}

	/**
	 * Sets state fips.
	 *
	 * @param stateFips the state fips
	 */
	public void setStateFips(String stateFips) {
		this.stateFips = stateFips;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		StateResponse response = (StateResponse) o;
		return state.equals(response.state) &&
				stateFips.equals(response.stateFips) &&
				data.equals(response.data);
	}

	@Override
	public int hashCode() {
		return Objects.hash(state, stateFips, data);
	}

	@Override
 	public String toString(){
		return 
			"StateResponse{" +
				"state = '" + state + '\'' +
				",stateFips = '" + stateFips + '\'' +
				",data = '" + data + '\'' +
			"}";
		}
}