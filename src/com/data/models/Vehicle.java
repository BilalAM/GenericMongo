package com.data.models;

import java.io.Serializable;

public class Vehicle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7953839798341810420L;
	
	
	private int vehicleCode;
	private String vehMake;
	private String vehColour;
	private double vehPrice;
	
	public int getVehicleCode() {
		return vehicleCode;
	}
	public void setVehicleCode(int vehicleCode) {
		this.vehicleCode = vehicleCode;
	}
	public String getVehMake() {
		return vehMake;
	}
	public void setVehMake(String vehMake) {
		this.vehMake = vehMake;
	}
	public String getVehColour() {
		return vehColour;
	}
	public void setVehColour(String vehColour) {
		this.vehColour = vehColour;
	}
	public double getVehPrice() {
		return vehPrice;
	}
	public void setVehPrice(double vehPrice) {
		this.vehPrice = vehPrice;
	}

}
