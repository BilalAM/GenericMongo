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


/*
 * 	Implementation has been merged into Parser.java , do not use this class
 * 	SUBECTED TO DELETION AFTER Version 2 RELEASE 
 * 
 * 
 * */
@Deprecated
public abstract class Mappy{

	private static final MongoClient MONGO_CLIENT = new MongoClient("localhost:27017");
	private static final MongoDatabase MONGO_DATABASE = MONGO_CLIENT.getDatabase("ComplaintsDataset");
	private static MongoCollection<Document> collection;
	private static List<String> attributeList;

	public static List<String> getAttributes(String collectionName) {
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
