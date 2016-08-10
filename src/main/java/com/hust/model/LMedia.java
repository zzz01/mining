package com.hust.model;

import java.util.List;

public class LMedia {
	private int id;
	private String name;
	private int weight;
	private List<IMedia> medialist;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public List<IMedia> getMedialist() {
		return medialist;
	}

	public void setMedialist(List<IMedia> medialist) {
		this.medialist = medialist;
	}

	@Override
	public String toString() {
		return "LMedia [id=" + id + ", name=" + name + ", weight=" + weight + ", medialist=" + medialist + "]";
	}

}
