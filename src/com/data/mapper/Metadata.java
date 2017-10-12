package com.data.mapper;

import java.util.HashMap;
import java.util.*;

public class Metadata {
	private String dbName;
	private List<CollectionMetaData> collections;

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public List<CollectionMetaData> getCollections() {
		return collections;
	}

	public void setCollections(List<CollectionMetaData> collections) {
		this.collections = collections;
	}

	public static void main(String[] args) {
		
	}

}
