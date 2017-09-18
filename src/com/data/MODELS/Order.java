package com.data.MODELS;

import java.io.Serializable;
import java.util.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3187662856542759395L;
	/**
	 * 
	 */
	@Getter
	@Setter
	private Product product;
	private List<Product> listOfProducts;
	private String timeStampOfOrder;
	private Customer customer;
	private int orderCode;
	
	public Order(int orderCode , Customer customer, List<Product> listOfProducts, String timestamp) {
		this.setOrderCode(orderCode);
		this.setCustomer(customer);
		this.setListOfProducts(listOfProducts);
		this.setTimeStampOfOrder(timestamp);
	}

	public Order(int orderCode , Customer customer, Product p, String timestamp) {
		this.setCustomer(customer);
		this.setProduct(p);
		this.setTimeStampOfOrder(timestamp);
		this.setOrderCode(orderCode);
	}

	public Order() {	
	}

	public Product getProduct(){
		return product;
	}
	public void setProduct(Product p){
		this.product = p;
	}
	public List<Product> getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(List<Product> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}

	public String getTimeStampOfOrder() {
		return timeStampOfOrder;
	}

	public void setTimeStampOfOrder(String timeStampOfOrder) {
		this.timeStampOfOrder = timeStampOfOrder;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

}
