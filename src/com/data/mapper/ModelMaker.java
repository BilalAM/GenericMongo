package com.data.mapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Attribute;
import org.jdom2.Document;
import org.jdom2.transform.JDOMSource;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;

import com.data.mapper.Mappy;

import lombok.val;
import utilities.ValidatorXmlXsl;

public class ModelMaker {

	private static final Metadata DATA = Parser.parse();

	/* tests */
	public static void main(String[] args) {

		/*
		  	
		  
		  
		 
		 * */

	}

	private static void generateClass(CollectionMetaData collection) {
		String className = collection.getCollectionClassName();
		Map<String, String> attributes = new HashMap<>();
		
		
		
		
		
	}
	
	private String generateVariableDeclaration(CollectionMetaData collection) {
		StringBuilder builder = new StringBuilder();
		for(Node attributeNode : collection.getAttributeNodes()) {
			/*     
			 if(attributeNode.getTextContent(type).equals(String)){
			 	builder.append(declareStringVariable(....)
			 }
			 do like this...
			 
			 **/
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
		return "double" + identifier + ";";
	}

}
