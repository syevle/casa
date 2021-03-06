package com.city.model.obj;
// default package
// Generated May 2, 2013 10:50:23 AM by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DvdLocation generated by hbm2java
 */
@Entity
@Table(name = "DVD_LOCATION")
public class DvdLocation implements Cloneable, Serializable {

	private String dvdLocation;

	public DvdLocation() {
	}

	public DvdLocation(String dvdLocation) {
		this.dvdLocation = dvdLocation;
	}

	@Id
	@Column(name = "DVD_LOCATION", unique = true, nullable = false, length = 20)
	public String getDvdLocation() {
		return this.dvdLocation;
	}

	public void setDvdLocation(String dvdLocation) {
		this.dvdLocation = dvdLocation;
	}

}
