package com.data.dao;

import com.data.models.Product;

public class ProductMongoConcrete extends MongoConcrete<Product> {
	public ProductMongoConcrete() {
		super("ProductCollection");
	}
}