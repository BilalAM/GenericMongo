package com.data.models;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7821706464574968623L;
	/**
	 * 
	 */

	private int productCode;
	private String productName;
	private String productCategory;
	private double productPrice;
	private int productQuantity;
	private String description;

	public Product(int code, String name, String category, double price, int quantity, String description) {
		this.setProductCode(code);
		this.setProductName(name);
		this.setProductCategory(category);
		this.setProductPrice(price);
		this.setProductQuantity(quantity);
		this.setDescription(description);
	}

	public Product() {

	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
}
