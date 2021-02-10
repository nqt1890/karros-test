package com.example.demo.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Link {
	private @Id @GeneratedValue Long id;

	@XmlAttribute
	String href;

	@XmlElement
	String text;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "metadata_id", referencedColumnName = "id")
	Metadata metadata;

	public Link() {
	}

	public Link(String href, String text) {
		super();
		this.href = href;
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

}
