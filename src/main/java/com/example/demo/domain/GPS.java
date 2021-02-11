package com.example.demo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@Entity
@XmlRootElement
public class GPS {
	private @Id @GeneratedValue Long id;
	
	private long userId;

	@XmlAttribute
	private String version;

	@XmlAttribute
	private String creator;

	@OneToOne(targetEntity = Metadata.class, mappedBy = "gps", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Metadata metadata;

	@JacksonXmlElementWrapper(useWrapping = false)
	@OneToMany(targetEntity = Waypoint.class, mappedBy = "gps", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Waypoint> wpt;

	@OneToOne(targetEntity = Track.class, mappedBy = "gps", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Track trk;

	public GPS() {};
	
	public GPS(Long id, long userId, String version, String creator, Metadata metadata, List<Waypoint> wpt, Track trk) {
		super();
		this.id = id;
		this.userId = userId;
		this.version = version;
		this.creator = creator;
		this.metadata = metadata;
		this.wpt = wpt;
		this.trk = trk;
	}



	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<Waypoint> getWpt() {
		return wpt;
	}

	public void setWpt(List<Waypoint> wpt) {
		this.wpt = wpt;
	}

	public Track getTrk() {
		return trk;
	}

	public void setTrk(Track trk) {
		this.trk = trk;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
