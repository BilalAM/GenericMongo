package com.data.DAO;

import java.util.List;

import org.bson.conversions.Bson;

import com.mongodb.client.model.Filters;

interface IMongoAccess<T> {

	void add(T entity);

	T getOneFilter(Bson filterQuery);

	void remove(Bson filterQuery);

}
