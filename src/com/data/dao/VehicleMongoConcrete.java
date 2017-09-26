package com.data.dao;

import com.data.models.Vehicle;

public class VehicleMongoConcrete extends MongoConcrete<Vehicle> {
	public VehicleMongoConcrete() {
		super("VehicleCollection");
	}
}