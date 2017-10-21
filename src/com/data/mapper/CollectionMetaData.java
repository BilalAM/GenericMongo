package com.data.mapper;

import java.util.*;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CollectionMetaData {

	private List<Node> attributeNodes;
	private List<Node> associationsNodes;
	private String collectionName;
	private String collectionClassName;
	
	public List<Node> getAttributeNodes() {
		return attributeNodes;
	}
	public void setAttributeNodes(List<Node> attributeNodes) {
		this.attributeNodes = attributeNodes;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	public String getCollectionClassName() {
		return collectionClassName;
	}
	public void setCollectionClassName(String collectionClassName) {
		this.collectionClassName = collectionClassName;
	}
	public List<Node> getAssociationsNodes() {
		return associationsNodes;
	}
	public void setAssociationsNodes(List<Node> associationsNodes) {
		this.associationsNodes = associationsNodes;
	}

}
