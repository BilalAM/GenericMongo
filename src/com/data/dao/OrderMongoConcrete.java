package com.data.dao;

import com.data.models.Order;

public class OrderMongoConcrete extends MongoConcrete<Order> {
	public OrderMongoConcrete() {
		super("OrderCollection");
	}
}