package com.data.DAO;

import com.data.MODELS.Product;

public class ProductMongoConcrete extends MongoConcrete<Product> {
	public ProductMongoConcrete() {
		super("ProductCollection");
	}
}