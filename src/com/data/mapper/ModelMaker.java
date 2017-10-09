package com.data.mapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Attribute;
import org.jdom2.Document;
import org.jdom2.transform.JDOMSource;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.data.mapper.Mappy;

public class ModelMaker {
	private static final String MAPPING_FILE = "/home/bilalam/git/GenericMongo/resources/mapping.xml";
	private static final DocumentBuilderFactory parserFactory = DocumentBuilderFactory.newInstance();
	private static org.w3c.dom.Document document;
	private static List<String> collectionAttributesData = new ArrayList<>();

	static {
		initialize();
	}

	private static void initialize() {

		try {
			File file = new File(MAPPING_FILE);
			DocumentBuilder builder = parserFactory.newDocumentBuilder();
			document = builder.parse(file);
			document.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static List<Node> getNodes(String tagName) {
		List<Node> nodes = new ArrayList<>();
		NodeList documentNodes = document.getElementsByTagName(tagName);
		for (int i = 0; i < documentNodes.getLength(); i++) {
			Node tempNode = documentNodes.item(i);
			nodes.add(tempNode);
			// check child nodes
			if (tempNode.hasChildNodes()) {
				NodeList childNodes = tempNode.getChildNodes();
				for (int j = 0; j < childNodes.getLength() - 1; j++) {
					Node childTempNode = childNodes.item(j);
				}
			}

		}
		return nodes;
	}

	public static String getDatabaseName() {
		Node nNode = document.getElementsByTagName("db").item(0).getAttributes().getNamedItem("name");
		if (nNode.getNodeValue() == "") {
			return "Name of database is empty";
		} else {
			return nNode.getNodeValue();
		}
	}

	private static boolean checkAndGetCollectionAttributes(Node nNode) {
		boolean check = true;
		for (int i = 0; i < nNode.getAttributes().getLength(); i++) {
			try {
				if (nNode.getAttributes().getNamedItem("name").getNodeName().equals("name")
						&& nNode.getAttributes().getNamedItem("class").getNodeName().equals("class")) {
					// fills the list with name and class attribute values constantly
					collectionAttributesData.add(nNode.getAttributes().item(i).getTextContent());
					check = true;
				} else {
					check = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return check;
	}

	private static boolean checkAndGetAttributes(Node nNode) {
		return true;
	}

	/* tests */
	public static void main(String[] args) {
		try {
			Metadata metaData = new Metadata();

			for (Node node : getNodes("db")) {
				System.out.println(node.getAttributes().getNamedItem("name").getTextContent());
				metaData.setDbName(node.getAttributes().getNamedItem("name").getTextContent());
				for (Node collectionNode : getNodes("collection")) {
					System.out.println("collection  =====");
					System.out.println(collectionNode.getAttributes().getNamedItem("name").getTextContent());
					System.out.println(collectionNode.getAttributes().getNamedItem("class").getTextContent());
					System.out.println("=====");
					for (Node attributeNode : getNodes("attribute")) {
						System.out.println("attribute ====");
						System.out.println(attributeNode.getAttributes().getNamedItem("name").getTextContent());
						System.out.println(attributeNode.getAttributes().getNamedItem("key").getTextContent());
						System.out.println(attributeNode.getAttributes().getNamedItem("type").getTextContent());
						System.out.println("=====");

					}
				}
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

}
