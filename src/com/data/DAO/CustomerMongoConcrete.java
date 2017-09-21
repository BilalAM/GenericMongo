package com.data.DAO;

import com.data.MODELS.Customer;

public class CustomerMongoConcrete extends MongoConcrete<Customer> {
	public CustomerMongoConcrete() {
		super("CustomerCollection");
	}
}