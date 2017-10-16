package com.data.mapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import org.w3c.dom.Node;

public class ModelMaker {

	private static final Metadata DATA = Parser.parse();

	/* tests */
	public static void main(String[] args) throws Exception {
		for (CollectionMetaData collection : DATA.getCollections()) {
			generateClass(collection);
		}
	}

	public void run() throws Exception {
		for (CollectionMetaData collection : DATA.getCollections()) {
			generateClass(collection);
		}
	}
	@SuppressWarnings("unused")
	private static void generateClass(CollectionMetaData collection) throws Exception {
		String className = collection.getCollectionClassName();
		String classNameWithoutExtension = className.substring(0, className.indexOf("."));
		File file = new File("src/com/data/models/" + className);
		if (file.exists()) {
			System.out.println(className + " already created");
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.append("package com.data.models;");
			writer.append("\n");
			if (generateVariableDeclaration(collection).contains("collection")) {
				writer.append("import java.util;");
			}
			writer.append("\n");
			writer.append("public class " + classNameWithoutExtension);
			writer.append("\n {");
			writer.append("\n");
			writer.append(generateVariableDeclaration(collection));
			writer.append("\n");
			writer.append("}");
		}

	}

	@SuppressWarnings("unused")
	private static String generateVariableDeclaration(CollectionMetaData collection) {
		StringBuilder builder = new StringBuilder();

		for (Node attributeNode : collection.getAttributeNodes()) {
			String typeVariable = attributeNode.getAttributes().getNamedItem("type").getTextContent();
			String identifer = attributeNode.getAttributes().getNamedItem("name").getTextContent();
			if (typeVariable.equalsIgnoreCase("string")) {
				builder.append(declareStringVariable(identifer));
				builder.append("\n");
			} else if (typeVariable.equalsIgnoreCase("int")) {
				builder.append(declareIntegerVariable(identifer));
				builder.append("\n");

			} else if (typeVariable.equalsIgnoreCase("double")) {
				builder.append(declareDoubleVariable(identifer));
				builder.append("\n");

			} else if (typeVariable.equalsIgnoreCase("collection")) {
				builder.append(declareListVariable(identifer, typeVariable));
				builder.append("\n");

			}

		}
		return builder.toString();
	}

	private static String declareStringVariable(String identifier) {
		return "String " + identifier + ";";
	}

	private static String declareIntegerVariable(String identifier) {
		return "int " + identifier + ";";

	}

	private static String declareListVariable(String identifier, String type) {

		return "List<" + type + "> " + identifier + ";";
	}

	private static String declareDoubleVariable(String identifier) {
		return "double " + identifier + ";";
	}

}
