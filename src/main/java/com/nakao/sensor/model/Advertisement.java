package com.nakao.sensor.model;

import java.io.Serializable;

public class Advertisement implements Serializable {

	private static final long serialVersionUID = -83495614936533070L;

	private String sensor;
	private String address;
	private String detected;
	private String since;
	private String name;
	private int rssi;
	private String advertisement;
	private String scanresponse;
	private String eventtype;

	public String getSensor() {
		return sensor;
	}

	public void setSensor(String sensor) {
		this.sensor = sensor;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetected() {
		return detected;
	}

	public void setDetected(String detected) {
		this.detected = detected;
	}

	public String getSince() {
		return since;
	}

	public void setSince(String since) {
		this.since = since;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRssi() {
		return rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public String getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(String advertisement) {
		this.advertisement = advertisement;
	}

	public String getScanresponse() {
		return scanresponse;
	}

	public void setScanresponse(String scanresponse) {
		this.scanresponse = scanresponse;
	}

	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

	@Override
	public String toString() {
		return "Advertisement [sensor=" + sensor + ", address=" + address + ", detected=" + detected + ", since="
				+ since + ", name=" + name + ", rssi=" + rssi + ", advertisement=" + advertisement + ", scanresponse="
				+ scanresponse + ", eventtype=" + eventtype + "]";
	}

}
