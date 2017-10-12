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
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;

import com.data.mapper.Mappy;

import utilities.ValidatorXmlXsl;

public class ModelMaker {

	/* tests */
	public static void main(String[] args) {
		ValidatorXmlXsl d = new ValidatorXmlXsl();
		d.run();
	}

}
