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
		for (int i = 0; i < documentNodes.getLength() - 1; i++) {
			Node tempNode = documentNodes.item(i);
			nodes.add(tempNode);
		}
		return nodes;
	}

	private static String getDatabaseName() {
		Node nNode = document.getFirstChild().getAttributes().getNamedItem("name");
		return nNode.getNodeValue();

	}

	/* tests */
	public static void main(String[] args) {
		try {
			System.out.println("Database Name" + getDatabaseName());
			NodeList collectionNodes = document.getElementsByTagName("collection");

			for (int i = 0; i < collectionNodes.getLength() - 1; i++) {
				Node nNode = collectionNodes.item(i);

				System.out.println("current node --> " + nNode.getNodeName());
				System.out.println("current node type --> " + nNode.getNodeType());
				System.out.println(nNode.getAttributes().getNamedItem("name").getNodeValue());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
