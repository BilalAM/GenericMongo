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
		}
		return nodes;
	}

	private static String getDatabaseName() {
		Node nNode = document.getElementsByTagName("db").item(0).getAttributes().getNamedItem("name");
		if (nNode.getNodeValue() == "") {
			return "Name of database is empty";
		} else {
			return nNode.getNodeValue();
		}
	}

	private static List<Node> getCollectionNodes() {
		return getNodes("collection");
	}

	private static boolean checkCollectionAttributes(Node nNode) {
		boolean check = true;
		for (int i = 0; i < nNode.getAttributes().getLength(); i++) {
			try {
				if (nNode.getAttributes().getNamedItem("name").getNodeName().equals("name")
						&& nNode.getAttributes().getNamedItem("class").getNodeName().equals("class")) {
					check = true;
				} else {
					check = false;
				}
			} catch (Exception e) {
				System.out.println("one of the attrbutes are missing , please check the format");
				e.printStackTrace();
			}
		}
		return check;
	}

	/* tests */
	public static void main(String[] args) {
		try {
			for (Node node : getCollectionNodes()) {
				System.out.println(checkCollectionAttributes(node));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
