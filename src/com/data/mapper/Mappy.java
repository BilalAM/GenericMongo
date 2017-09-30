/**
 * 
 */
package com.data.mapper;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

/**
 * @author bilalam
 *
 */
public abstract class Mappy implements IMap {

	private static final MongoClient MONGO_CLIENT = new MongoClient("localhost:27017");
	private static final MongoDatabase MONGO_DATABASE = MONGO_CLIENT.getDatabase("ComplaintsDataset");
	private static MongoCollection<Document> collection;
	private List<String> attributeList;

	@Override
	public List<String> getAttributes(String collectionName) {
		attributeList = new ArrayList<>();
		collection = MONGO_DATABASE.getCollection(collectionName);
		JSONObject object = new JSONObject(collection.find(Filters.exists("_id")).skip(1).first().toJson());
		String[] fieldNames = JSONObject.getNames(object);
		for (String s : fieldNames) {
			attributeList.add(s);
		}
		return attributeList;
	}
}
