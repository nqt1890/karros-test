package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Trackpoint {
	private @Id @GeneratedValue Long id;

	@XmlAttribute
	double lat;

	@XmlAttribute
	double lon;

	@XmlElement
	String ele;

	@XmlElement
	Date time;

	@ManyToOne
	@JoinColumn(name = "track_id")
	Track track;

	public Trackpoint() {
	}

	public Trackpoint(double lat, double lon, String ele, Date time) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.ele = ele;
		this.time = time;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getEle() {
		return ele;
	}

	public void setEle(String ele) {
		this.ele = ele;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@JsonIgnore
	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
