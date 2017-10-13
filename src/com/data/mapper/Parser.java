package com.data.mapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.bson.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Parser {
	private static final String MAPPING_FILE = "/home/bilalam/git/GenericMongo/resources/mapping.xml";
	private static final DocumentBuilderFactory parserFactory = DocumentBuilderFactory.newInstance();
	private static org.w3c.dom.Document document;
	private static List<String> collectionAttributesData = new ArrayList<>();

	private static final MongoClient myClient = new MongoClient("localhost:27017");
	private static MongoDatabase database;
	private MongoCollection<Document> collection;

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

	private static boolean checkDBExistence(String DbName) {

		for (String s : myClient.listDatabaseNames()) {
			if (s.equalsIgnoreCase(DbName)) {
				System.out.println(s);
				return true;
			}
		}
		return false;
	}

	public Metadata parse() {
		Metadata data = new Metadata();
		List<CollectionMetaData> collections = new ArrayList<>();
		List<Node> attributeNodes = null;
		CollectionMetaData collection = null;

		NodeList list = document.getDocumentElement().getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node tempNode = list.item(i);
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				// check if db name exists
				if (checkDBExistence(tempNode.getAttributes().getNamedItem("name").getTextContent())) {
					System.out.println("database found successfully");
					data.setDbName(tempNode.getAttributes().getNamedItem("name").getTextContent());
				} else {
					System.out.println("database doesnt exists");
					break;
				}
				// get collection nodes against dbName node
				NodeList childsOfDb = tempNode.getChildNodes();
				// traverse the collection nodes
				for (int j = 0; j < childsOfDb.getLength(); j++) {
					// get a single collection node
					Node tempChildOfDbNode = childsOfDb.item(j);
					// check it if it is really a element node
					if (tempChildOfDbNode.getNodeType() == Node.ELEMENT_NODE) {
						// initialize the metadata for a SINGLE collection
						collection = new CollectionMetaData();
						attributeNodes = new ArrayList<>();

						// get the attributes of the collection node
						NamedNodeMap attributesOfCollection = tempChildOfDbNode.getAttributes();

						// set the collection name and collection class name attributes
						collection.setCollectionName(
								tempChildOfDbNode.getAttributes().getNamedItem("name").getTextContent());
						collection.setCollectionClassName(
								tempChildOfDbNode.getAttributes().getNamedItem("class").getTextContent());

						// get the child <attribute>..</attribute> nodes of that SINGLE collection
						NodeList childsOfCollection = tempChildOfDbNode.getChildNodes();
						// traverse the list
						for (int k = 0; k < childsOfCollection.getLength(); k++) {
							// get a single <attribute>..</..> node
							Node tempChildOfCollection = childsOfCollection.item(k);
							// check it
							if (tempChildOfCollection.getNodeType() == Node.ELEMENT_NODE) {
								// add the attribute node completely into the attributeNodes list
								attributeNodes.add(tempChildOfCollection);
							}
						}
						// after the list is filled , set the attribute nodes to the collectionmetadata
						collection.setAttributeNodes(attributeNodes);
						// add the overall collection datastructure into the collections list
						collections.add(collection);
					}

				}
				// System.out.println("size of collections list --> " + collections.size());
				data.setCollections(collections);
			}
		}
		// System.out.println("ds");
		return data;
	}

}
