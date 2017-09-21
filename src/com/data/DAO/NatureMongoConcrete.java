package com.data.DAO;

import com.data.MODELS.Nature;

public class NatureMongoConcrete extends MongoConcrete<Nature> {
	public NatureMongoConcrete() {
		super("NatureCollection");
	}
}