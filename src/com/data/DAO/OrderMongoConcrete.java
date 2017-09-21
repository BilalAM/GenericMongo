package com.data.DAO;

import com.data.MODELS.Order;

public class OrderMongoConcrete extends MongoConcrete<Order> {
	public OrderMongoConcrete() {
		super("OrderCollection");
	}
}