package com.example.tricogassignment.api.model;

import com.google.gson.annotations.SerializedName;

public class RedirectsItem{

	@SerializedName("index")
	private int index;

	@SerializedName("from")
	private String from;

	@SerializedName("to")
	private String to;

	public void setIndex(int index){
		this.index = index;
	}

	public int getIndex(){
		return index;
	}

	public void setFrom(String from){
		this.from = from;
	}

	public String getFrom(){
		return from;
	}

	public void setTo(String to){
		this.to = to;
	}

	public String getTo(){
		return to;
	}

	@Override
 	public String toString(){
		return 
			"RedirectsItem{" + 
			"index = '" + index + '\'' + 
			",from = '" + from + '\'' + 
			",to = '" + to + '\'' + 
			"}";
		}
}