package com.data.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

public class Parser {
	private static final String MAPPING_FILE = "/home/bilalam/git/GenericMongo/resources/mapping.xml";
	private static final DocumentBuilderFactory parserFactory = DocumentBuilderFactory.newInstance();
	private static org.w3c.dom.Document document;
	private static List<String> collectionAttributesData = new ArrayList<>();
}
