package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Waypoint {
	private @Id @GeneratedValue Long id;

	@XmlAttribute
	double lat;

	@XmlAttribute
	double lon;

	@XmlElement
	String name;

	@XmlElement
	String sym;

	@ManyToOne
	@JoinColumn(name = "gps_id")
	GPS gps;

	public Waypoint() {
	}

	public Waypoint(double lat, double lon, String name, String sym) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.name = name;
		this.sym = sym;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSym() {
		return sym;
	}

	public void setSym(String sym) {
		this.sym = sym;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonIgnore
	public GPS getGps() {
		return gps;
	}

	public void setGps(GPS gps) {
		this.gps = gps;
	}
}
