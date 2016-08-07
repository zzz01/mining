package com.hust.model;

public class User {
	private String username;
	private String passwd;
	private int authlevel;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getAuthlevel() {
		return authlevel;
	}

	public void setAuthlevel(int authlevel) {
		this.authlevel = authlevel;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", passwd=" + passwd + ", authlevel=" + authlevel + "]";
	}

}
