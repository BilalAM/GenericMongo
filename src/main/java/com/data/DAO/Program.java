package com.data.DAO;

import java.util.List;

import com.data.MODELS.*;
import com.mongodb.client.model.Filters;

public class Program {
	public static void main(String[] args) {

		MongoCustomerConcrete customerConcrete = new MongoCustomerConcrete();
		MongoProductConcrete prodConrete = new MongoProductConcrete();
		MongoOrderConcrete orderConcrete = new MongoOrderConcrete();

		Customer c = customerConcrete.getOneFilter(Filters.eq("code", 1));
		Product p = prodConrete
				.getOneFilter(Filters.and(Filters.eq("productCode", 11), Filters.eq("productName", "Iphone")));
		
		

		orderConcrete.add(new Order(1, c, p, "10-2-2018"));

	}
}
