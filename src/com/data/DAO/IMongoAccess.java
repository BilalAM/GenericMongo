package com.data.DAO;

import java.util.List;

import org.bson.conversions.Bson;

import com.mongodb.client.model.Filters;

interface IMongoAccess<T> {

	void add(T entity);

	T getOneFilter(Bson filterQuery);
	
	List<T> getLimitedFilteredResult(Bson filter, int limit);
	
	void remove(Bson filterQuery);
	
	void removeMany(Bson filterQuery);

}
