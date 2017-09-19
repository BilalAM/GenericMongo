/**
 * 
 */
package com.data.SCAFFOLDER;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @author bilalam
 *
 */
public class ScaffolderMain implements IScaffold {
	// Productmongoconcrete
	@Override
	public void generateClass(String superDaoClass, String modelClass) {

		// {P}roductmongoconcrete
		char camelCasedName = Character.toUpperCase(modelClass.charAt(0));

		// ProductMongoConcrete.java
		String appendedModelName = String.valueOf(camelCasedName) + "MongoConcrete" + ".java";

		// make it a file
		File file = new File(appendedModelName);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
