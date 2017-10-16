package com.CI;

import com.data.mapper.CollectionMetaData;
import com.data.mapper.Metadata;
import com.data.mapper.ModelMaker;
import com.data.scaffolder.ScaffolderMain;

import utilities.ValidatorXmlXsl;

public class Utilities {

	public static void createConcreteDao(String superDao, String modelClass) {
		ScaffolderMain scaffolder = new ScaffolderMain();
		scaffolder.generateClass(superDao, modelClass);
	}

	public static void createModelsFromXml() throws Exception {
		ValidatorXmlXsl validator = new ValidatorXmlXsl();
		validator.run();
		ModelMaker maker = new ModelMaker();
		maker.run();
	}
}
