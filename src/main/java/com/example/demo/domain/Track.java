package com.example.demo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@Entity
public class Track {
	private @Id @GeneratedValue Long id;

	@OneToMany(targetEntity = Trackpoint.class, mappedBy = "track", cascade = CascadeType.ALL)
	@JacksonXmlElementWrapper(localName = "trkseg")
	private List<Trackpoint> trkpt;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gps_id", referencedColumnName = "id")
	private GPS gps;

	public Track() {
	}

	public Track(List<Trackpoint> trkpt) {
		super();
		this.trkpt = trkpt;
	}

	public List<Trackpoint> getTrkpt() {
		return trkpt;
	}

	public void setTrkpt(List<Trackpoint> trkpt) {
		this.trkpt = trkpt;
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
