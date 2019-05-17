package com.example.tricogassignment.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Terms{

	@SerializedName("description")
	private List<String> description;

	public void setDescription(List<String> description){
		this.description = description;
	}

	public List<String> getDescription(){
		return description;
	}

	@Override
 	public String toString(){
		return 
			"Terms{" + 
			"description = '" + description + '\'' + 
			"}";
		}
}