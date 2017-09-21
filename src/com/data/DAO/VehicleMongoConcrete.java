package com.data.DAO;

import com.data.MODELS.Vehicle;

public class VehicleMongoConcrete extends MongoConcrete<Vehicle> {
	public VehicleMongoConcrete() {
		super("VehicleCollection");
	}
}