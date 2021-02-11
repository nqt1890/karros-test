package com.example.demo.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Metadata {
	private @Id @GeneratedValue Long id;

	private String name;

	@Lob
	@Column(length = 10000)
	private String desc;
	private String author;
	private Date time;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gps_id", referencedColumnName = "id")
	private GPS gps;

	@OneToOne(targetEntity = Link.class, mappedBy = "metadata", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Link link;

	public Metadata() {
	}

	public Metadata(String name, String desc, String author, Date time, Link link) {
		super();
		this.name = name;
		this.desc = desc;
		this.author = author;
		this.time = time;
		this.link = link;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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
