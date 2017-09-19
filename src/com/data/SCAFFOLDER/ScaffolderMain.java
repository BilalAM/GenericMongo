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
		String appendedModelName = String.valueOf(camelCasedName) + modelClass.substring(1) + "MongoConcrete" + ".java";

		// make it a file
		File file = new File(appendedModelName);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			StringBuilder codeBuilder = new StringBuilder();
			codeBuilder.append("package com.data.DAO");
			codeBuilder.append("\n");
			codeBuilder.append("import com.data.MODELS." + modelClass);
			
			writer.write(codeBuilder.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
