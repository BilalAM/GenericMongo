package com.data.MODELS;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7997141875557428226L;
	private String name;
	private int code;

	public Customer(int code, String name) {
		this.setName(name);
		this.setCode(code);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
