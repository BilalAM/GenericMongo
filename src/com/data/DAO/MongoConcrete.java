package com.data.DAO;

import java.io.Reader;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.Block;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.json.*;

public abstract class MongoConcrete<T> implements IMongoAccess<T> {
	private static final MongoClient myClient = new MongoClient("localhost:27017");
	private static MongoDatabase database = myClient.getDatabase("ProductDB");
	private MongoCollection<Document> collection;

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public MongoConcrete(String collectionName) {
		collection = database.getCollection(collectionName);
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Override
	public void add(T entity) {
		JSONObject entityObject = new JSONObject(entity);
		collection.insertOne(Document.parse(entityObject.toString()));
		System.out.println(Document.parse(entityObject.toString()));
	}

	@Override
	public void remove(Bson filterExpression) {
		collection.findOneAndDelete(filterExpression);

	}

	@Override
	public T getOneFilter(Bson filterExpression) {
		T entityToReturn;
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		entityToReturn = gson.fromJson(collection.find(filterExpression).first().toJson(), entityClass);

		System.out.println(gson.toJson(entityToReturn));
		if (entityToReturn == null) {
			try {
				throw new Exception("Cannot find");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return entityToReturn;
	}
	
	//maps document results into a list of objects
	@Override
	public List<T> getLimitedFilteredResult(Bson filter, int limit) {
		Gson gson = new GsonBuilder().create();
		List<T> innerList = new ArrayList<>();
		collection.find(filter).limit(limit).forEach((Block<Document>) document -> {
			innerList.add(gson.fromJson(document.toJson(), entityClass));
		});
		return innerList;

	}
	
	@Override
	public void removeMany(Bson filterQuery){
		collection.deleteMany(filterQuery);
	}

}
