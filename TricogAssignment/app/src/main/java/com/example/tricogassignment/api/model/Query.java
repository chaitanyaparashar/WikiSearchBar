package com.example.tricogassignment.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Query{

	@SerializedName("pages")
	private List<PagesItem> pages;

	@SerializedName("redirects")
	private List<RedirectsItem> redirects;

	public void setPages(List<PagesItem> pages){
		this.pages = pages;
	}

	public List<PagesItem> getPages(){
		return pages;
	}

	public void setRedirects(List<RedirectsItem> redirects){
		this.redirects = redirects;
	}

	public List<RedirectsItem> getRedirects(){
		return redirects;
	}

	@Override
 	public String toString(){
		return 
			"Query{" + 
			"pages = '" + pages + '\'' + 
			",redirects = '" + redirects + '\'' + 
			"}";
		}
}