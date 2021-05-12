package com.nakao.sensor.model;

public class Coordinate {
	private Integer x;
	private Integer y;
	private float heat;

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public float getHeat() {
		return heat;
	}

	public void setHeat(float heat) {
		this.heat = heat;
	}

	@Override
	public String toString() {
		return "Coordinate [x=" + x + ", y=" + y + ", heat=" + heat + "]";
	}

	public Coordinate(Integer x, Integer y, float heat) {
		super();
		this.x = x;
		this.y = y;
		this.heat = heat;
	}
}
