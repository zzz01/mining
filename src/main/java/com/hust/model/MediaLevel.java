package com.hust.model;

import java.util.List;

public class MediaLevel {

	private String name;
	private List<String> mediaList;
	private int weight;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getMediaList() {
		return mediaList;
	}

	public void setMediaList(List<String> mediaList) {
		this.mediaList = mediaList;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Media [name=" + name + ", mediaList=" + mediaList + ", weight=" + weight + "]";
	}

}
