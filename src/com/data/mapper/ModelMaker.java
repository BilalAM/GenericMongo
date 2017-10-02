package com.data.mapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.Document;
import org.jdom2.transform.JDOMSource;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.data.mapper.Mappy;

public class ModelMaker extends Mappy {

	private static final String MAPPING_FILE = "/home/bilalam/git/GenericMongo/resources/mapping.xml"; 
	private static final DocumentBuilderFactory parserFactory = DocumentBuilderFactory.newInstance();

	
	
	/* tests*/
	public static void main(String[] args) {
		try {
			File file = new File(MAPPING_FILE);
			DocumentBuilder builder = parserFactory.newDocumentBuilder();
			org.w3c.dom.Document document = builder.parse(file);
			document.getDocumentElement().normalize();
			
			System.out.println("ROOT ELEMENT --> " + document.getDocumentElement().getNodeName());
			NodeList collectionNodes = document.getElementsByTagName("collection");
			
			for(int i =0 ; i < collectionNodes.getLength()-1 ; i++){
				Node nNode = collectionNodes.item(i);
				
				System.out.println("current node --> " + nNode.getNodeName());
				System.out.println("current node type --> " + nNode.getNodeType());
				System.out.println(nNode.getAttributes().getNamedItem("name").getNodeValue());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	@Override
	public List<String> getAttributes(String collectionName) {
		// TODO Auto.-generated method stub
		return super.getAttributes(collectionName);
	}
	
	
	

}
