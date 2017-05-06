package com.data.DAO;

import com.data.MODELS.Order;
import com.data.MODELS.Product;

public class MongoProductConcrete extends MongoConcrete<Product> {

	public MongoProductConcrete() {
		super("ProductCollection");

	}
}
