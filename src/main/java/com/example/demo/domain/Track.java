package com.example.demo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@Entity
public class Track {
	private @Id @GeneratedValue Long id;

	int userId;

	@OneToMany(targetEntity = Trackpoint.class, mappedBy = "track", cascade = CascadeType.ALL)
	@JacksonXmlElementWrapper(localName = "trkseg")
	List<Trackpoint> trkpt;

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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
