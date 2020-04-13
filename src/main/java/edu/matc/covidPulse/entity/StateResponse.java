package edu.matc.covidPulse.entity;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StateResponse {

	@JsonProperty("data")
	private List<StateDataItem> data;

	@JsonProperty("state")
	private String state;

	@JsonProperty("state_fips")
	private String stateFips;

	public StateResponse() {
		data = new ArrayList<>();
	}

	public void setData(List<StateDataItem> data){
		this.data = data;
	}

	public List<StateDataItem> getData(){
		return data;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public String getStateFips() {
		return stateFips;
	}

	public void setStateFips(String stateFips) {
		this.stateFips = stateFips;
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