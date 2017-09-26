package com.data.dao;

import com.data.models.Customer;

public class CustomerMongoConcrete extends MongoConcrete<Customer> {
	public CustomerMongoConcrete() {
		super("CustomerCollection");
	}
}