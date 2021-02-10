package com.example.demo.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@XmlRootElement
public class GPS {
	@XmlAttribute
	String version;

	@XmlAttribute
	String creator;

	Metadata metadata;

	@JacksonXmlElementWrapper(useWrapping = false)
	List<Waypoint> wpt;

	Track trk;

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

}
