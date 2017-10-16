package com.data.mapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.bson.Document;
import org.json.JSONObject;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Parser {
	private static final String MAPPING_FILE = "/home/bilalam/git/GenericMongo/resources/mapping.xml";
	private static final DocumentBuilderFactory parserFactory = DocumentBuilderFactory.newInstance();
	private static org.w3c.dom.Document document;

	private static final MongoClient myClient = new MongoClient("localhost:27017");

	static {
		initialize();
	}

	public static Metadata parse() {
		Metadata data = new Metadata();
		List<CollectionMetaData> collections = new ArrayList<>();
		List<Node> attributeNodes = null;
		CollectionMetaData collection = null;
		MongoDatabase database = null;
		MongoCollection<Document> mongoCollection = null;

		NodeList list = document.getDocumentElement().getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node tempNode = list.item(i);
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				String dbName = tempNode.getAttributes().getNamedItem("name").getTextContent();
				// check if db name exists
				if (checkDBExistence(dbName)) {
					// opens a connection to that database for checking collections and attributes
					database = myClient.getDatabase(dbName);
					System.out.println("database found successfully");
					data.setDbName(dbName);
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
						// get collection name from xml
						String collectionName = tempChildOfDbNode.getAttributes().getNamedItem("name").getTextContent();
						// check its existence
						if (checkCollectionExistence(database, collectionName)) {

							// set the collection name and collection class name attributes
							collection.setCollectionName(collectionName);

							collection.setCollectionClassName(
									tempChildOfDbNode.getAttributes().getNamedItem("class").getTextContent());
						} else {
							// we couldnt found any collection , exit the parsing procedure
							System.out.println("collection doesnt exist , please create one");
							break;
						}
						// get the child <attribute>..</attribute> nodes of that SINGLE collection
						NodeList childsOfCollection = tempChildOfDbNode.getChildNodes();
						// traverse the list
						for (int k = 0; k < childsOfCollection.getLength(); k++) {
							// get a single <attribute>..</..> node
							Node tempChildOfCollection = childsOfCollection.item(k);
							// check it
							if (tempChildOfCollection.getNodeType() == Node.ELEMENT_NODE) {
								// add the attribute node completely into the attributeNodes list
								
								if(getAndCheckAttributesExistence(tempChildOfCollection, database, mongoCollection, collectionName)) {
									attributeNodes.add(tempChildOfCollection);
								}
								else {
									break;
								}
								
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
		return data;
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
			if (s.equals(DbName)) {
				return true;
			}
		}
		return false;
	}

	// uses Mappy.getAttributes() logic
	private static boolean getAndCheckAttributesExistence(Node attributeNode, MongoDatabase database,
			MongoCollection<Document> collection, String collectionName) {
		String attributeKey = attributeNode.getAttributes().getNamedItem("key").getTextContent();
		for (String s : getAttributes(database, collection, collectionName)) {
			if (s.equals(attributeKey)) {
				return true;
			}
		}
		System.out.println("the " + attributeKey + " does not exist in the collection " + collectionName);
		System.out.println("please create the collection key first then run...");
		return false;
	}

	private static boolean checkCollectionExistence(MongoDatabase database, String collectionName) {
		for (String s : database.listCollectionNames()) {
			if (s.equals(collectionName)) {
				return true;
			}
		}
		return false;
	}

	public static List<String> getAttributes(MongoDatabase database, MongoCollection<Document> collection,
			String collectionName) {
		List<String> attributeList = new ArrayList<>();
		collection = database.getCollection(collectionName);
		JSONObject object = new JSONObject(collection.find(Filters.exists("_id")).first().toJson());
		String[] fieldNames = JSONObject.getNames(object);
		for (String s : fieldNames) {
			attributeList.add(s);
		}
		return attributeList;
	}

}
