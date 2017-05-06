package com.data.DAO;
import com.data.MODELS.*;
public class MongoOrderConcrete extends MongoConcrete<Order>{
	
	public MongoOrderConcrete(){
		super("orderCollection");
		
	}
}
