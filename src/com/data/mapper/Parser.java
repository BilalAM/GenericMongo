package com.data.mapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parser {
	private static final String MAPPING_FILE = "/home/bilalam/git/GenericMongo/resources/mapping.xml";
	private static final DocumentBuilderFactory parserFactory = DocumentBuilderFactory.newInstance();
	private static org.w3c.dom.Document document;
	private static List<String> collectionAttributesData = new ArrayList<>();

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

	public Metadata parse() {
		Metadata data = new Metadata();
		List<CollectionMetaData> collections = new ArrayList<>();
		List<Node> attributeNodes = new ArrayList<>();
		CollectionMetaData collection = null;

		NodeList list = document.getDocumentElement().getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			// System.out.println(list.getLength());
			Node tempNode = list.item(i);
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println(tempNode.getNodeName());
				/* setting dbName */
				data.setDbName(tempNode.getAttributes().getNamedItem("name").getTextContent());
				// get collection nodes against dbName node
				NodeList childsOfDb = tempNode.getChildNodes();
				// System.out.println(childsOfDb.getLength());
				// traverse the collection nodes
				for (int j = 0; j < childsOfDb.getLength(); j++) {
					// get a single collection node
					Node tempChildOfDbNode = childsOfDb.item(j);
					// check it if it is really a element node
					if (tempChildOfDbNode.getNodeType() == Node.ELEMENT_NODE) {
						// initialize the metadata for a SINGLE collection
						/**/ collection = new CollectionMetaData();

						System.out.println("  " + tempChildOfDbNode.getNodeName());

						// get the attributes of the collection node
						NamedNodeMap attributesOfCollection = tempChildOfDbNode.getAttributes();
						// print them for testing
						System.out.println(tempChildOfDbNode.getNodeName() + "atr --> "
								+ attributesOfCollection.getNamedItem("name") + "   "
								+ attributesOfCollection.getNamedItem("class"));
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
								System.out.println("     " + tempChildOfCollection.getNodeName());
							}
						}
						// after the list is filled , set the attribute nodes to the collectionmetadata
						collection.setAttributeNodes(attributeNodes);
						// add the overall collection datastructure into the collections list
						collections.add(collection);
					}

				}
				System.out.println("size of collections list --> " + collections.size());
				data.setCollections(collections);
			}
		}
		System.out.println("ds");
		return data;
	}

}
