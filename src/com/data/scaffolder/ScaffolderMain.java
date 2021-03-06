/**
 * 
 */
package com.data.scaffolder;

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
		// ProductMongoConcrete
		String appendedModelName = String.valueOf(camelCasedName) + modelClass.substring(1) + "MongoConcrete";
		// ProductMongoConcrete.java
		String appendedClassName = String.valueOf(camelCasedName) + modelClass.substring(1) + "MongoConcrete" + ".java";
		// make it a file
		File file = new File("src/com/data/DAO/" + appendedClassName);
		// check for pre existing class
		if (file.exists()) {
			System.out.println("concrete already created");
		}
		generateCode(file, modelClass, appendedModelName);

	}

	private static void generateCode(File file, String modelClass, String appendedModelName) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			StringBuilder codeBuilder = new StringBuilder();
			codeBuilder.append("package com.data.dao;");
			codeBuilder.append("\n");
			codeBuilder.append("import com.data.models." + modelClass + ";");
			codeBuilder.append("\n");
			codeBuilder
					.append("public class " + appendedModelName + " extends " + "MongoConcrete<" + modelClass + ">{");
			codeBuilder.append("\n");
			codeBuilder.append("public " + appendedModelName + "(){");
			codeBuilder.append("\n");
			codeBuilder.append("super(\"" + modelClass + "Collection" + "\");");
			codeBuilder.append("\n");
			codeBuilder.append("}");
			codeBuilder.append("\n");
			codeBuilder.append("}");
			writer.write(codeBuilder.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
