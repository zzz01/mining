package com.hust.model;

import java.util.Date;

public class Incident {
	private String id;
	private String website;
	private String plate;
	private String title;
	private String poster;
	private Date postTime;
	private String link;
	private long hitNum;
	private long replyNum;
	private String source;
	private String ip;
	private String ipSite;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public long getHitNum() {
		return hitNum;
	}

	public void setHitNum(long hitNum) {
		this.hitNum = hitNum;
	}

	public long getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(long replyNum) {
		this.replyNum = replyNum;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIpSite() {
		return ipSite;
	}

	public void setIpSite(String ipSite) {
		this.ipSite = ipSite;
	}

	@Override
	public String toString() {
		return "Incident [id=" + id + ", website=" + website + ", plate=" + plate + ", title=" + title + ", poster="
				+ poster + ", postTime=" + postTime + ", link=" + link + ", hitNum=" + hitNum + ", replyNum=" + replyNum
				+ ", source=" + source + ", ip=" + ip + ", ipSite=" + ipSite + "]";
	}

}
