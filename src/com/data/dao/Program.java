package com.data.dao;

import java.util.List;

import com.data.models.*;
import com.data.scaffolder.ScaffolderMain;
import com.mongodb.client.model.Filters;

public class Program {
	public static void main(String[] args) {

		/*			EXAMPLE OF USING THE DAOs
		 * 
		 * MongoCustomerConcrete customerConcrete = new MongoCustomerConcrete();
		 * MongoProductConcrete prodConrete = new MongoProductConcrete();
		 * MongoOrderConcrete orderConcrete = new MongoOrderConcrete();
		 * 
		 * Customer c = customerConcrete.getOneFilter(Filters.eq("code", 1));
		 * Product p = prodConrete
		 * .getOneFilter(Filters.and(Filters.eq("productCode", 11),
		 * Filters.eq("productName", "Iphone")));
		 * 
		 * 
		 * 
		 * orderConcrete.add(new Order(1, c, p, "10-2-2018"));
		 * 
		 * 
		 */

		
		/* EXAMPLE OF USING THE SCAFFOLDER */
		ScaffolderMain main = new ScaffolderMain();
		main.generateClass("MongoConcrete", "Customer");
		main.generateClass("MongoConcrete", "Nature");
		main.generateClass("MongoConcrete", "Order");
		main.generateClass("MongoConcrete", "Product");
		main.generateClass("MongoConcrete", "Vehicle");
		
	


	}
}
