package com.hust.model;

public class IMedia {
	private int id;
	private String name;
	private String level;

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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "IMedia [id=" + id + ", name=" + name + ", level=" + level + "]";
	}

}
